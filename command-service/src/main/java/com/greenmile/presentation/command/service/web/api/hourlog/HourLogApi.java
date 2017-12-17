package com.greenmile.presentation.command.service.web.api.hourlog;

import com.greenmile.presentation.command.service.aggregate.hourlog.HourLogOwner;
import com.greenmile.presentation.command.service.command.hourlog.HourLogCommandFactory;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/hour-logs")
public class HourLogApi {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CommandGateway commandGateway;

    private final HourLogCommandFactory commandFactory = HourLogCommandFactory.INSTANCE;

    @PostMapping
    public CompletableFuture<String> addHourLog(@RequestBody HourLogOwner newhourLog) {
        String hourLogId = UUID.randomUUID().toString();
        Date actionTime = new Date();
        Assert.hasLength(newhourLog.getUserId(), "Missing user id");

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() ->
                commandGateway.sendAndWait(commandFactory.newAddHourLogCommand(hourLogId, actionTime,
                        newhourLog.getAction(), newhourLog.getUserId()))
        );
        return completableFuture
                .thenApply(s -> s)
                .exceptionally((e) -> {
                    logger.warn("Add hour log {} failed", newhourLog);
                    throw new RuntimeException(String.format("Add hour log %s failed because of %s ", newhourLog, e.getMessage()), e);
                });
    }
}

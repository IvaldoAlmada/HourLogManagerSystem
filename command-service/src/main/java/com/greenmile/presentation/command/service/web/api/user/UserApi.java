package com.greenmile.presentation.command.service.web.api.user;

import com.greenmile.presentation.command.service.aggregate.user.UserOwner;
import com.greenmile.presentation.command.service.command.user.UserCommandFactory;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/users")
public class UserApi {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CommandGateway commandGateway;
    private final UserCommandFactory commandFactory = UserCommandFactory.INSTANCE;

    @PostMapping
    public CompletableFuture<String> createUser(@RequestBody UserOwner user) {
        Assert.hasLength(user.getName(), "Missing user name");
        String userId = UUID.randomUUID().toString();
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() ->
                commandGateway.sendAndWait(commandFactory.newCreateUserCommand(userId, user.getName()))
        );
        return completableFuture
                .thenApply(s -> s)
                .exceptionally((e) -> {
                    logger.warn("Create User {} failed", user);
                    throw new RuntimeException(String.format("Create User %s failed", user), e);
                });
    }


}

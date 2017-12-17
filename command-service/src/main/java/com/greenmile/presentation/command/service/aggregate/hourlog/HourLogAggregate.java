package com.greenmile.presentation.command.service.aggregate.hourlog;

import com.greenmile.presentation.command.service.command.hourlog.AddHourLogCommand;
import com.greenmile.presentation.common.events.hourlog.HourLogAddedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.util.Assert;

import java.util.Date;

@Aggregate
public class HourLogAggregate {

    @AggregateIdentifier
    private String id;
    private Date actionTime;
    private String action;
    private String userId;

    public HourLogAggregate () {}

    @CommandHandler
    public HourLogAggregate(AddHourLogCommand command) {
        String id = command.identifier();
        Date actionTime = command.getActionTime();
        String action = command.getAction();
        String userId = command.getUserId();

        Assert.hasLength(id, "Missing id");
        Assert.hasLength(action, "Missing action");
        Assert.hasLength(userId, "Missing user id");

        AggregateLifecycle.apply(new HourLogAddedEvent(id, actionTime, action, userId));
    }

    @EventSourcingHandler
    protected void on(HourLogAddedEvent event) {
        this.id = event.getId();
        this.actionTime = event.getActionTime();
        this.action = event.getAction();
        this.userId = event.getUserId();
    }

    public String getId() {
        return id;
    }

    public Date getActionTime() {
        return actionTime;
    }

    public String getAction() {
        return action;
    }

    public String getUserId() {
        return userId;
    }
}

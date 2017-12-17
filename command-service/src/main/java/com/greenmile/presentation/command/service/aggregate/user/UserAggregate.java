package com.greenmile.presentation.command.service.aggregate.user;

import com.greenmile.presentation.command.service.command.user.CreateUserCommand;
import com.greenmile.presentation.common.events.user.UserCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.util.Assert;

import java.io.Serializable;

@Aggregate
public class UserAggregate implements Serializable {

    @AggregateIdentifier
    private String id;

    private String name;

    public UserAggregate() {
    }

    @CommandHandler
    public UserAggregate(CreateUserCommand command) {
        String id = command.identifier();
        String name = command.getName();

        Assert.hasLength(id, "Missing id");
        Assert.hasLength(name, "Missing user name");

        AggregateLifecycle.apply(new UserCreatedEvent(id, name));
    }

    @EventSourcingHandler
    protected void on(UserCreatedEvent event) {
        this.id = event.getId();
        this.name = event.getName();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

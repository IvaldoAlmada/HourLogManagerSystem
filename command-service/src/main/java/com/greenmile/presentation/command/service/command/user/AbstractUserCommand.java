package com.greenmile.presentation.command.service.command.user;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

public abstract class AbstractUserCommand<ID> {

    @TargetAggregateIdentifier
    private final ID id;

    AbstractUserCommand(ID id) {
        this.id = id;
    }

    public ID identifier() {
        return id;
    }
}

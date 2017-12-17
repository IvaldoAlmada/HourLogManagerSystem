package com.greenmile.presentation.command.service.command.hourlog;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

public abstract class AbstractHourLogCommand<ID> {

    @TargetAggregateIdentifier
    private final ID id;

    AbstractHourLogCommand(ID id) {
        this.id = id;
    }

    public ID identifier() {
        return id;
    }
}

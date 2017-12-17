package com.greenmile.presentation.common.events.user;


import com.greenmile.presentation.common.AbstractEvent;

public class UserCreatedEvent extends AbstractEvent {
    private final String name;

    public UserCreatedEvent(String id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

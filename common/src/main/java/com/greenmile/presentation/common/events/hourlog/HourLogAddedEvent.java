package com.greenmile.presentation.common.events.hourlog;

import com.greenmile.presentation.common.AbstractEvent;

import java.util.Date;

public class HourLogAddedEvent extends AbstractEvent {

    private final Date actionTime;
    private final String action;
    private final String userId;

    public HourLogAddedEvent(String id, Date actionTime, String action, String userId) {
        super(id);
        this.actionTime = actionTime;
        this.action = action;
        this.userId = userId;
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

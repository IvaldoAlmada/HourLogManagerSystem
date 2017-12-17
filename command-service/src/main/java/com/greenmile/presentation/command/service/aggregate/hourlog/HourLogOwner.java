package com.greenmile.presentation.command.service.aggregate.hourlog;

public class HourLogOwner {

    private String action;
    private String userId;

    public HourLogOwner() {}

    public HourLogOwner(String action, String userId) {
        this.action = action;
        this.userId = userId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

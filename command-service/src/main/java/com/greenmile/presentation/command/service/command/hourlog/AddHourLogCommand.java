package com.greenmile.presentation.command.service.command.hourlog;

import java.util.Date;

public class AddHourLogCommand extends AbstractHourLogCommand<String> {


    private final Date actionTime;
    private final String action;
    private final String userId;

    public AddHourLogCommand(String id, Date actionTime, String action, String userId) {
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

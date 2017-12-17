package com.greenmile.presentation.command.service.command.hourlog;

import java.util.Date;

public enum HourLogCommandFactory {
    INSTANCE;

    public AddHourLogCommand newAddHourLogCommand(String id, Date actionTime, String action, String userId) {
        return new AddHourLogCommand(id, actionTime, action, userId);
    }
}

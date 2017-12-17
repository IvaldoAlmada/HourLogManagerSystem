package com.greenmile.presentation.service;

import com.greenmile.presentation.entity.HourLog;

public interface HourLogService {

    Iterable<HourLog> getHourLogsByUserId(int userId);

    void saveHourLog(HourLog hourLog);

}

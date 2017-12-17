package com.greenmile.presentation.service.impl;

import com.greenmile.presentation.entity.HourLog;
import com.greenmile.presentation.repository.HourLogRepository;
import com.greenmile.presentation.service.HourLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class HourLogServiceImpl implements HourLogService {

    @Autowired
    private HourLogRepository hourLogRepository;

    @Override
    public Iterable<HourLog> getHourLogsByUserId(int userId) {
        Iterable<HourLog> hourLogs = hourLogRepository.findByUserId(userId);
        return hourLogs;
    }

    @Override
    public void saveHourLog(HourLog hourLog) {
        hourLog.setActionTime(new Date());
        hourLogRepository.save(hourLog);
    }
}

package com.greenmile.presentation.query.service.hourlog.service;

import com.greenmile.presentation.query.service.hourlog.entity.HourLogEntity;
import com.greenmile.presentation.query.service.hourlog.entity.WorkedDay;
import com.greenmile.presentation.query.service.hourlog.entity.WorkedTimeEntity;
import com.greenmile.presentation.query.service.hourlog.repository.HourLogReadOnlyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class HourLogReadOnlyService {

    @Autowired
    private HourLogReadOnlyRepository repository;

    private final DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    public CompletableFuture<Page<HourLogEntity>> findAll(Pageable pageable) {
        return CompletableFuture.supplyAsync(() -> repository.findAll(pageable));
    }

    public CompletableFuture<WorkedTimeEntity> findByUserAndDateRange(String userId, String startDateStr, String endDateStr) throws ParseException {
        Date startDate = formatter.parse(startDateStr);
        Date endDate = formatter.parse(endDateStr);
        List<HourLogEntity> hourLogs = repository.findByUserIdAndActionTime(userId, startDate, endDate);
        if (hourLogs.isEmpty()) {
            return CompletableFuture.supplyAsync(() -> new WorkedTimeEntity(userId, 0L));
        } else {
            List<HourLogEntity> hourLogsIn = hourLogs.stream().filter(hourLogEntity -> hourLogEntity.getAction()
                    .equals("IN")).collect(Collectors.toList());
            List<HourLogEntity> hourLogsOut = hourLogs.stream().filter(hourLogEntity -> hourLogEntity.getAction()
                    .equals("OUT")).collect(Collectors.toList());
            List<WorkedDay> workedDays = hourLogsIn.stream().map(hourLogIn -> {
                WorkedDay workedDay = new WorkedDay();
                workedDay.setInDate(hourLogIn.getActionTime());
                Optional<HourLogEntity> hourLogOut = hourLogsOut.stream().filter(hourLogOutToProcess ->
                        removeTime(hourLogOutToProcess.getActionTime()).equals(removeTime(hourLogIn.getActionTime()))).findFirst();
                hourLogOut.ifPresent(hourLogEntity -> workedDay.setOutDate(hourLogEntity.getActionTime()));
                return workedDay;
            }).collect(Collectors.toList());
            Long workedTimeLong = workedDays.stream().map(workedDay -> {
                if (workedDay.getInDate() != null && workedDay.getOutDate() != null) {
                    return workedDay.getOutDate().getTime() - workedDay.getInDate().getTime();
                } else {
                    return 0L;
                }
            }).reduce((x, y) -> x + y).get();
            WorkedTimeEntity workedTime = new WorkedTimeEntity(userId, workedTimeLong);
            return CompletableFuture.supplyAsync(() -> workedTime);
        }

    }

    private Date removeTime(Date date) {
        Calendar cal = Calendar.getInstance(); // locale-specific
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }


}

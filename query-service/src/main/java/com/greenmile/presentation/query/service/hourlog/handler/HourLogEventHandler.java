package com.greenmile.presentation.query.service.hourlog.handler;

import com.greenmile.presentation.common.events.hourlog.HourLogAddedEvent;
import com.greenmile.presentation.query.service.hourlog.entity.HourLogEntity;
import com.greenmile.presentation.query.service.hourlog.repository.HourLogWriteOnlyRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("hourLogEvents")
public class HourLogEventHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final HourLogWriteOnlyRepository repository;

    public HourLogEventHandler(HourLogWriteOnlyRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(HourLogAddedEvent event) {
        HourLogEntity createdHourLog = repository.save(new HourLogEntity(
                event.getId(),
                event.getActionTime(),
                event.getAction(),
                event.getUserId()
        ));

        logger.info("Hour Log {} is saved", createdHourLog);
    }
}

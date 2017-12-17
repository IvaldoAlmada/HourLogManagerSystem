package com.greenmile.presentation.query.service.user.handler;

import com.greenmile.presentation.common.events.user.UserCreatedEvent;
import com.greenmile.presentation.query.service.user.entity.UserEntity;
import com.greenmile.presentation.query.service.user.repository.UserWriteOnlyRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("userEvents")
public class UserEventHandler {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final UserWriteOnlyRepository repository;

    public UserEventHandler(UserWriteOnlyRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(UserCreatedEvent event) {
        UserEntity createdUser = repository.save(new UserEntity(
                event.getId(),
                event.getName()));

        logger.info("User {} is saved", createdUser);
    }
}

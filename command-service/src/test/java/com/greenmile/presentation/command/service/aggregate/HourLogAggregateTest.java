package com.greenmile.presentation.command.service.aggregate;

import com.greenmile.presentation.command.service.aggregate.hourlog.HourLogAggregate;
import com.greenmile.presentation.command.service.command.hourlog.AddHourLogCommand;
import com.greenmile.presentation.common.events.hourlog.HourLogAddedEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class HourLogAggregateTest {

    private AggregateTestFixture<HourLogAggregate> fixture;

    private Date actionTime = new Date();

    @Before
    public void setUp() {
        fixture = new AggregateTestFixture<>(HourLogAggregate.class);
    }

    @Test
    public void testCreateAccount() {
        fixture.givenNoPriorActivity()
                .when(new AddHourLogCommand("1", actionTime, "IN", "1"))
                .expectEvents(new HourLogAddedEvent("1", actionTime, "IN", "1"));
    }
}

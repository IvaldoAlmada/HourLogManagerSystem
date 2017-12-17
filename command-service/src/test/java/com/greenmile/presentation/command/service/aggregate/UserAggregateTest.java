package com.greenmile.presentation.command.service.aggregate;

import com.greenmile.presentation.command.service.aggregate.user.UserAggregate;
import com.greenmile.presentation.command.service.command.user.CreateUserCommand;
import com.greenmile.presentation.common.events.user.UserCreatedEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.Before;
import org.junit.Test;

public class UserAggregateTest {
    private AggregateTestFixture<UserAggregate> fixture;

    @Before
    public void setUp() {
        fixture = new AggregateTestFixture<>(UserAggregate.class);
    }

    @Test
    public void testCreateAccount() {
        fixture.givenNoPriorActivity()
                .when(new CreateUserCommand("user1", "test user"))
                .expectEvents(new UserCreatedEvent("user1", "test user"));
    }
}

package com.greenmile.presentation.command.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles({"dev"})
public class CommandServiceApplicationTest {

    @Inject
    ApplicationContext applicationContext;

    @Test
    public void contextLoads() {
        assertNotNull("application context loaded", applicationContext);
    }

}

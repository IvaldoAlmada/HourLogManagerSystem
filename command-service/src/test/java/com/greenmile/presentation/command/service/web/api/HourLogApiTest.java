package com.greenmile.presentation.command.service.web.api;

import com.greenmile.presentation.command.service.aggregate.hourlog.HourLogOwner;
import com.greenmile.presentation.command.service.command.hourlog.HourLogCommandFactory;
import com.greenmile.presentation.command.service.web.api.hourlog.HourLogApi;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(value = HourLogApi.class, secure = false)
public class HourLogApiTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommandGateway commandGateway;

    private HourLogOwner hourLogOwnerToRequest = new HourLogOwner();

    private final HourLogCommandFactory commandFactory = HourLogCommandFactory.INSTANCE;

    @Test
    public void addHourLogTest() throws Exception {
        hourLogOwnerToRequest.setAction("IN");
        hourLogOwnerToRequest.setUserId("1");
        Date actionTime = new Date();
        String addHourLogResultMock = "12345";
        Mockito.when(commandGateway.sendAndWait(commandFactory.newAddHourLogCommand(hourLogOwnerToRequest.getUserId(), actionTime,
                hourLogOwnerToRequest.getAction(), hourLogOwnerToRequest.getUserId()))).thenReturn(addHourLogResultMock);

        String hourLogJson = "{\"action\": \"IN\", \"userId\": 1}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/hour-logs").accept(MediaType.APPLICATION_JSON)
                .content(hourLogJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

}

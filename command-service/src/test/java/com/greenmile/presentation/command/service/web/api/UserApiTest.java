package com.greenmile.presentation.command.service.web.api;

import com.greenmile.presentation.command.service.command.user.UserCommandFactory;
import com.greenmile.presentation.command.service.web.api.hourlog.HourLogApi;
import com.greenmile.presentation.command.service.web.api.user.UserApi;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserApi.class, secure = false)
public class UserApiTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommandGateway commandGateway;

    private final UserCommandFactory commandFactory = UserCommandFactory.INSTANCE;

    private String createUserResultMock = "12345";

    @Test
    public void addHourLogTest() throws Exception {
        Mockito.when(commandGateway.sendAndWait(commandFactory.newCreateUserCommand("1", "Ivaldo"))).thenReturn(createUserResultMock);

        String userJson = "{\"name\": \"Ivaldo\"}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users").accept(MediaType.APPLICATION_JSON)
                .content(userJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}

package com.greenmile.presentation.query.service.rest;

import com.greenmile.presentation.query.service.user.entity.UserEntity;
import com.greenmile.presentation.query.service.user.repository.UserReadOnlyRepository;
import com.greenmile.presentation.query.service.user.rest.UserRestApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserRestApi.class, secure = false)
@EnableSpringDataWebSupport
public class UserRestApiTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserReadOnlyRepository userReadOnlyRepository;

    private Pageable mockPageable = mock(Pageable.class);
    private Page mockPage = new PageImpl(new ArrayList(), mockPageable, 10);


    @Test
    public void findAllTest() throws Exception {
        Mockito.when(userReadOnlyRepository.findAll(mockPageable)).thenReturn(mockPage);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/users").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertEquals("", result.getResponse().getContentAsString());
    }
}

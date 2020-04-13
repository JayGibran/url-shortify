package com.neueda.urlshortify.controller;


import com.neueda.urlshortify.dto.UrlResponseDTO;
import com.neueda.urlshortify.helper.RequestHelper;
import com.neueda.urlshortify.service.UrlShortenerService;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = UrlRedirectController.class)
public class UrlRedirectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UrlShortenerService urlShortenerService;

    @MockBean
    private RequestHelper requestHelper;


    @Test
    public void whenKeyNotExistThrowsNotFoundAndErrorMessage() throws Exception{

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/api/v1/redirect/fjaksdlf");


        MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isNotFound())
                .andReturn();

        assertEquals(result.getResolvedException().getMessage(), "The key entered was not found");
    }

    @Test
    public void whenKeyExistsReturnsOriginalUrl() throws Exception{

        UrlResponseDTO responseDTO = UrlResponseDTO.builder().originalUrl("http://www.neueda.com").build();

        given(urlShortenerService.getOriginalUrl(any())).willReturn(responseDTO);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/api/v1/redirect/b");

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.originalUrl").value("http://www.neueda.com"))
                .andReturn();

    }

}

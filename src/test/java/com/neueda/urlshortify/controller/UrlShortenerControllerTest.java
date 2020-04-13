package com.neueda.urlshortify.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neueda.urlshortify.dto.KeyUrlDTO;
import com.neueda.urlshortify.dto.OriginalUrlDTO;
import com.neueda.urlshortify.helper.RequestHelper;
import com.neueda.urlshortify.service.UrlShortenerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = UrlShortenerController.class)
public class UrlShortenerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UrlShortenerService urlShortenerService;

    @MockBean
    private RequestHelper requestHelper;

    @Autowired
    private ObjectMapper objectMapper;

    private JacksonTester<OriginalUrlDTO> shorteningRequest;

    @Before
    public void setup() {
        JacksonTester.initFields(this, objectMapper);
    }


    @Test
    public void whenUrlOriginalIsEmptyThrowsBadRequestAndErrorMessage() throws Exception{
        OriginalUrlDTO dto = new OriginalUrlDTO();
        dto.setOriginalUrl("");

        final String OriginalUrlDTOJson = shorteningRequest.write(dto).getJson();

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                "/api/v1/shortener").accept(MediaType.APPLICATION_JSON).content(OriginalUrlDTOJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isBadRequest())
                .andReturn();

        assertEquals(result.getResolvedException().getMessage(), "Please provide a url");
    }

    @Test
    public void whenOriginalUrlIsInvalidThrowsBadRequestAndErrorMessage() throws Exception{
        OriginalUrlDTO dto = new OriginalUrlDTO();
        dto.setOriginalUrl("jfkaldjfasdf");

        final String OriginalUrlDTOJson = shorteningRequest.write(dto).getJson();

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                "/api/v1/shortener").accept(MediaType.APPLICATION_JSON).content(OriginalUrlDTOJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isBadRequest())
                .andReturn();

        assertEquals(result.getResolvedException().getMessage(), "The URL entered is invalid");
    }

    @Test
    public void whenOriginalUrlIsValidReturnKey() throws Exception{
        OriginalUrlDTO dto = new OriginalUrlDTO();
        dto.setOriginalUrl("www.neueda.com");

        KeyUrlDTO keyDTO = KeyUrlDTO.builder().key("d").build();

        given(urlShortenerService.shortenUrl(anyString())).willReturn(keyDTO);

        when(requestHelper.isValid(anyString())).thenReturn(true);

        when(requestHelper.urlNormalization(anyString())).thenReturn("http://www.google.com");

        final String OriginalUrlDTOJson = shorteningRequest.write(dto).getJson();

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                "/api/v1/shortener").accept(MediaType.APPLICATION_JSON).content(OriginalUrlDTOJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.key").value("d"))
                .andReturn();
    }


}

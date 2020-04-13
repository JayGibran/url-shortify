package com.neueda.urlshortify.controller;

import com.neueda.urlshortify.dto.StatisticsDTO;
import com.neueda.urlshortify.helper.RequestHelper;
import com.neueda.urlshortify.model.Browser;
import com.neueda.urlshortify.model.OS;
import com.neueda.urlshortify.service.StatisticsService;
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

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = StatisticsController.class)
public class StatistcsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StatisticsService statisticsService;


    @Test
    public void whenKeyNotExistThrowsNotFoundAndErrorMessage() throws Exception{

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/api/v1/statistics/fjaksdlf");

        MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isNotFound())
                .andReturn();

        assertEquals(result.getResolvedException().getMessage(), "The key entered was not found");
    }

    @Test
    public void whenKeyExistsReturnStatisticsDTO() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/api/v1/statistics/b");
        OS os = new OS();
        os.incrementLinux();

        Browser browser = new Browser();
        browser.incrementChrome();

        StatisticsDTO statisticsDTO = StatisticsDTO.builder()
                .hits(1l)
                .lastAccess(LocalDateTime.now())
                .browser(browser)
                .os(os)
                .build();

        given(statisticsService.getStatistics(any())).willReturn(statisticsDTO);

        MvcResult result =  mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.hits").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastAccess").value(statisticsDTO.getLastAccess().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.browser.chrome").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.os.linux").value(1))
                .andReturn();
    }

}

package com.example.pricesearcher;

import com.example.pricesearcher.application.SearchPriceResponse;
import com.example.pricesearcher.infrastructure.db.PriceDB;
import com.example.pricesearcher.infrastructure.db.PriceJPARepository;
import com.example.pricesearcher.infrastructure.rest.PriceRestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PriceRestController priceRestController;

    @MockBean
    private PriceJPARepository priceJPARepository;

    @Test
    public void shouldPassFirstTest() throws Exception {

        //Given
        when(priceJPARepository.findAll()).thenReturn(getAllPrices());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/prices/?applicationDate=2020-06-14-10.00.00&productId=35455&brandId=1");

        //When
        MvcResult mvcResult = mvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();
        SearchPriceResponse searchPriceResponse = new ObjectMapper().readValue(json, SearchPriceResponse.class);

        //Then
        assertEquals("35455", searchPriceResponse.getProductId());
        assertEquals("1", searchPriceResponse.getBrandId());
        assertEquals("1", searchPriceResponse.getPriceList());
        assertEquals("2020-06-14-00.00.00", searchPriceResponse.getStartDate());
        assertEquals("2020-12-31-23.59.59", searchPriceResponse.getEndDate());
        assertEquals("35.50 EUR", searchPriceResponse.getFinalPrice());

    }

    @Test
    public void shouldPassSecondTest() throws Exception {

        //Given
        when(priceJPARepository.findAll()).thenReturn(getAllPrices());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/prices/?applicationDate=2020-06-14-16.00.00&productId=35455&brandId=1");

        //When
        MvcResult mvcResult = mvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();
        SearchPriceResponse searchPriceResponse = new ObjectMapper().readValue(json, SearchPriceResponse.class);

        //Then
        assertEquals("35455", searchPriceResponse.getProductId());
        assertEquals("1", searchPriceResponse.getBrandId());
        assertEquals("2", searchPriceResponse.getPriceList());
        assertEquals("2020-06-14-15.00.00", searchPriceResponse.getStartDate());
        assertEquals("2020-06-14-18.30.00", searchPriceResponse.getEndDate());
        assertEquals("25.45 EUR", searchPriceResponse.getFinalPrice());

    }

    @Test
    public void shouldPassThirdTest() throws Exception {

        //Given
        when(priceJPARepository.findAll()).thenReturn(getAllPrices());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/prices/?applicationDate=2020-06-14-21.00.00&productId=35455&brandId=1");

        //When
        MvcResult mvcResult = mvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();
        SearchPriceResponse searchPriceResponse = new ObjectMapper().readValue(json, SearchPriceResponse.class);

        //Then
        assertEquals("35455", searchPriceResponse.getProductId());
        assertEquals("1", searchPriceResponse.getBrandId());
        assertEquals("1", searchPriceResponse.getPriceList());
        assertEquals("2020-06-14-00.00.00", searchPriceResponse.getStartDate());
        assertEquals("2020-12-31-23.59.59", searchPriceResponse.getEndDate());
        assertEquals("35.50 EUR", searchPriceResponse.getFinalPrice());

    }

    @Test
    public void shouldPassFourthTest() throws Exception {

        //Given
        when(priceJPARepository.findAll()).thenReturn(getAllPrices());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/prices/?applicationDate=2020-06-15-10.00.00&productId=35455&brandId=1");

        //When
        MvcResult mvcResult = mvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();
        SearchPriceResponse searchPriceResponse = new ObjectMapper().readValue(json, SearchPriceResponse.class);

        //Then
        assertEquals("35455", searchPriceResponse.getProductId());
        assertEquals("1", searchPriceResponse.getBrandId());
        assertEquals("3", searchPriceResponse.getPriceList());
        assertEquals("2020-06-15-00.00.00", searchPriceResponse.getStartDate());
        assertEquals("2020-06-15-11.00.00", searchPriceResponse.getEndDate());
        assertEquals("30.50 EUR", searchPriceResponse.getFinalPrice());

    }

    @Test
    public void shouldPassFifthTest() throws Exception {

        //Given
        when(priceJPARepository.findAll()).thenReturn(getAllPrices());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/prices/?applicationDate=2020-06-16-21.00.00&productId=35455&brandId=1");

        //When
        MvcResult mvcResult = mvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();
        SearchPriceResponse searchPriceResponse = new ObjectMapper().readValue(json, SearchPriceResponse.class);

        //Then
        assertEquals("35455", searchPriceResponse.getProductId());
        assertEquals("1", searchPriceResponse.getBrandId());
        assertEquals("4", searchPriceResponse.getPriceList());
        assertEquals("2020-06-15-16.00.00", searchPriceResponse.getStartDate());
        assertEquals("2020-12-31-23.59.59", searchPriceResponse.getEndDate());
        assertEquals("38.95 EUR", searchPriceResponse.getFinalPrice());

    }

    private List<PriceDB> getAllPrices() {

        List<PriceDB> pricesDB = new ArrayList<>();

        PriceDB priceDB1 = new PriceDB();
        priceDB1.setPriceId("1");
        priceDB1.setBrandId("1");
        priceDB1.setStartDate(LocalDateTime.of(2020,06,14,00,00,00));
        priceDB1.setEndDate(LocalDateTime.of(2020,12,31,23,59,59));
        priceDB1.setPriceList("1");
        priceDB1.setProductId("35455");
        priceDB1.setPriority("0");
        priceDB1.setPrice(35.50);
        priceDB1.setCurrency("EUR");

        PriceDB priceDB2 = new PriceDB();
        priceDB2.setPriceId("2");
        priceDB2.setBrandId("1");
        priceDB2.setStartDate(LocalDateTime.of(2020,06,14,15,00,00));
        priceDB2.setEndDate(LocalDateTime.of(2020,06,14,18,30,00));
        priceDB2.setPriceList("2");
        priceDB2.setProductId("35455");
        priceDB2.setPriority("1");
        priceDB2.setPrice(25.45);
        priceDB2.setCurrency("EUR");

        PriceDB priceDB3 = new PriceDB();
        priceDB3.setPriceId("3");
        priceDB3.setBrandId("1");
        priceDB3.setStartDate(LocalDateTime.of(2020,06,15,00,00,00));
        priceDB3.setEndDate(LocalDateTime.of(2020,06,15,11,00,00));
        priceDB3.setPriceList("3");
        priceDB3.setProductId("35455");
        priceDB3.setPriority("1");
        priceDB3.setPrice(30.50);
        priceDB3.setCurrency("EUR");

        PriceDB priceDB4 = new PriceDB();
        priceDB4.setPriceId("4");
        priceDB4.setBrandId("1");
        priceDB4.setStartDate(LocalDateTime.of(2020,06,15,16,00,00));
        priceDB4.setEndDate(LocalDateTime.of(2020,12,31,23,59,59));
        priceDB4.setPriceList("4");
        priceDB4.setProductId("35455");
        priceDB4.setPriority("1");
        priceDB4.setPrice(38.95);
        priceDB4.setCurrency("EUR");

        pricesDB.add(priceDB1);
        pricesDB.add(priceDB2);
        pricesDB.add(priceDB3);
        pricesDB.add(priceDB4);

        return pricesDB;
    }


}

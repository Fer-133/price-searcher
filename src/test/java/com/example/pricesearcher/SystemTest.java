package com.example.pricesearcher;

import com.example.pricesearcher.application.SearchPriceResponse;
import com.example.pricesearcher.infrastructure.db.PriceDB;
import com.example.pricesearcher.infrastructure.db.PriceJPARepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SystemTest {

    @Autowired
    private PriceJPARepository priceJPARepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldWorkFirstCase () {

        //Given
        PriceDB priceDB = new PriceDB();
        priceDB.setPriceId("1");
        priceDB.setBrandId("1");
        priceDB.setStartDate(LocalDateTime.of(2020,06,14,00,00,00));
        priceDB.setEndDate(LocalDateTime.of(2020,12,31,23,59,59));
        priceDB.setPriceList("1");
        priceDB.setProductId("35455");
        priceDB.setPriority("0");
        priceDB.setPrice(35.50);
        priceDB.setCurrency("EUR");

        priceJPARepository.save(priceDB);

        //When
        ResponseEntity<SearchPriceResponse> res = restTemplate.getForEntity("/prices/?applicationDate=2020-06-14-10.00.00&productId=35455&brandId=1", SearchPriceResponse.class);

        //Then
        assertTrue(res.getStatusCode().is2xxSuccessful());

        SearchPriceResponse response = res.getBody();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
        String expectedStartDate = formatter.format(priceDB.getStartDate());
        String expectedEndDate = formatter.format(priceDB.getEndDate());

        DecimalFormat df = new DecimalFormat("#.00", DecimalFormatSymbols.getInstance(Locale.US));

        String expectedFinalPrice = df.format(priceDB.getPrice()) + " " + priceDB.getCurrency();

        assertEquals(priceDB.getProductId(), response.getProductId());
        assertEquals(priceDB.getBrandId(), response.getBrandId());
        assertEquals(priceDB.getPriceList(), response.getPriceList());
        assertEquals(expectedStartDate, response.getStartDate());
        assertEquals(expectedEndDate, response.getEndDate());
        assertEquals(expectedFinalPrice, response.getFinalPrice());

    }

}

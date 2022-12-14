package com.example.pricesearcher;

import com.example.pricesearcher.application.SearchPricePetition;
import com.example.pricesearcher.application.SearchPriceResponse;
import com.example.pricesearcher.application.SearchPriceUseCase;
import com.example.pricesearcher.domain.aggregates.PriceDO;
import com.example.pricesearcher.domain.exceptions.PriceNotFoundException;
import com.example.pricesearcher.domain.repositories.PriceRepository;
import com.example.pricesearcher.domain.services.PriceService;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnitTest {

    @Test
    public void shouldPassFirstTest () throws PriceNotFoundException {

        //Given
        PriceRepository priceRepository = mock(PriceRepository.class);
        PriceService priceService = new PriceService(priceRepository);
        SearchPriceUseCase searchPriceUseCase = new SearchPriceUseCase(priceService);

        SearchPricePetition petition = SearchPricePetition.builder()
                .applicationDate("2020-06-14-10.00.00")
                .productId("35455")
                .brandId("1")
                .build();

        //When
        when(priceRepository.findAll()).thenReturn(getAllPrices());
        SearchPriceResponse response = searchPriceUseCase.execute(petition);

        //Then
        assertEquals("35455", response.getProductId());
        assertEquals("1", response.getBrandId());
        assertEquals("1", response.getPriceList());
        assertEquals("2020-06-14-00.00.00", response.getStartDate());
        assertEquals("2020-12-31-23.59.59", response.getEndDate());
        assertEquals("35.50 EUR", response.getFinalPrice());
    }

    @Test
    public void shouldPassSecondTest() throws PriceNotFoundException {

        //Given
        PriceRepository priceRepository = mock(PriceRepository.class);
        PriceService priceService = new PriceService(priceRepository);
        SearchPriceUseCase searchPriceUseCase = new SearchPriceUseCase(priceService);

        SearchPricePetition petition = SearchPricePetition.builder()
                .applicationDate("2020-06-14-16.00.00")
                .productId("35455")
                .brandId("1")
                .build();

        //When
        when(priceRepository.findAll()).thenReturn(getAllPrices());
        SearchPriceResponse response = searchPriceUseCase.execute(petition);

        //Then
        assertEquals("35455", response.getProductId());
        assertEquals("1", response.getBrandId());
        assertEquals("2", response.getPriceList());
        assertEquals("2020-06-14-15.00.00", response.getStartDate());
        assertEquals("2020-06-14-18.30.00", response.getEndDate());
        assertEquals("25.45 EUR", response.getFinalPrice());
    }

    @Test
    public void shouldPassThirdTest() throws PriceNotFoundException {

        //Given
        PriceRepository priceRepository = mock(PriceRepository.class);
        PriceService priceService = new PriceService(priceRepository);
        SearchPriceUseCase searchPriceUseCase = new SearchPriceUseCase(priceService);

        SearchPricePetition petition = SearchPricePetition.builder()
                .applicationDate("2020-06-14-21.00.00")
                .productId("35455")
                .brandId("1")
                .build();

        //When
        when(priceRepository.findAll()).thenReturn(getAllPrices());
        SearchPriceResponse response = searchPriceUseCase.execute(petition);

        //Then
        assertEquals("35455", response.getProductId());
        assertEquals("1", response.getBrandId());
        assertEquals("1", response.getPriceList());
        assertEquals("2020-06-14-00.00.00", response.getStartDate());
        assertEquals("2020-12-31-23.59.59", response.getEndDate());
        assertEquals("35.50 EUR", response.getFinalPrice());
    }

    @Test
    public void shouldPassFourthTest() throws PriceNotFoundException {

        //Given
        PriceRepository priceRepository = mock(PriceRepository.class);
        PriceService priceService = new PriceService(priceRepository);
        SearchPriceUseCase searchPriceUseCase = new SearchPriceUseCase(priceService);

        SearchPricePetition petition = SearchPricePetition.builder()
                .applicationDate("2020-06-15-10.00.00")
                .productId("35455")
                .brandId("1")
                .build();

        //When
        when(priceRepository.findAll()).thenReturn(getAllPrices());
        SearchPriceResponse response = searchPriceUseCase.execute(petition);

        //Then
        assertEquals("35455", response.getProductId());
        assertEquals("1", response.getBrandId());
        assertEquals("3", response.getPriceList());
        assertEquals("2020-06-15-00.00.00", response.getStartDate());
        assertEquals("2020-06-15-11.00.00", response.getEndDate());
        assertEquals("30.50 EUR", response.getFinalPrice());
    }

    @Test
    public void shouldPassFifthTest() throws PriceNotFoundException {

        //Given
        PriceRepository priceRepository = mock(PriceRepository.class);
        PriceService priceService = new PriceService(priceRepository);
        SearchPriceUseCase searchPriceUseCase = new SearchPriceUseCase(priceService);

        SearchPricePetition petition = SearchPricePetition.builder()
                .applicationDate("2020-06-16-21.00.00")
                .productId("35455")
                .brandId("1")
                .build();

        //When
        when(priceRepository.findAll()).thenReturn(getAllPrices());
        SearchPriceResponse response = searchPriceUseCase.execute(petition);

        //Then
        assertEquals("35455", response.getProductId());
        assertEquals("1", response.getBrandId());
        assertEquals("4", response.getPriceList());
        assertEquals("2020-06-15-16.00.00", response.getStartDate());
        assertEquals("2020-12-31-23.59.59", response.getEndDate());
        assertEquals("38.95 EUR", response.getFinalPrice());
    }

    private List<PriceDO> getAllPrices() {

        return new ArrayList<>(
                List.of(
                        PriceDO.builder()
                                .brandId("1")
                                .startDate(LocalDateTime.of(2020,06,14,00,00,00))
                                .endDate(LocalDateTime.of(2020,12,31,23,59,59))
                                .priceList("1")
                                .productId("35455")
                                .priority("0")
                                .price(35.50)
                                .currency("EUR")
                                .build(),

                        PriceDO.builder()
                                .brandId("1")
                                .startDate(LocalDateTime.of(2020,06,14,15,00,00))
                                .endDate(LocalDateTime.of(2020,06,14,18,30,00))
                                .priceList("2")
                                .productId("35455")
                                .priority("1")
                                .price(25.45)
                                .currency("EUR")
                                .build(),

                        PriceDO.builder()
                                .brandId("1")
                                .startDate(LocalDateTime.of(2020,06,15,00,00,00))
                                .endDate(LocalDateTime.of(2020,06,15,11,00,00))
                                .priceList("3")
                                .productId("35455")
                                .priority("1")
                                .price(30.50)
                                .currency("EUR")
                                .build(),

                        PriceDO.builder()
                                .brandId("1")
                                .startDate(LocalDateTime.of(2020,06,15,16,00,00))
                                .endDate(LocalDateTime.of(2020,12,31,23,59,59))
                                .priceList("4")
                                .productId("35455")
                                .priority("1")
                                .price(38.95)
                                .currency("EUR")
                                .build()
                )
        );

    }

}

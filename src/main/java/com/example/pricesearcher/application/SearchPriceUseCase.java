package com.example.pricesearcher.application;

import com.example.pricesearcher.domain.PriceService;

public class SearchPriceUseCase {

    private PriceService priceService;
    public SearchPriceUseCase (PriceService priceService) { this.priceService = priceService; }

    public SearchPriceResponse execute (SearchPricePetition petititon) {

        return SearchPriceResponse.builder()
                .productId("35455")
                .brandId("1")
                .priceList("1")
                .startDate("2020-06-14-00.00.00")
                .endDate("2020-12-31-23.59.59")
                .finalPrice("35.50 EUR")
                .build();

    }

}

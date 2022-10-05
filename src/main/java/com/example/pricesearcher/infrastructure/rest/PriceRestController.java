package com.example.pricesearcher.infrastructure.rest;

import com.example.pricesearcher.application.SearchPricePetition;
import com.example.pricesearcher.application.SearchPriceResponse;
import com.example.pricesearcher.application.SearchPriceUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PriceRestController {

    @Autowired
    SearchPriceUseCase searchPriceUseCase;

    @GetMapping("/prices/")
    public SearchPriceResponse searchPrice (
            @RequestParam String applicationDate,
            @RequestParam String productId,
            @RequestParam String brandId
    ){
        SearchPricePetition petition = SearchPricePetition.builder()
                .applicationDate(applicationDate)
                .productId(productId)
                .brandId(brandId)
                .build();

        return searchPriceUseCase.execute(petition);
    }

}

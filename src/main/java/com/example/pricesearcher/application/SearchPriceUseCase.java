package com.example.pricesearcher.application;

import com.example.pricesearcher.domain.PriceDO;
import com.example.pricesearcher.domain.PriceService;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class SearchPriceUseCase {

    private PriceService priceService;
    public SearchPriceUseCase (PriceService priceService) { this.priceService = priceService; }

    public SearchPriceResponse execute (SearchPricePetition petititon) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
        LocalDateTime dateTime = LocalDateTime.parse(petititon.getApplicationDate(), formatter);

        DecimalFormat df = new DecimalFormat("#.00", DecimalFormatSymbols.getInstance(Locale.US));

        PriceDO priceDO = priceService.findPrice(dateTime, petititon.getProductId(), petititon.getBrandId());

        return SearchPriceResponse.builder()
                .productId(priceDO.getProductId())
                .brandId(priceDO.getBrandId())
                .priceList(priceDO.getPriceList())
                .startDate(formatter.format(priceDO.getStartDate()))
                .endDate(formatter.format(priceDO.getEndDate()))
                .finalPrice(df.format(priceDO.getPrice()) + " " + priceDO.getCurrency())
                .build();
    }

}

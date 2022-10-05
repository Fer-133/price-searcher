package com.example.pricesearcher.application;

import com.example.pricesearcher.domain.aggregates.PriceDO;
import com.example.pricesearcher.domain.exceptions.PriceNotFoundException;
import com.example.pricesearcher.domain.services.PriceService;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class SearchPriceUseCase {

    private PriceService priceService;
    public SearchPriceUseCase (PriceService priceService) { this.priceService = priceService; }

    public SearchPriceResponse execute (SearchPricePetition petition) throws PriceNotFoundException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
        LocalDateTime dateTime = LocalDateTime.parse(petition.getApplicationDate(), formatter);

        DecimalFormat df = new DecimalFormat("#.00", DecimalFormatSymbols.getInstance(Locale.US));

        PriceDO priceDO = priceService.findPrice(dateTime, petition.getProductId(), petition.getBrandId());

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

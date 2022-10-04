package com.example.pricesearcher.application;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchPriceResponse {

    private final String productId;
    private final String brandId;
    private final String priceList;
    private final String startDate;
    private final String endDate;
    private final String finalPrice;

}

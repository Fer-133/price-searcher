package com.example.pricesearcher.application;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchPriceResponse {

    private String productId;
    private String brandId;
    private String priceList;
    private String startDate;
    private String endDate;
    private String finalPrice;

}

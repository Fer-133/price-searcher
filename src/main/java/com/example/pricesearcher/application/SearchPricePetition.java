package com.example.pricesearcher.application;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchPricePetition {

    private final String applicationDate;
    private final String productId;
    private final String brandId;

}

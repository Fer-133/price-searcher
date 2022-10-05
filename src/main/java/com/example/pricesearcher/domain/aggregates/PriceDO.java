package com.example.pricesearcher.domain.aggregates;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Builder
public class PriceDO {

    private final String brandId;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final String priceList;
    private final String productId;
    private final String priority;
    private final Double price;
    private final String currency;

}

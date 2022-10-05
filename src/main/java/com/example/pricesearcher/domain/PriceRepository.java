package com.example.pricesearcher.domain;

import java.util.List;

public interface PriceRepository {

    List<PriceDO> findAll();

}

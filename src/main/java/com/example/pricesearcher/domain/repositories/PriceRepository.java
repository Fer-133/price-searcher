package com.example.pricesearcher.domain.repositories;

import com.example.pricesearcher.domain.aggregates.PriceDO;

import java.util.List;

public interface PriceRepository {

    List<PriceDO> findAll();

}

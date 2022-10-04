package com.example.pricesearcher.domain;

import com.example.pricesearcher.domain.PriceDO;

import java.util.List;

public interface PriceRepository {

    List<PriceDO> findAll();

}

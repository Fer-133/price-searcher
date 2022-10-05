package com.example.pricesearcher.infrastructure.db;

import com.example.pricesearcher.domain.aggregates.PriceDO;
import com.example.pricesearcher.domain.repositories.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PriceJPARepositoryAdapter implements PriceRepository {

    @Autowired
    PriceJPARepository priceJPARepository;

    @Override
    public List<PriceDO> findAll() {

        List<PriceDB> pricesDB = priceJPARepository.findAll();
        List<PriceDO> pricesDO = new ArrayList<>();

        for (PriceDB priceDB : pricesDB){

            PriceDO priceDO = PriceDO.builder()
                    .brandId(priceDB.getBrandId())
                    .startDate(priceDB.getStartDate())
                    .endDate(priceDB.getEndDate())
                    .priceList(priceDB.getPriceList())
                    .productId(priceDB.getProductId())
                    .priority(priceDB.getPriority())
                    .price(priceDB.getPrice())
                    .currency(priceDB.getCurrency())
                    .build();

            pricesDO.add(priceDO);
        }

        return pricesDO;
    }
}

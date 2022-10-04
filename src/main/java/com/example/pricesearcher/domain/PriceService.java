package com.example.pricesearcher.domain;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class PriceService {

    private PriceRepository priceRepository;

    public PriceService (PriceRepository priceRepository) { this.priceRepository = priceRepository; }

    public PriceDO findPrice(LocalDateTime applicationDate, String productId, String brandId){

        List<PriceDO> pricesDO = priceRepository.findAll();

        Optional<PriceDO> optionalPriceDO = pricesDO.stream()
                .filter(p -> p.getEndDate().isAfter(applicationDate))
                .filter(p -> p.getStartDate().isBefore(applicationDate))
                .filter(p -> p.getProductId().equals(productId))
                .filter(p -> p.getBrandId().equals(brandId))
                .max(Comparator.comparing(PriceDO::getPriority));

        if (optionalPriceDO.isPresent()) {
            return optionalPriceDO.get();
        } else {
            throw new RuntimeException();
        }
    }
}

package com.example.pricesearcher.domain.services;

import com.example.pricesearcher.domain.aggregates.PriceDO;
import com.example.pricesearcher.domain.exceptions.PriceNotFoundException;
import com.example.pricesearcher.domain.repositories.PriceRepository;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class PriceService {

    private PriceRepository priceRepository;

    public PriceService (PriceRepository priceRepository) { this.priceRepository = priceRepository; }

    public PriceDO findPrice(LocalDateTime applicationDate, String productId, String brandId) throws PriceNotFoundException {

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
            throw new PriceNotFoundException("Precio no encontrado.");
        }
    }
}

package com.example.pricesearcher.infrastructure.config;

import com.example.pricesearcher.application.SearchPriceUseCase;
import com.example.pricesearcher.domain.PriceRepository;
import com.example.pricesearcher.domain.PriceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public PriceService priceService (PriceRepository priceRepository) { return new PriceService(priceRepository); }


    @Bean
    public SearchPriceUseCase searchPriceUseCase (PriceService priceService) { return new SearchPriceUseCase(priceService); }

}

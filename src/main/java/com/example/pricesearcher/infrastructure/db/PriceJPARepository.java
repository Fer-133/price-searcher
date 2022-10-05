package com.example.pricesearcher.infrastructure.db;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceJPARepository extends JpaRepository <PriceDB, String> {
}

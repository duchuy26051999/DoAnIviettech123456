package com.example.Auction.repository;

import com.example.Auction.entity.CommissionLevelEntity;
import org.apache.catalina.startup.ContextRuleSet;
import org.springframework.data.repository.CrudRepository;

public interface CommissionLevelRepository extends CrudRepository<CommissionLevelEntity,Integer> {
    CommissionLevelEntity findByMinPriceGreaterThanAndMaxPriceLessThan(Double minPrice,Double maxPrice);

}

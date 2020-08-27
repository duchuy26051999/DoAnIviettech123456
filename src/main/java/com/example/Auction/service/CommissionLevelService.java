package com.example.Auction.service;

import com.example.Auction.entity.CommissionLevelEntity;
import com.example.Auction.repository.CommissionLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommissionLevelService {
    @Autowired
    private CommissionLevelRepository commissionLevelRepo;

    public CommissionLevelEntity getCommissionLevelByPrice(Double minPrice,Double maxPrice){
        CommissionLevelEntity newCommissionLevel = null;
        try {
            newCommissionLevel = commissionLevelRepo.findByMinPriceGreaterThanAndMaxPriceLessThan(minPrice,maxPrice);
        }catch (Exception e){
            System.out.println(e);
    }

        return newCommissionLevel;
    }

    public List<CommissionLevelEntity> getAllCommissionLevel(){
        return (List<CommissionLevelEntity>) commissionLevelRepo.findAll();
    }
    public CommissionLevelEntity getIncrement(List<CommissionLevelEntity> listCommissionLevel,Double bidMax){
        CommissionLevelEntity commissionLevel = new CommissionLevelEntity();
        for (CommissionLevelEntity level : listCommissionLevel) {
            if(level.getMinPrice() <= bidMax && bidMax <= level.getMaxPrice()){
                commissionLevel = level;
            }
        }
        return commissionLevel;
    }
}

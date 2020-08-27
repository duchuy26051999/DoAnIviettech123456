package com.example.Auction.service;

import com.example.Auction.entity.IncrementEntity;
import com.example.Auction.repository.IncrementLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncrementLevelService {
    @Autowired
    private IncrementLevelRepository incrementLevelRepo;

    public List<IncrementEntity> getAllIncrement(){
        List<IncrementEntity> list;
        try {
            list = (List<IncrementEntity>) incrementLevelRepo.findAll();
            return list;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public double bidPriceIncrement(double price){
        List<IncrementEntity> list = getAllIncrement();
        double sum = 0;
        for(IncrementEntity incrementEntity : list){
            if(price >= incrementEntity.getMinPrice() && price <= incrementEntity.getMaxPrice()){
                sum = price * incrementEntity.getIncrement();
            }
        }
        return sum;
    }

    public double getIncrement(double price){
        List<IncrementEntity> list = getAllIncrement();
        double sum = 0;
        for (IncrementEntity i : list){
            if(price >= i.getMinPrice() && price <= i.getMaxPrice()){
                sum = i.getIncrement();
            }
        }
        return sum;
    }
}

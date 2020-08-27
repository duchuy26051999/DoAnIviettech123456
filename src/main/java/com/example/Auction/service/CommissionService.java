package com.example.Auction.service;

import com.example.Auction.entity.CommissionEntity;
import com.example.Auction.repository.CommissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommissionService {
    @Autowired
    private CommissionRepository commissionRepo;

    public CommissionEntity addCommission(CommissionEntity commission){
        CommissionEntity newCommission = null;
        try{
            newCommission = commissionRepo.save(commission);
        }catch (Exception e){
            System.out.println(e);
        }
        return newCommission;
    }

    public Double sumCommissions(String day, String day1){
        return commissionRepo.sumCommissions(day,day1);
    }

    public List<CommissionEntity> getAll(){
        List<CommissionEntity> list;
        try {
            list = commissionRepo.findByOrderByIdDesc();
            return list;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
}

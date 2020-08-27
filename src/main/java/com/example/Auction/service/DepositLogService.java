package com.example.Auction.service;

import com.example.Auction.entity.DepositLogEntity;
import com.example.Auction.repository.DepositLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepositLogService {
    @Autowired
    private DepositLogRepository depositLogRepo;

    public DepositLogEntity addDepositLog(DepositLogEntity depositLog){
        DepositLogEntity newDepositLog = null;
        try {
            newDepositLog = depositLogRepo.save(depositLog);
        }catch (Exception e){
            System.out.println(e);
        }
        return newDepositLog;
    }

    public List<DepositLogEntity> getAllDeposit(){
        List<DepositLogEntity> logEntities;
        try {
            logEntities = depositLogRepo.findByOrderByIdDesc();
            return logEntities;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public Double sumDeposit(String day, String day1){
        return depositLogRepo.sumDeposit(day,day1);
    }
}

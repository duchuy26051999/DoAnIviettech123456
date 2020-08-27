package com.example.Auction.service;

import com.example.Auction.entity.BidEntity;
import com.example.Auction.entity.CreditEntity;
import com.example.Auction.entity.TransactionLogEntity;
import com.example.Auction.repository.TransactionLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionLogService {
    @Autowired
    private TransactionLogRepository transactionLogRepo;

    public List<TransactionLogEntity> getAllTransactionLogByCreditId(int id){
        return transactionLogRepo.findByCredit_Id(id);
    }

    public TransactionLogEntity addTransactionLog(TransactionLogEntity transactionLog){
        TransactionLogEntity newTransactionLog = null;
        try{
            newTransactionLog = transactionLogRepo.save(transactionLog);
        }catch (Exception e){
            System.out.println(e);
        }
        return newTransactionLog;
    }

    public List<TransactionLogEntity> getAllTransactionLogOrderByDateLog(int id){
        List<TransactionLogEntity> listTransactionLog = null;
        try {
            listTransactionLog = (List<TransactionLogEntity>) transactionLogRepo.findByCredit_IdOrderByIdDesc(id);
        }catch (Exception e){
            System.out.println(e);
        }
        return listTransactionLog;
    }

    public List<TransactionLogEntity> getAllTransactionLogByDateLogBetween(int id,LocalDateTime dateStart,LocalDateTime dateEnd){
        List<TransactionLogEntity> listTransaction = null;
        try{
            listTransaction = transactionLogRepo.findByCredit_IdAndDateLogBetween(id,dateStart,dateEnd);
        }catch (Exception e){
            System.out.println(e);
        }
        return listTransaction;
    }
}

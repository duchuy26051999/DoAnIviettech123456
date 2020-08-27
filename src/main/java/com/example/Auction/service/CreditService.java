package com.example.Auction.service;

import com.example.Auction.entity.CreditEntity;
import com.example.Auction.repository.CategoryRepository;
import com.example.Auction.repository.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditService {
    @Autowired
    private CreditRepository creditRepo;

    public CreditEntity getCreditByCustomerId(int id){
        return creditRepo.findByCustomerId(id);
    }

    public boolean isCreditAlreadyPresent(String cardNumber){
        boolean kq = false;
        if(creditRepo.findByCardNumber(cardNumber) != null){
            kq = true;
        }else {
            kq = false;
        }
        return kq;
    }

    public CreditEntity createCredit(CreditEntity credit){
        CreditEntity newCredit = new CreditEntity();
        try{
            newCredit= creditRepo.save(credit);
        }catch (Exception e){
            System.out.println(e);
        }
        return newCredit;
    }

    public List<CreditEntity> getAllCredit(){
        return (List<CreditEntity>) creditRepo.findAll();
    }

    public CreditEntity getCreditByCardOrCustomerName(String cardNumber,String customerName){
        CreditEntity newCredit = null;
        try {
            newCredit = creditRepo.findByCardNumberLikeOrCustomer_CustomerName(cardNumber,customerName);
        }catch (Exception e){
            System.out.println(e);
        }
        return newCredit;
    }

    public CreditEntity getCreditByCardNumber(String cardNumber){
        CreditEntity newCredit = null;
        try {
            newCredit = creditRepo.findByCardNumber(cardNumber);
        }catch (Exception e){
            System.out.println(e);
        }
        return newCredit;
    }

    public void updateCredit(CreditEntity credit){
        creditRepo.save(credit);
    }
}

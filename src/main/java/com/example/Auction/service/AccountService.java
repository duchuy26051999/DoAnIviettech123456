package com.example.Auction.service;

import com.example.Auction.entity.AccountEntity;
import com.example.Auction.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepo;

    public AccountEntity addAccount(AccountEntity acc){
        AccountEntity newAccount  = null;
        try{
            newAccount= accountRepo.save(acc);
        }catch (Exception e){
            System.out.println(e);
        }
        return newAccount;
    }
    public AccountEntity getAccount(int id){
        AccountEntity account = accountRepo.findById(id);
        return account;

    }

    public AccountEntity getAccountByUserName(String name){
        AccountEntity account = accountRepo.findByUserName(name);
        return account;
    }
    public boolean isAccountAlreadyPresent(AccountEntity accountEntity){
        Boolean kq = false;
        if(accountRepo.findByUserName(accountEntity.getUserName()) != null){
            kq = true;
        }else {
            kq = false;
        }
        return kq;
    }

    public List<AccountEntity> findByOrderByIdDescAccount(){
        List<AccountEntity> list;
        try {
            list = (List<AccountEntity>) accountRepo.findByOrderByIdDesc();
            return list;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public List<AccountEntity> searchAccountUserName(String name){
        name = "%" +name+ "%";
        return (List<AccountEntity>) accountRepo.searchAccountName(name);
    }

    public AccountEntity getUsername(String name){
        return accountRepo.findByUserName(name);
    }

}

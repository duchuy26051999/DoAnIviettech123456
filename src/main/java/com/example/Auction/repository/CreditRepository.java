package com.example.Auction.repository;

import com.example.Auction.entity.CreditEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditRepository extends CrudRepository<CreditEntity, Integer> {
    CreditEntity findByCustomerId(int id);

    CreditEntity findByCardNumber(String cardNumber);

    CreditEntity findByCardNumberLikeOrCustomer_CustomerName(String cardNumber,String name);
}

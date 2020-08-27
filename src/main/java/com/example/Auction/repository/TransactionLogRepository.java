package com.example.Auction.repository;

import com.example.Auction.entity.CreditEntity;
import com.example.Auction.entity.TransactionLogEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionLogRepository extends CrudRepository<TransactionLogEntity, Integer> {
    List<TransactionLogEntity> findByCredit_Id(int id);

    List<TransactionLogEntity> findByCredit_IdOrderByIdDesc(int id);

    List<TransactionLogEntity> findByDateLogBetween(LocalDateTime dateStart,LocalDateTime dateEnd);

    List<TransactionLogEntity> findByCredit_IdAndDateLogBetween(int id,LocalDateTime dateStart,LocalDateTime dateEnd);
}

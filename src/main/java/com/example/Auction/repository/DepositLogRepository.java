package com.example.Auction.repository;

import com.example.Auction.entity.DepositLogEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DepositLogRepository extends CrudRepository<DepositLogEntity,Integer> {
    @Query(value = "select depositCustomer(?1,?2)", nativeQuery = true)
    Double sumDeposit(String day, String da1);

    List<DepositLogEntity> findByOrderByIdDesc();
}

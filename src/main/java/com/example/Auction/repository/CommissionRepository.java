package com.example.Auction.repository;

import com.example.Auction.entity.CommissionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommissionRepository extends CrudRepository<CommissionEntity,Integer> {
    @Query(value = "select sumCommission(?1,?2)", nativeQuery = true)
    Double sumCommissions(String day, String day2);

    List<CommissionEntity> findByOrderByIdDesc();
}

package com.example.Auction.repository;

import com.example.Auction.entity.IncrementEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncrementLevelRepository extends CrudRepository<IncrementEntity, Integer> {
}

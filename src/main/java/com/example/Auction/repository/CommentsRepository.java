package com.example.Auction.repository;

import com.example.Auction.entity.CommentsEntity;
import com.example.Auction.entity.CommentsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepository extends CrudRepository<CommentsEntity, Integer> {
    List<CommentsEntity> findByAuctionId(int id);
}

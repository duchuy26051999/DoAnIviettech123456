package com.example.Auction.repository;

import com.example.Auction.entity.NewsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends CrudRepository<NewsEntity, Integer> {
}

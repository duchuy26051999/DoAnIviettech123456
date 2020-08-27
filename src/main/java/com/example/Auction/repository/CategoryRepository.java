package com.example.Auction.repository;

import com.example.Auction.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, Integer> {
    CategoryEntity findById(int id);

    List<CategoryEntity> findByOrderByIdDesc();

    List<CategoryEntity> findByCategoryLike(String name);
}

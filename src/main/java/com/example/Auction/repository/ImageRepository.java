package com.example.Auction.repository;

import com.example.Auction.entity.ImageEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends CrudRepository<ImageEntity, Integer> {
    List<ImageEntity> findByGoods_Id(int id);

    @Query(value = "select image.id,url,goodID\n" +
            "   from image join goods\n" +
            "   on image.goodID = goods.id\n" +
            "   where goods.good_Name like ?1", nativeQuery = true)
    List<ImageEntity> findByImageGoodName(String name);

    List<ImageEntity> findByOrderByIdDesc();
}

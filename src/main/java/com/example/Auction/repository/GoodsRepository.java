package com.example.Auction.repository;

import com.example.Auction.entity.CategoryEntity;
import com.example.Auction.entity.GoodsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsRepository extends CrudRepository<GoodsEntity, Integer> {
    List<GoodsEntity> findByStatusAndGoodNameLike(String status,String name);

    @Query(value ="SELECT * from goods ORDER BY id desc limit 4", nativeQuery=true)
    List<GoodsEntity> getGood1();

    @Query(value="select * from goods limit 4", nativeQuery=true)
    List<GoodsEntity> getGood2();

    @Query(value="select * from goods limit 4,4", nativeQuery=true)
    List<GoodsEntity> getGood3();

    @Query(value="select * from goods limit 9,4", nativeQuery=true)
    List<GoodsEntity> getGood4();

    @Query(value="select goods.id,goods.goodsID,goods.good_Name,good_Description,image,categoryID\n" +
            " from goods join category on goods.categoryID = category.id\n" +
            " where category.category = ?1", nativeQuery=true)
    List<GoodsEntity> searchCategory(String name);

    GoodsEntity findById(int id);

    List<GoodsEntity> findByCustomerId(int id);

//    List<GoodsEntity> findByStatusLikeOrStatusLike(String status1,String status2);
    List<GoodsEntity> findByCustomer_IdAndStatusLikeOrStatusLike(int id,String status1,String status2);

    List<GoodsEntity> findByCustomerIdOrderByIdDesc(int id);

    List<GoodsEntity> findByCustomer_IdAndGoodNameLike(int id,String name);

    @Query(value = "SELECT goods.id,good_Name,good_Description,status,goods.categoryID,customerID \n" +
            " FROM goods\n" +
            " join category on goods.categoryID = category.id \n" +
            " where good_Name like ?1 and\n" +
            " category.category = ?2", nativeQuery = true)
    List<GoodsEntity> findByGoodNameCategory(String name, String category);
}

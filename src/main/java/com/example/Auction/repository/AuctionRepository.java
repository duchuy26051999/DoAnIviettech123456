package com.example.Auction.repository;

import com.example.Auction.entity.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AuctionRepository extends CrudRepository<AuctionEntity, Integer> {
    AuctionEntity findById(int id);

    List<AuctionEntity> findByGoodsGoodNameLike(String name);


    List<AuctionEntity> findByGoods_Category_Category(String name);

    AuctionEntity findByGoods_IdAndAuctionStatus(int id,String status);

    List<AuctionEntity> findByCustomerId(int id);

    List<AuctionEntity> findByCustomerIdOrderByIdDesc(int id);
    List<AuctionEntity> findByAuctionStatusOrderByDateStartAsc(String status);

    List<AuctionEntity> findByDateStartBetweenOrderByDateStartAsc(LocalDateTime date1,LocalDateTime date2);

    List<AuctionEntity> findByGoods_Category_CategoryOrderByDateStartAsc(String name);
    List<AuctionEntity> findByGoods_Category_CategoryAndAuctionStatusOrderByDateStartAsc(String name,String status);

    List<AuctionEntity> findByGoods_GoodNameLike(String name);

    List<AuctionEntity> findByCustomer_IdAndDateStartBetween(int id,LocalDateTime date1,LocalDateTime date2);

    List<AuctionEntity> findByOrderByIdDesc();

    @Query(value = "select auction.id,auction.customerID,goodsID,\n" +
            "date_Start,date_End,price_Start,\n" +
            "desired_Price,auction_Status\n" +
            "from auction join goods on\n" +
            "auction.goodsID = goods.id\n" +
            "where goods.status = 'Sell success'\n" +
            "and date_End between ?1 and ?2", nativeQuery = true)
    List<AuctionEntity> searchReportAuctionSuccess(String day, String day1);

    @Query(value = "select auction.id,auction.customerID,goodsID,\n" +
            "date_Start,date_End,price_Start,\n" +
            "desired_Price,auction_Status\n" +
            "from auction join goods on\n" +
            "auction.goodsID = goods.id\n" +
            "where goods.status = 'Sell Fails' and auction_Status = 'close'\n" +
            "and date_End between ?1 and ?2", nativeQuery = true)
    List<AuctionEntity> searchReportAuctionFailure(String day, String day1);

    @Query(value = "select auction.id,auction.customerID,goodsID,\n" +
            "date_Start,date_End,price_Start,\n" +
            "desired_Price,auction_Status\n" +
            "from auction join goods on\n" +
            "auction.goodsID = goods.id where\n" +
            "goods.status = 'Sell success'", nativeQuery = true)
    List<AuctionEntity> reportAuctionSuccess();

    @Query(value = "select auction.id,auction.customerID,goodsID,\n" +
            "date_Start,date_End,price_Start,\n" +
            "desired_Price,auction_Status\n" +
            "from auction join goods on\n" +
            "auction.goodsID = goods.id\n" +
            "where goods.status = 'Sell Fails'", nativeQuery = true)
    List<AuctionEntity> reportAuctionFailure();

    @Query(value = "select auction.id,auction.goodsID,auction.customerID,\n" +
            "date_Start,date_End,price_Start,desired_Price,auction_Status\n" +
            "from auction \n" +
            "join customer on auction.customerID = customer.id \n" +
            "join goods on auction.goodsID = goods.id \n" +
            "where customer.customer_Name like ?1 \n" +
            "or goods.good_Name like ?2\n" +
            "and auction_Status = ?3", nativeQuery = true)
    List<AuctionEntity> searchAuctionName(String customer,String goods,String status);
}

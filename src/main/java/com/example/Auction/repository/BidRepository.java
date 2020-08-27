package com.example.Auction.repository;

import com.example.Auction.entity.BidEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BidRepository extends CrudRepository<BidEntity, Integer> {

    BidEntity findByAuctionIdAndBidPrice(int auctionID,Double price);
    List<BidEntity> findByCustomerId(int id);

    List<BidEntity> findByAuction_IdOrderByBidPriceDesc(int id);

    List<BidEntity> findByAuctionIdOrderByBidPriceDesc(int id);

    BidEntity findByBidPriceAndAuction_Id(Double price,int id);

    List<BidEntity> findByAuctionId(int id);

    List<BidEntity> findByCustomerIdOrderByIdDesc(int id);

    List<BidEntity> findByCustomer_IdAndDateBidBetween(int id, LocalDateTime date1,LocalDateTime date2);

}

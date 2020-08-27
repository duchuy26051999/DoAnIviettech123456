package com.example.Auction.service;

import com.example.Auction.entity.AuctionEntity;
import com.example.Auction.entity.BidEntity;
import com.example.Auction.entity.IncrementEntity;
import com.example.Auction.entity.TransactionLogEntity;
import com.example.Auction.repository.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BidService {
    @Autowired
    private BidRepository bidRepo;
    @Autowired
    private AuctionService auctionService;
    @Autowired
    private IncrementLevelService incrementLevelService;

    public BidEntity getBidByAccountIdAndPrice(int auctionID,Double price){
        return bidRepo.findByAuctionIdAndBidPrice(auctionID,price);
    }

    public List<BidEntity> getAllBid(){
        List<BidEntity> list;
        try {
            list = (List<BidEntity>) bidRepo.findAll();
            return list;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
    public double getPriceBidIncrement(double price,int id){
        AuctionEntity auction = auctionService.getAuction(id);
        List<BidEntity> bid = getAllBid1(auction.getId());
        if(bid.isEmpty()){
            double s = auction.getPriceStart();
            return s;
        }else {
            double s = getIncrement(price);
            double sum = 0;
            for (BidEntity b : bid){
                if(sum < b.getBidPrice()){
                    sum = b.getBidPrice() * s;
                }
            }
            return sum;
        }
    }

    public String getPriceBidIncrementFormat(double price,int id){
        AuctionEntity auction = auctionService.getAuction(id);
        List<BidEntity> bid = getAllBid1(auction.getId());
        if(bid.isEmpty()){
            double s = auction.getPriceStart();
            DecimalFormat decimo = new DecimalFormat("###,###,###,###.##");
            return decimo.format(s);
        }else {
            double s = getIncrement(price);
            double sum = 0;
            for (BidEntity b : bid){
                if(sum < b.getBidPrice()){
                    sum = b.getBidPrice() * s;
                }
            }
            DecimalFormat decimo = new DecimalFormat("###,###,###,###.##");
            return decimo.format(sum);
        }
    }
    // lay ra % cua de tang gia dau theo so tien
    public double getIncrement(double price){
        double s = 0;
        List<IncrementEntity> increment = incrementLevelService.getAllIncrement();
        for (IncrementEntity i : increment){
            if(price >= i.getMinPrice() && price <= i.getMaxPrice()){
                s = i.getIncrement();
            }
        }
        return s;
    }
    public List<BidEntity> getAllBid1(int id){
        List<BidEntity> list;
        try {
            list = (List<BidEntity>) bidRepo.findByAuctionIdOrderByBidPriceDesc(id);
            return list;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
    public BidEntity addBid(BidEntity bid){
        BidEntity bidEntity;
        try {
            bidEntity = bidRepo.save(bid);
            return bidEntity;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public List<BidEntity> getAllBidCustomer(int id){
        List<BidEntity> list;
        try {
            list = bidRepo.findByCustomerId(id);
            return list;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public BidEntity getBidOfCustomerHaveBidMax(Double bidMax,int id){
        return bidRepo.findByBidPriceAndAuction_Id(bidMax,id);
    }

    public List<BidEntity> getAllBidByAuctionID(int id){
        return bidRepo.findByAuctionId(id);
    }

    public List<BidEntity> getAllBidOfCustomerOrderById(int id){
        List<BidEntity> listBid = null;
        try {
            listBid = bidRepo.findByCustomerIdOrderByIdDesc(id);
        }catch (Exception e){
            System.out.println(e);
        }
        return listBid;
    }

    public List<BidEntity> getAllBidLogBetWeen(int id, LocalDateTime dateStart,LocalDateTime dateEnd){
        List<BidEntity> listBid = null;
        try{
            listBid = bidRepo.findByCustomer_IdAndDateBidBetween(id,dateStart,dateEnd);
        }catch (Exception e){
            System.out.println(e);
        }
        return listBid;
    }
}

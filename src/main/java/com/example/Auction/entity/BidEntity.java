package com.example.Auction.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Table(name = "bid")
public class BidEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "customerID")
    private CustomerEntity customer;
    private Double bidPrice;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateBid;
    private String bidStatus;
    @ManyToOne
    @JoinColumn(name = "auctionID")
    private AuctionEntity auction;

    public BidEntity() {
    }

    public BidEntity(int id, CustomerEntity customer, Double bidPrice, LocalDateTime dateBid, String bidStatus, AuctionEntity auction) {
        this.id = id;
        this.customer = customer;
        this.bidPrice = bidPrice;
        this.dateBid = dateBid;
        this.bidStatus = bidStatus;
        this.auction = auction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public Double getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(Double bidPrice) {
        this.bidPrice = bidPrice;
    }

    public LocalDateTime getDateBid() {
        return dateBid;
    }

    public void setDateBid(LocalDateTime dateBid) {
        this.dateBid = dateBid;
    }

    public String getBidStatus() {
        return bidStatus;
    }

    public void setBidStatus(String bidStatus) {
        this.bidStatus = bidStatus;
    }

    public AuctionEntity getAuction() {
        return auction;
    }

    public void setAuction(AuctionEntity auction) {
        this.auction = auction;
    }

    public String formatPrice() {
        DecimalFormat decimo = new DecimalFormat("###,###,###,###.##");
        String price = decimo.format(bidPrice);
        return price;
    }
    public String formatDate(){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateFormat = format.format(dateBid);
        return dateFormat;
    }
}

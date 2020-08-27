package com.example.Auction.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customer")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String customerName;
    private String address;
    private String phoneNumber;
    @Length(min = 9,max = 12,message = "The ID is in the range of 9 to 12 numbers")
    private String idNumber;
    private String email;
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<BidEntity> bidList;
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<CommentsEntity> comments;
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<OrdersEntity> ordersList;
    @OneToOne(mappedBy = "customer", fetch = FetchType.LAZY)
    private CreditEntity credit;
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<AuctionEntity> auctionList;
    @OneToOne
    @JoinColumn(name = "accountID")
    private AccountEntity account;
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<GoodsEntity> goodList;

    public CustomerEntity() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<BidEntity> getBidList() {
        return bidList;
    }

    public void setBidList(List<BidEntity> bidList) {
        this.bidList = bidList;
    }

    public List<CommentsEntity> getComments() {
        return comments;
    }

    public void setComments(List<CommentsEntity> comments) {
        this.comments = comments;
    }

    public List<OrdersEntity> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<OrdersEntity> ordersList) {
        this.ordersList = ordersList;
    }

    public CreditEntity getCredit() {
        return credit;
    }

    public void setCredit(CreditEntity credit) {
        this.credit = credit;
    }

    public List<AuctionEntity> getAuctionList() {
        return auctionList;
    }

    public void setAuctionList(List<AuctionEntity> auctionList) {
        this.auctionList = auctionList;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    public List<GoodsEntity> getGoodList() {
        return goodList;
    }

    public void setGoodList(List<GoodsEntity> goodList) {
        this.goodList = goodList;
    }
}
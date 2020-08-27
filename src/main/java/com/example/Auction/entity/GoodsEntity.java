package com.example.Auction.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "goods")
public class GoodsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String goodName;
    private String goodDescription;
    private String status;
    @ManyToOne
    @JoinColumn(name = "categoryID")
    private CategoryEntity category;
    @OneToMany(mappedBy = "goods", fetch = FetchType.EAGER)
    private List<AuctionEntity> auctionList;
    @OneToMany(mappedBy = "goods",fetch = FetchType.LAZY)
    private List<ImageEntity> imageList;
    @ManyToOne
    @JoinColumn(name = "customerID")
    private CustomerEntity customer;

    public GoodsEntity() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getGoodDescription() {
        return goodDescription;
    }

    public void setGoodDescription(String goodDescription) {
        this.goodDescription = goodDescription;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public List<AuctionEntity> getAuctionList() {
        return auctionList;
    }

    public void setAuctionList(List<AuctionEntity> auctionList) {
        this.auctionList = auctionList;
    }

    public List<ImageEntity> getImageList() {
        return imageList;
    }

    public void setImageList(List<ImageEntity> imageList) {
        this.imageList = imageList;
    }

    public String getUrlImage(){
        String result = null;
        result = imageList.get(0).getUrl();
        return result;
    }
}

package com.example.Auction.entity;

import javax.persistence.*;

@Entity
@Table(name = "image")
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String url;
    @ManyToOne
    @JoinColumn(name = "goodID")
    private GoodsEntity goods;

    public ImageEntity() {
    }

    public ImageEntity(String url, GoodsEntity goods) {
        this.url = url;
        this.goods = goods;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public GoodsEntity getGoods() {
        return goods;
    }

    public void setGoods(GoodsEntity goods) {
        this.goods = goods;
    }
}

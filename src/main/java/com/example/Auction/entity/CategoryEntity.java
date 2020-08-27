package com.example.Auction.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String category;
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private List<GoodsEntity> goodsList;

    public CategoryEntity() {
    }

    public CategoryEntity(int id, String category, List<GoodsEntity> goodsList) {
        this.id = id;
        this.category = category;
        this.goodsList = goodsList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<GoodsEntity> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<GoodsEntity> goodsList) {
        this.goodsList = goodsList;
    }
}

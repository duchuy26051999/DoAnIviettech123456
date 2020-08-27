package com.example.Auction.entity;

import javax.persistence.*;

@Entity
@Table(name = "Increment")
public class IncrementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Double minPrice;
    private Double maxPrice;
    private Double increment;

    public IncrementEntity() {
    }

    public IncrementEntity(int id, Double minPrice, Double maxPrice, Double increment) {
        this.id = id;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.increment = increment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Double getIncrement() {
        return increment;
    }

    public void setIncrement(Double increment) {
        this.increment = increment;
    }
}

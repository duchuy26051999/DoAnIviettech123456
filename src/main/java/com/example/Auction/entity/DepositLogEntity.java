package com.example.Auction.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "deposit_log")
public class DepositLogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Double amount;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateDeposit;
    @ManyToOne
    @JoinColumn(name = "staffID")
    private StaffEntity staff;
    @ManyToOne
    @JoinColumn(name = "creditID")
    private CreditEntity credit;

    public DepositLogEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDateDeposit() {
        return dateDeposit;
    }

    public void setDateDeposit(LocalDateTime dateDeposit) {
        this.dateDeposit = dateDeposit;
    }

    public StaffEntity getStaff() {
        return staff;
    }

    public void setStaff(StaffEntity staff) {
        this.staff = staff;
    }

    public CreditEntity getCredit() {
        return credit;
    }

    public void setCredit(CreditEntity credit) {
        this.credit = credit;
    }

    public String formatDate(){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateFormat = format.format(dateDeposit);
        return dateFormat;
    }
}

package com.example.Auction.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "TransactionLog")
public class TransactionLogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "creditID")
    private CreditEntity credit;
    private String content;
    private Double balance;
    private Double amount;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateLog;
    private String logType;


    public TransactionLogEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CreditEntity getCredit() {
        return credit;
    }

    public void setCredit(CreditEntity credit) {
        this.credit = credit;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDateLog() {
        return dateLog;
    }

    public void setDateLog(LocalDateTime dateLog) {
        this.dateLog = dateLog;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String formatAmount(){
        DecimalFormat decimal = new DecimalFormat("###,###,###,###.##");
        String amountFormat = decimal.format(amount);
        return amountFormat;
    }
    public String formatBalance(){
        DecimalFormat decimal = new DecimalFormat("###,###,###,###.##");
        String balanceFormat = decimal.format(balance);
        return balanceFormat;
    }
    public String formatDate(){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateFormat = format.format(dateLog);
        return dateFormat;
    }
}

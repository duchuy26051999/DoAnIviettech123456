package com.example.Auction.entity;

import javax.persistence.*;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "credit")
public class CreditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String cardNumber;
    private Double amount;
    @OneToOne
    @JoinColumn(name = "customerID")
    private CustomerEntity customer;
    @OneToMany(mappedBy = "credit", fetch = FetchType.LAZY)
    private List<TransactionLogEntity> transactionLogList;
    @OneToMany(mappedBy = "credit",fetch = FetchType.LAZY)
    private List<DepositLogEntity> depositLogList;

    public CreditEntity() {
    }

    public CreditEntity(int id, String cardNumber, Double amount, CustomerEntity customer, List<TransactionLogEntity> transactionLogList) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.amount = amount;
        this.customer = customer;
        this.transactionLogList = transactionLogList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public List<TransactionLogEntity> getTransactionLogList() {
        return transactionLogList;
    }

    public void setTransactionLogList(List<TransactionLogEntity> transactionLogList) {
        this.transactionLogList = transactionLogList;
    }

    public List<DepositLogEntity> getDepositLogList() {
        return depositLogList;
    }

    public void setDepositLogList(List<DepositLogEntity> depositLogList) {
        this.depositLogList = depositLogList;
    }

    public Long createCredit(){
        Random generator = new Random();
        Long value = generator.nextLong() / 1000;
        if(value > 0){
            value *=1;
        }else{
            value *= -1;
        }
        return value;
    }

    public String formatAmount(){
        DecimalFormat decimal = new DecimalFormat("###,###,###,###.##");
        String amountFormat = decimal.format(amount);
        return amountFormat;
    }
}

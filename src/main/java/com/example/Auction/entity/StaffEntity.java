package com.example.Auction.entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Staff")
public class StaffEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String staffName;
    private String address;
    private String staffPosition;
    private String phoneNumber;
    private String email;
    @OneToOne
    @JoinColumn(name = "accountID")
    private AccountEntity account;
    @OneToMany(mappedBy = "staff",fetch = FetchType.EAGER)
    private List<NewsEntity> newsList;
    @OneToMany(mappedBy = "staff",fetch = FetchType.LAZY)
    private List<DepositLogEntity> depositLogList;


    public StaffEntity() {
    }

    public StaffEntity(int id, String staffName, String address, String staffPosition, String numberPhone, String email, AccountEntity account, List<NewsEntity> newsList) {
        this.id = id;
        this.staffName = staffName;
        this.address = address;
        this.staffPosition = staffPosition;
        this.phoneNumber = numberPhone;
        this.email = email;
        this.account = account;
        this.newsList = newsList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStaffPosition() {
        return staffPosition;
    }

    public void setStaffPosition(String staffPosition) {
        this.staffPosition = staffPosition;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    public List<NewsEntity> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<NewsEntity> newsList) {
        this.newsList = newsList;
    }

    public List<DepositLogEntity> getDepositLogList() {
        return depositLogList;
    }

    public void setDepositLogList(List<DepositLogEntity> depositLogList) {
        this.depositLogList = depositLogList;
    }
}

package com.example.Auction.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Accounts")
public class AccountEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userName;
    @Length(min = 8,message = "Password must be at least 8 characters")
    @Column(name = "password",nullable = false)
    private String passWord;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "account_role",
            joinColumns = @JoinColumn(name = "accountID",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "roleID",
                    referencedColumnName = "id"))
    private Set<RoleEntity> role;
    @Column(name = "enabled", nullable = false, columnDefinition
            = "bit(1) default 1")
    private boolean enabled;
    @OneToOne(mappedBy = "account",fetch = FetchType.LAZY)
    private CustomerEntity customer;
    @OneToOne(mappedBy = "account", fetch = FetchType.LAZY)
    private StaffEntity staff;


    public AccountEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Set<RoleEntity> getRole() {
        return role;
    }

    public void setRole(Set<RoleEntity> role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public StaffEntity getStaff() {
        return staff;
    }

    public void setStaff(StaffEntity staff) {
        this.staff = staff;
    }
}

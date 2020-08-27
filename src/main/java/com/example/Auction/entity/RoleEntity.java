package com.example.Auction.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Role")
public class RoleEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String role;
    @ManyToMany(mappedBy = "role")
    private Set<AccountEntity> account;

    public RoleEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRoleName(String role) {
        this.role = role;
    }

    public Set<AccountEntity> getAccount() {
        return account;
    }

    public void setAccount(Set<AccountEntity> account) {
        this.account = account;
    }
}

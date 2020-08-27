package com.example.Auction.service;

import com.example.Auction.entity.RoleEntity;
import com.example.Auction.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepo;
    public RoleEntity getRole(int id){
        RoleEntity role =  roleRepo.findById(id);
        return role;
    }
    public RoleEntity getRoleByName(String name){
        return roleRepo.findByRole(name);
    }
}

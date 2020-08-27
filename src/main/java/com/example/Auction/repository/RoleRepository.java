package com.example.Auction.repository;

import com.example.Auction.entity.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Integer> {
    RoleEntity findById(int id);
    public List<RoleEntity> findByAccount_UserName(String userName);
    public RoleEntity findByRole(String role);
}

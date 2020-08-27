package com.example.Auction.repository;

import com.example.Auction.entity.StaffEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepository extends CrudRepository<StaffEntity, Integer> {
    StaffEntity findByAccount_UserName(String name);

    StaffEntity findById(int id);

    List<StaffEntity> findByOrderByIdDesc();

    StaffEntity findByEmail(String name);

    StaffEntity findByPhoneNumber(String name);

    List<StaffEntity> findByStaffNameLikeOrAddressLikeAndStaffPosition(String name,String address, String position);
}

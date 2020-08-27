package com.example.Auction.service;

import com.example.Auction.entity.StaffEntity;
import com.example.Auction.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService {
    @Autowired
    private StaffRepository staffRepo;

    public StaffEntity getStaffByAccountName(String name){
        return staffRepo.findByAccount_UserName(name);
    }

    public StaffEntity getStaffID(int id){
        return staffRepo.findById(id);
    }

    public List<StaffEntity> getAllStaff(){
        List<StaffEntity> list;
        try {
            list = staffRepo.findByOrderByIdDesc();
            return list;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public List<StaffEntity> searchStaffName(String name,String position){
        name = "%" +name+ "%";
        return staffRepo.findByStaffNameLikeOrAddressLikeAndStaffPosition(name,name,position);
    }

    public StaffEntity addStaff(StaffEntity staff){
        StaffEntity newStaff;
        try {
            newStaff = staffRepo.save(staff);
            return newStaff;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public StaffEntity checkEmail(String name){
        StaffEntity staff;
        try {
            staff = staffRepo.findByEmail(name);
            return staff;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public StaffEntity checkPhone(String name){
        StaffEntity staff;
        try {
            staff = staffRepo.findByPhoneNumber(name);
            return staff;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
}


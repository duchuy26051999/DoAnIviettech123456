package com.example.Auction.repository;

import com.example.Auction.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, Integer> {
    CustomerEntity findById(int id);
    CustomerEntity findByAccount_Id(int id);
    CustomerEntity findByEmail(String email);
    CustomerEntity findByPhoneNumber(String phoneNumber);
    CustomerEntity findByIdNumber(String idNumber);
    CustomerEntity findByAccount_UserName(String name);

    List<CustomerEntity> findByOrderByIdDesc();
    List<CustomerEntity> findByCustomerNameLikeOrAddressLikeOrIdNumberLike(String name, String address, String idNumber);
//    CustomerEntity

}

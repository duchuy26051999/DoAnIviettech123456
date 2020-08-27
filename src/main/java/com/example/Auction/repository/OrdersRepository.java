package com.example.Auction.repository;

import com.example.Auction.entity.OrdersEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends CrudRepository<OrdersEntity, Integer> {

    List<OrdersEntity> findByCustomer_CustomerNameOrderByIdDesc(String name);

    List<OrdersEntity> findByCustomerIdAndStatusOrderByIdDesc(int id,String status);

    OrdersEntity findById(int id);

}

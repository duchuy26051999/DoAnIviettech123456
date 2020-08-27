package com.example.Auction.service;

import com.example.Auction.entity.OrdersEntity;
import com.example.Auction.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {
    @Autowired
    private OrdersRepository ordersRepo;

    public OrdersEntity addOrders(OrdersEntity orders){
        OrdersEntity newOrders = null;
        try{
            newOrders = ordersRepo.save(orders);
        }catch (Exception e){
            System.out.println(e);
        }
        return newOrders;
    }

    public List<OrdersEntity> getAllOrdersOfCustomerOrderById(String name){
        List<OrdersEntity> newList = null;
        try{
            newList = ordersRepo.findByCustomer_CustomerNameOrderByIdDesc(name);
        }catch (Exception e){
            System.out.println(e);
        }
        return newList;
    }

    public List<OrdersEntity> getAllOrdersSuccess(int id,String status){
        List<OrdersEntity> newList = null;
        try{
            newList = ordersRepo.findByCustomerIdAndStatusOrderByIdDesc(id,status);
        }catch (Exception e){
            System.out.println(e);
        }
        return newList;
    }

    public OrdersEntity getOrderById(int id){
        return ordersRepo.findById(id);
    }

    public OrdersEntity checkoutOrders(OrdersEntity orders){
        OrdersEntity newOrders = null;
        orders.setStatus("confirmed");
        try{
            newOrders = ordersRepo.save(orders);
        }catch (Exception e){
            System.out.println(e);
        }
        return newOrders;
    }
}

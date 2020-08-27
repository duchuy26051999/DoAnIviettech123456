package com.example.Auction.service;

import com.example.Auction.entity.AccountEntity;
import com.example.Auction.entity.CustomerEntity;
import com.example.Auction.entity.RoleEntity;
import com.example.Auction.repository.AccountRepository;
import com.example.Auction.repository.CustomerRepository;
import com.example.Auction.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;


@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepo;
    @Autowired
    private AccountRepository accountRepo;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private RoleService roleService;

    @Transactional(rollbackOn = Exception.class)
    public CustomerEntity addAccount(AccountEntity account) throws Exception {
        CustomerEntity newCustomer = account.getCustomer();
        account.setEnabled(true);
        account.setPassWord(encoder.encode(account.getPassWord()));
        RoleEntity roleEntity = roleService.getRoleByName("ROLE_USER");
        account.setRole(new HashSet<RoleEntity>(Arrays.asList(roleEntity)));
        AccountEntity newAcc = accountRepo.save(account);
        newCustomer.setAccount(accountRepo.findById(newAcc.getId()));
        return customerRepo.save(newCustomer);
    }

    public CustomerEntity getCustomer(int id){
        CustomerEntity customer =  customerRepo.findById(id);
        return customer;
    }
    public CustomerEntity getCustomerByAccountId(int id){
        CustomerEntity customer = customerRepo.findByAccount_Id(id);
        return customer;
    }
    public boolean isEmailAlreadyPresent(String email){
        Boolean kq = false;
        if(customerRepo.findByEmail(email) != null){
            kq = true;
        }else{
            kq = false;
        }
        return kq;
    }
    public boolean isIdNumberAlreadyPresent(String idNumber){
        Boolean kq = false;
        if(customerRepo.findByIdNumber(idNumber) != null){
            kq = true;
        }else{
            kq = false;
        }
        return kq;
    }
    public boolean isPhoneNumberAlreadyPresent(String phoneNumber){
        Boolean kq = false;
        if(customerRepo.findByPhoneNumber(phoneNumber) != null){
            kq = true;
        }else{
            kq = false;
        }
        return kq;
    }
    public CustomerEntity getCustomerByAccountName(String name){
        return customerRepo.findByAccount_UserName(name);
    }

    public CustomerEntity updateCustomer(CustomerEntity customer){
        CustomerEntity newCustomer = null;
        try{
            newCustomer = customerRepo.save(customer);
        }catch (Exception e){
            System.out.println(e);
        }
        return newCustomer;
    }

    public CustomerEntity addCustomer(CustomerEntity customer){
        CustomerEntity newCustomer;
        try {
            newCustomer = customerRepo.save(customer);
            return newCustomer;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public List<CustomerEntity> findByOrderByIdDescCustomer(){
        List<CustomerEntity> list;
        try {
            list = (List<CustomerEntity>) customerRepo.findByOrderByIdDesc();
            return list;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public List<CustomerEntity> findByCustomerNameLikeOrAddressLikeOrIdNumberLike(String name) {
        name = "%" + name + "%";
        return customerRepo.findByCustomerNameLikeOrAddressLikeOrIdNumberLike(name, name, name);
    }

    public CustomerEntity customerID(int id){
        return customerRepo.findById(id);
    }
}

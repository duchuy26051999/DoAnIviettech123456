/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Auction.service;

import com.example.Auction.entity.AccountEntity;
import com.example.Auction.entity.RoleEntity;
import com.example.Auction.repository.AccountRepository;
import com.example.Auction.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ldanh
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        AccountEntity account = accountRepository.findByUserName(userName);
        if (account == null) {
            System.out.println("User not found! " + userName);
            throw new UsernameNotFoundException(userName);
        }
        List<RoleEntity> roleNames = roleRepository.findByAccount_UserName(userName);
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (!CollectionUtils.isEmpty(roleNames)) {
            for (RoleEntity role : roleNames) {
                GrantedAuthority authority = new SimpleGrantedAuthority(role.getRole());
                grantList.add(authority);
            }
        }

        return (UserDetails) new User(account.getUserName(), account.getPassWord(), grantList);
    }

}

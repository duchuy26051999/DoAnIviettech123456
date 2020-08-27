package com.example.Auction.repository;

import com.example.Auction.entity.AccountEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity, Integer> {
    AccountEntity findByUserNameAndPassWord(String username, String password);
    AccountEntity findByUserName(String username);
    AccountEntity findById(int id);

    List<AccountEntity> findByOrderByIdDesc();

    @Query(value = "select * from accounts where user_Name like ?1", nativeQuery = true)
    List<AccountEntity> searchAccountName(String name);
}

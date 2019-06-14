package com.sparksys.common.dao;

import com.sparksys.common.entity.Account;
import com.sparksys.common.entity.Buy;
import com.sparksys.common.entity.Vegetables;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BuyDao {



	public List<Buy> findByName(Map<String, Object> map);



    int findBuyCount(Map<String, Object> map);

    public List<Account> findAccountByName(Map<String, Object> map);

    int findAccountCount(Map<String, Object> map);

    List<Account> findAccountShopByName(Map<String, Object> map);

    int findAccountShopCount(Map<String, Object> map);

    List<Account> findAllAccountShopByName(Map<String, Object> map);

    List<Account> findAllAccountByName(Map<String, Object> map);
}
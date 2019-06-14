package com.sparksys.common.dao;

import com.sparksys.common.entity.Shop;
import com.sparksys.common.entity.Vegetables;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ShopDao {


    public List<Shop> findAll();

	public List<Shop> findByName(Map<String, Object> map);

	public int findShopCount(Map<String, Object> map);

	public int saveShop(Shop shop);

    Shop findById(Integer id);

    int updateShop(Shop shop);

    int deleteShop(Integer id);
}
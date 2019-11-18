package com.sparksys.common.dao;

import com.sparksys.common.entity.Plant;
import com.sparksys.common.entity.Shop;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PlantDao {


    public List<Plant> findAll();

	public List<Plant> findByName(Map<String, Object> map);

	public int findShopCount(Map<String, Object> map);

	public int saveShop(Plant shop);

    Plant findById(Integer id);

    int updateShop(Plant shop);

    int deleteShop(Integer id);
}
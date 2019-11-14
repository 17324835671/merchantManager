package com.sparksys.common.dao;

import com.sparksys.common.entity.Goods;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface GoodsDao {

    public List<Goods> findAll();

	public List<Goods> findByName(Map<String, Object> map);

	public int findVegetablesCount(Map<String, Object> map);

	public int saveGoods(Goods vegetables);

    Goods findById(Integer id);

    int updateGoods(Goods vegetables);

    int deleteGoods(Integer id);
}
package com.sparksys.common.dao;

import com.sparksys.common.entity.MstD002Material;
import com.sparksys.common.entity.Vegetables;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface VegetablesDao {

    public List<Vegetables> findAll();

	public List<Vegetables> findByName(Map<String, Object> map);

	public int findVegetablesCount(Map<String, Object> map);

	public int saveGoods(Vegetables vegetables);

    Vegetables findById(Integer id);

    int updateGoods(Vegetables vegetables);

    int deleteGoods(Integer id);
}
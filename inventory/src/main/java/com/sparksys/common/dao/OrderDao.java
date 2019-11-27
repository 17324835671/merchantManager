package com.sparksys.common.dao;

import com.sparksys.common.entity.Order;
import com.sparksys.common.entity.Shop;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface OrderDao {


	public List<Order> findByName(Map<String, Object> map);

	public int findOrderCount(Map<String, Object> map);

	public Integer saveOrder(Order order);

    public Integer updateOrder(Order order);

    Order findById(Integer id);

    Order findByShopId(Integer shopId);

    void deleteById(Integer id);
}
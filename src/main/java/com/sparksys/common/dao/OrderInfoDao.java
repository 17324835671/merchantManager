package com.sparksys.common.dao;

import com.sparksys.common.entity.Order;
import com.sparksys.common.entity.OrderInfo;
import com.sparksys.common.entity.Shop;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface OrderInfoDao {


	public List<Order> findByName(Map<String, Object> map);

	public int findOrderCount(Map<String, Object> map);

    void saveOrderInfo(OrderInfo orderInfo);

    List<OrderInfo> findByOrderId(Integer integer);

    void deleteByOrderId(Integer id);
}
package com.sparksys.inventory.goods;

import com.github.pagehelper.PageHelper;
import com.sparksys.common.dao.OrderDao;
import com.sparksys.common.dao.OrderInfoDao;
import com.sparksys.common.dao.ShopDao;
import com.sparksys.common.entity.Order;
import com.sparksys.common.entity.OrderInfo;
import com.sparksys.common.entity.PageBean;
import com.sparksys.common.entity.Shop;
import com.sparksys.common.utils.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    @Resource
    OrderDao orderDao;

    @Resource
    OrderInfoDao orderInfoDao;

    public PageBean<Order> findByName(int currentPage, int pageSize, Map<String, Object> map) {
        PageHelper.startPage(currentPage, pageSize);
        List<Order> list = orderDao.findByName(map);
        int totalCount = orderDao.findOrderCount(map);
        PageBean<Order> pageData = new PageBean<>(currentPage, pageSize, totalCount);
        pageData.setItems(list);
        return pageData;
    }

    public void saveOrder(Order order) {
        order.setTimeDesc(DateUtil.getDateAsYYMMDD(new Date()));
        orderDao.saveOrder(order);
        if(order.getShopId()!=null){

        }
        List<OrderInfo> orderInfos=order.getOrderInfo();
        for (OrderInfo orderInfo:orderInfos){
            orderInfo.setOrderId(order.getId());
            orderInfoDao.saveOrderInfo(orderInfo);
        }
    }

    public void updateOrder(Order order) {
        orderInfoDao.deleteByOrderId(order.getId());
        List<OrderInfo> orderInfos=order.getOrderInfo();
        for (OrderInfo orderInfo:orderInfos){
            orderInfo.setOrderId(order.getId());
            orderInfoDao.saveOrderInfo(orderInfo);
        }
    }

    public void deleteOrder(Integer orderId) {
        orderInfoDao.deleteByOrderId(orderId);
        orderDao.deleteById(orderId);
    }
}

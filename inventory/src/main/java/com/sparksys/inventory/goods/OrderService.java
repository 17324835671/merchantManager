package com.sparksys.inventory.goods;

import com.github.pagehelper.PageHelper;
import com.sparksys.common.dao.OrderDao;
import com.sparksys.common.dao.OrderInfoDao;
import com.sparksys.common.dao.StockDao;
import com.sparksys.common.entity.Order;
import com.sparksys.common.entity.OrderInfo;
import com.sparksys.common.entity.PageBean;
import com.sparksys.common.entity.Stock;
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

    @Resource
    ComService comService;


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

        List<OrderInfo> orderInfos=order.getOrderInfo();
        for (OrderInfo orderInfo:orderInfos){
            orderInfo.setOrderId(order.getId());
            orderInfoDao.saveOrderInfo(orderInfo);
            Stock stock=new Stock();
            stock.setProId(orderInfo.getGoodsId());
            stock.setAmount(orderInfo.getAmount());
            comService.updateStock(stock);
        }
    }

    public void updateOrder(Order order) {
        orderDao.updateOrder(order);

        //先查询编辑前的订单把库存回滚
        List<OrderInfo> orderInfoList = orderInfoDao.findByOrderId(Integer.valueOf(order.getId()));
        for (OrderInfo orderInfo:orderInfoList){
            Stock stock=new Stock();
            stock.setProId(orderInfo.getGoodsId());
            stock.setAmount(-orderInfo.getAmount());
            comService.updateStock(stock);
        }
        orderInfoDao.deleteByOrderId(order.getId());
        List<OrderInfo> orderInfos=order.getOrderInfo();
        for (OrderInfo orderInfo:orderInfos){
            orderInfo.setOrderId(order.getId());
            orderInfoDao.saveOrderInfo(orderInfo);
            Stock stock=new Stock();
            stock.setProId(orderInfo.getGoodsId());
            stock.setAmount(orderInfo.getAmount());
            comService.updateStock(stock);
        }
    }

    public void deleteOrder(Integer orderId) {
        orderInfoDao.deleteByOrderId(orderId);
        orderDao.deleteById(orderId);
    }


}

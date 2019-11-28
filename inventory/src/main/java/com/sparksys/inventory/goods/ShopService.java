package com.sparksys.inventory.goods;

import com.github.pagehelper.PageHelper;
import com.sparksys.common.dao.OrderDao;
import com.sparksys.common.dao.ShopDao;
import com.sparksys.common.entity.Order;
import com.sparksys.common.entity.PageBean;
import com.sparksys.common.entity.Shop;
import com.sparksys.common.entity.Vegetables;
import com.sparksys.common.utils.CheckUtil;
import org.mvel2.ast.Or;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ShopService {
    @Resource
    private ShopDao shopDao;

    @Resource
    private OrderDao orderDao;

    public PageBean<Shop> findByName(int currentPage, int pageSize, Map<String, Object> map) {
        PageHelper.startPage(currentPage, pageSize);
        List<Shop> list = shopDao.findByName(map);
        for (Shop shop : list) {
            Order order=orderDao.findByShopId(shop.getId());
            if(CheckUtil.isEmpty(order)){
                shop.setTotalPrice(0.0);
            }else {
                shop.setTotalPrice(order.getTotalPrice());
            }
        }
        int totalCount = shopDao.findShopCount(map);
        PageBean<Shop> pageData = new PageBean<>(currentPage, pageSize, totalCount);
        pageData.setItems(list);
        return pageData;
    }
}

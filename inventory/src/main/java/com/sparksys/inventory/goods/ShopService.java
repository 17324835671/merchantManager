package com.sparksys.inventory.goods;

import com.github.pagehelper.PageHelper;
import com.sparksys.common.dao.ShopDao;
import com.sparksys.common.entity.PageBean;
import com.sparksys.common.entity.Shop;
import com.sparksys.common.entity.Vegetables;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ShopService {
    @Resource
    ShopDao shopDao;

    public PageBean<Shop> findByName(int currentPage, int pageSize, Map<String, Object> map) {
        PageHelper.startPage(currentPage, pageSize);
        List<Shop> list = shopDao.findByName(map);
        int totalCount = shopDao.findShopCount(map);
        PageBean<Shop> pageData = new PageBean<>(currentPage, pageSize, totalCount);
        pageData.setItems(list);
        return pageData;
    }
}

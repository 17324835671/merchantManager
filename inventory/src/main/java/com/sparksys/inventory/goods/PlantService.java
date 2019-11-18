package com.sparksys.inventory.goods;

import com.github.pagehelper.PageHelper;
import com.sparksys.common.dao.PlantDao;
import com.sparksys.common.dao.ShopDao;
import com.sparksys.common.entity.PageBean;
import com.sparksys.common.entity.Plant;
import com.sparksys.common.entity.Shop;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class PlantService {
    @Resource
    PlantDao plantDao;

    public PageBean<Plant> findByName(int currentPage, int pageSize, Map<String, Object> map) {
        PageHelper.startPage(currentPage, pageSize);
        List<Plant> list = plantDao.findByName(map);
        int totalCount = plantDao.findShopCount(map);
        PageBean<Plant> pageData = new PageBean<>(currentPage, pageSize, totalCount);
        pageData.setItems(list);
        return pageData;
    }
}

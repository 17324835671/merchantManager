package com.sparksys.inventory.goods;

import com.github.pagehelper.PageHelper;
import com.sparksys.common.dao.GoodsDao;
import com.sparksys.common.entity.Goods;
import com.sparksys.common.entity.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class StockService {

    @Resource
    private GoodsDao vegetablesDao;

    public PageBean<Goods> findByName(int currentPage, int pageSize, Map<String, Object> map) {
        PageHelper.startPage(currentPage, pageSize);
        List<Goods> list = vegetablesDao.findByName(map);
        int totalCount = vegetablesDao.findVegetablesCount(map);
        PageBean<Goods> pageData = new PageBean<>(currentPage, pageSize, totalCount);
        pageData.setItems(list);
        return pageData;
    }

    public Goods findGoodsById(Integer id) {
        return vegetablesDao.findById(id);
    }
}

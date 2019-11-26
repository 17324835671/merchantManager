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
public class GoodService {

    @Resource
    private GoodsDao goodsDao;

    public PageBean<Goods> findByName(int currentPage, int pageSize, Map<String, Object> map) {
        PageHelper.startPage(currentPage, pageSize);
        List<Goods> list = goodsDao.findByName(map);
        int totalCount = goodsDao.findVegetablesCount(map);
        PageBean<Goods> pageData = new PageBean<>(currentPage, pageSize, totalCount);
        pageData.setItems(list);
        return pageData;
    }

    public Goods findGoodsById(Integer id) {
        return goodsDao.findById(id);
    }
}

package com.sparksys.inventory.goods;

import com.github.pagehelper.PageHelper;
import com.sparksys.common.dao.VegetablesDao;
import com.sparksys.common.entity.MstD002Material;
import com.sparksys.common.entity.PageBean;
import com.sparksys.common.entity.Vegetables;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class GoodService {

    @Resource
    private VegetablesDao vegetablesDao;

    public PageBean<Vegetables> findByName(int currentPage, int pageSize, Map<String, Object> map) {
        PageHelper.startPage(currentPage, pageSize);
        List<Vegetables> list = vegetablesDao.findByName(map);
        int totalCount = vegetablesDao.findVegetablesCount(map);
        PageBean<Vegetables> pageData = new PageBean<>(currentPage, pageSize, totalCount);
        pageData.setItems(list);
        return pageData;
    }

    public Vegetables findGoodsById(Integer id) {
        return vegetablesDao.findById(id);
    }
}

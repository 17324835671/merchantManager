package com.sparksys.inventory.goods;

import com.github.pagehelper.PageHelper;
import com.sparksys.common.dao.BuyDao;
import com.sparksys.common.dao.VegetablesDao;
import com.sparksys.common.entity.Account;
import com.sparksys.common.entity.Buy;
import com.sparksys.common.entity.PageBean;
import com.sparksys.common.entity.Vegetables;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class BuyService {

    @Resource
    private BuyDao buyDao;

    public PageBean<Buy> findByName(int currentPage, int pageSize, Map<String, Object> map) {
        PageHelper.startPage(currentPage, pageSize);
        List<Buy> list = buyDao.findByName(map);
        int totalCount = buyDao.findBuyCount(map);
        PageBean<Buy> pageData = new PageBean<>(currentPage, pageSize, totalCount);
        pageData.setItems(list);
        return pageData;
    }

    public PageBean<Account> findAccountByName(int currentPage, int pageSize, Map<String, Object> map) {
        PageHelper.startPage(currentPage, pageSize);
        List<Account> list = buyDao.findAccountByName(map);
        int totalCount = buyDao.findAccountCount(map);
        PageBean<Account> pageData = new PageBean<>(currentPage, pageSize, totalCount);
        pageData.setItems(list);
        return pageData;
    }

    public PageBean<Account> findAccountShopByName(int currentPage, int pageSize, Map<String, Object> map) {
        PageHelper.startPage(currentPage, pageSize);
        List<Account> list = buyDao.findAccountShopByName(map);
        int totalCount = buyDao.findAccountShopCount(map);
        PageBean<Account> pageData = new PageBean<>(currentPage, pageSize, totalCount);
        pageData.setItems(list);
        return pageData;
    }

    public List<Account> findAllAccountShopByName(Map<String, Object> map) {
        return buyDao.findAllAccountShopByName(map);
    }

    public List<Account> findAllAccountByName(Map<String, Object> map) {
        return buyDao.findAllAccountByName(map);
    }
}

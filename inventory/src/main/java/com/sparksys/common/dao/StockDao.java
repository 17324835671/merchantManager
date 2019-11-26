package com.sparksys.common.dao;

import com.sparksys.common.entity.Order;
import com.sparksys.common.entity.Stock;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface StockDao {


    public void updateStockByProId(Stock stock);

    public Integer saveStock(Stock stock);

    int deleteStock(Integer id);
}
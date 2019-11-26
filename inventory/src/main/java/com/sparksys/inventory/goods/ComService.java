package com.sparksys.inventory.goods;

import com.sparksys.common.dao.StockDao;
import com.sparksys.common.entity.Stock;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ComService {


    @Resource
    StockDao stockDao;

    /**
     * 更新库存
     */
    public void updateStock(Stock stock){
        stockDao.updateStockByProId(stock);
    }

    public int saveStock(Integer amount,Integer proId){
        Stock stock=new Stock();
        stock.setProId(proId);
        stock.setStock(amount);
       return stockDao.saveStock(stock);
    }

    public void deleteStock(int proId){
        stockDao.deleteStock(proId);
    }

}

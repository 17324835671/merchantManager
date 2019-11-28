package com.sparksys.inventory.goods;

import com.sparksys.common.controller.CommonController;
import com.sparksys.common.dao.GoodsDao;
import com.sparksys.common.entity.Goods;
import com.sparksys.common.entity.PageBean;
import com.sparksys.common.entity.Stock;
import com.sparksys.common.entity.Vegetables;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
@Controller
public class GoodsController extends CommonController {

    @Resource
    private GoodService goodService;

    @Resource
    private GoodsDao goodsDao;

    @Resource
    private ComService comService;


    @RequestMapping(value ="/goodsSearch",method = RequestMethod.GET)
    public String goodsSearch(){
        return "goods/goodsSearch";
    }
    @RequestMapping(value ="/findGoodsList")
    public String findGoodsList() throws Exception {
        int pageNum = 0;
        String currentNum = this.getRequest().getParameter("currentNum");
        String name = this.getRequest().getParameter("name");
        if (currentNum!=null&&!"".equals(currentNum)) {
            pageNum=Integer.valueOf(currentNum);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        PageBean<Goods> list = goodService.findByName(pageNum, 10, map);
        this.getRequest().setAttribute("goods", list.getItems());
        this.getRequest().setAttribute("pageTag", list);
        return "goods/goodsList";
    }

    /**
     * 保存菜品
     * @throws Exception
     */
    @RequestMapping(value ="/saveGoods")
    @ResponseBody
    public void saveGoods(){
        String name= this.getRequest().getParameter("name");
        String proNo = this.getRequest().getParameter("proNo");
        String color = this.getRequest().getParameter("color");
        String stock = this.getRequest().getParameter("stock");
        String buyPrize = this.getRequest().getParameter("buyPrize");
        String salePrize = this.getRequest().getParameter("salePrize");
        String remark = this.getRequest().getParameter("remark");
        try {
            Goods goods=new Goods();
            goods.setName(name);
            goods.setProNo(proNo);
            goods.setColor(color);
            goods.setRemark(remark);
            if(stock==null||stock==""){
                goods.setStock(0);
            }else {
                goods.setStock(Integer.valueOf(stock));
            }

            if(buyPrize==null||buyPrize==""){
                goods.setBuyPrize(null);
            }else {
                goods.setBuyPrize(Double.valueOf(buyPrize));
            }
            if(salePrize==null||salePrize==""){
                goods.setSalePrize(null);
            }else {
                goods.setSalePrize(Double.valueOf(salePrize));
            }
            goodsDao.saveGoods(goods);
           comService.saveStock(goods.getStock(),goods.getId());
            this.success("新增商品成功",null);
        }catch (DuplicateKeyException d) {
            this.error("商品编号重复，请确认",null);
        } catch (Exception e) {
            e.printStackTrace();
            this.error("新增菜品失败",null);
        }
    }

    /**
     * 新增菜品转发
     * @return
     * @throws Exception
     */
    @RequestMapping(value ="/addGoodsForworrd")
    public String addGoodsForworrd(){
        return "goods/goodsAdd";
    }
    /**
     * 编辑菜品转发
     * @return
     * @throws Exception
     */
    @RequestMapping(value ="/updateGoodsForword",method = RequestMethod.GET)
    public String updateGoodsForword(){
        String id= this.getRequest().getParameter("id");
        Goods goods=goodService.findGoodsById(Integer.valueOf(id));
        this.getRequest().setAttribute("goods", goods);
        return "goods/goodsUpdate";
    }

    /**
     * 编辑菜品
     * @throws Exception
     */
    @RequestMapping(value ="/updateGoods")
    @ResponseBody
    public void updateGoods(){
        String id= this.getRequest().getParameter("id");
        String name= this.getRequest().getParameter("name");
        String proNo = this.getRequest().getParameter("proNo");
        String color = this.getRequest().getParameter("color");
        String buyPrize = this.getRequest().getParameter("buyPrize");
        String salePrize = this.getRequest().getParameter("salePrize");
        String stock = this.getRequest().getParameter("stock");
        String remark = this.getRequest().getParameter("remark");
        try {
            Goods goods=new Goods();
            goods.setId(Integer.valueOf(id));
            goods.setName(name);
            goods.setProNo(proNo);
            goods.setColor(color);
            goods.setRemark(remark);
            if(stock==null||stock==""){
                goods.setStock(0);
            }else {
                goods.setStock(Integer.valueOf(stock));
            }
            if(buyPrize==null||buyPrize==""){
                goods.setBuyPrize(null);
            }else {
                goods.setBuyPrize(Double.valueOf(buyPrize));
            }
            if(salePrize==null||salePrize==""){
                goods.setSalePrize(null);
            }else {
                goods.setSalePrize(Double.valueOf(salePrize));
            }
            goodsDao.updateGoods(goods);
            Stock stock1=new Stock();
            stock1.setStock(goods.getStock());
            stock1.setProId(goods.getId());
            comService.updateStock(stock1);
            this.success("编辑商品品成功",null);
        } catch (DuplicateKeyException d) {
            this.error("商品编号重复，请确认",null);
        }catch (Exception e) {
            e.printStackTrace();
            this.error("编辑商品失败",null);
        }
    }

    /**
     * 删除商品
     * @throws Exception
     */
    @RequestMapping(value ="/deleteGoods")
    @ResponseBody
    public void deleteGoods(){
        String id= this.getRequest().getParameter("id");

        try {
            goodsDao.deleteGoods(Integer.valueOf(id));
            comService.deleteStock(Integer.valueOf(id));
            this.success("删除商品成功",null);
        } catch (Exception e) {
            e.printStackTrace();
            this.error("删除菜商失败",null);
        }
    }



}

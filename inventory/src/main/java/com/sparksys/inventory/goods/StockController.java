package com.sparksys.inventory.goods;

import com.sparksys.common.controller.CommonController;
import com.sparksys.common.dao.GoodsDao;
import com.sparksys.common.entity.Goods;
import com.sparksys.common.entity.PageBean;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
public class StockController extends CommonController {

    @Resource
    private GoodService goodService;

    @Resource
    private StockService stockService;

    @Resource
    private GoodsDao vegetablesDao;


    @RequestMapping(value ="/findStockSearch",method = RequestMethod.GET)
    public String vegetablesSearch(){
        return "vegetables/vegetablesSearch";
    }
    @RequestMapping(value ="/findStockList")
    public String findStockList() {
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
        return "vegetables/vegetablesList";
    }

    /**
     * 保存菜品
     * @throws Exception
     */
    @RequestMapping(value ="/saveVegetables")
    @ResponseBody
    public void saveVegetables(){
        String name= this.getRequest().getParameter("name");
        String prize= this.getRequest().getParameter("prize");
        try {
            Goods vegetables=new Goods();
            vegetables.setName(name);
           /* if(prize==null||prize==""){
                vegetables.setPrize(null);
            }else {
                vegetables.setPrize(Double.valueOf(prize));
            }*/
            vegetablesDao.saveGoods(vegetables);
            this.success("新增菜品成功",null);
        }catch (DuplicateKeyException d) {
            this.error("菜品名称重复，请确认",null);
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
    @RequestMapping(value ="/addVegetablesForworrd")
    public String addVegetablesForworrd(){
        return "vegetables/vegetablesAdd";
    }
    /**
     * 编辑菜品转发
     * @return
     * @throws Exception
     */
    @RequestMapping(value ="/updateVegetablesForword",method = RequestMethod.GET)
    public String updateVegetablesForword(){
        String id= this.getRequest().getParameter("id");
        Goods vegetables=goodService.findGoodsById(Integer.valueOf(id));
        this.getRequest().setAttribute("goods", vegetables);
        return "vegetables/vegetablesUpdate";
    }

    /**
     * 编辑菜品
     * @throws Exception
     */
    @RequestMapping(value ="/updateVegetables")
    @ResponseBody
    public void updateVegetables(){
        String id= this.getRequest().getParameter("id");
        String name= this.getRequest().getParameter("name");
        String prize= this.getRequest().getParameter("prize");
        try {
            Goods vegetables=new Goods();
            vegetables.setId(Integer.valueOf(id));
            vegetables.setName(name);
           /* if(prize==null||prize==""){
                vegetables.setPrize(null);
            }else {
                vegetables.setPrize(Double.valueOf(prize));
            }*/
            vegetablesDao.updateGoods(vegetables);
            this.success("编辑菜品成功",null);
        } catch (DuplicateKeyException d) {
            this.error("菜品名称重复，请确认",null);
        }catch (Exception e) {
            e.printStackTrace();
            this.error("编辑菜品失败",null);
        }
    }

    /**
     * 删除菜品
     * @throws Exception
     */
    @RequestMapping(value ="/deleteVegetables")
    @ResponseBody
    public void deleteVegetables(){
        String id= this.getRequest().getParameter("id");

        try {
            vegetablesDao.deleteGoods(Integer.valueOf(id));
            this.success("删除菜品成功",null);
        } catch (Exception e) {
            e.printStackTrace();
            this.error("删除菜品失败",null);
        }
    }



}

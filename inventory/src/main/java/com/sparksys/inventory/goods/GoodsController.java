package com.sparksys.inventory.goods;

import com.sparksys.common.controller.CommonController;
import com.sparksys.common.dao.VegetablesDao;
import com.sparksys.common.dao.VegetavleMapper;
import com.sparksys.common.entity.PageBean;
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
    private VegetablesDao vegetablesDao;


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
        PageBean<Vegetables> list = goodService.findByName(pageNum, 10, map);
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
        String prize= this.getRequest().getParameter("prize");
        try {
            Vegetables vegetables=new Vegetables();
            vegetables.setName(name);
            if(prize==null||prize==""){
                vegetables.setPrize(null);
            }else {
                vegetables.setPrize(Double.valueOf(prize));
            }
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
        Vegetables vegetables =null;
        vegetables=goodService.findGoodsById(Integer.valueOf(id));
        this.getRequest().setAttribute("goods", vegetables);
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
        String prize= this.getRequest().getParameter("prize");
        try {
            Vegetables vegetables=new Vegetables();
            vegetables.setId(Integer.valueOf(id));
            vegetables.setName(name);
            if(prize==null||prize==""){
                vegetables.setPrize(null);
            }else {
                vegetables.setPrize(Double.valueOf(prize));
            }
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
    @RequestMapping(value ="/deleteGoods")
    @ResponseBody
    public void deleteGoods(){
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

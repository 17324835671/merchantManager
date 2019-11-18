package com.sparksys.inventory.goods;

import com.sparksys.common.controller.CommonController;
import com.sparksys.common.dao.ShopDao;
import com.sparksys.common.entity.PageBean;
import com.sparksys.common.entity.Shop;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ShopController extends CommonController {

    @Resource
    ShopService shopService;

    @Resource
    ShopDao shopDao;

    @RequestMapping(value ="/shopSearch",method = RequestMethod.GET)
    public String shopSearch(){
        return "shop/shopSearch";
    }
    @RequestMapping(value ="/findShopList")
    public String findShopList() throws Exception {
        int pageNum = 0;
        String currentNum = this.getRequest().getParameter("currentNum");
        String name = this.getRequest().getParameter("name");
        if (currentNum!=null&&!"".equals(currentNum)) {
            pageNum=Integer.valueOf(currentNum);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        PageBean<Shop> list = shopService.findByName(pageNum, 10, map);
        this.getRequest().setAttribute("shops", list.getItems());
        this.getRequest().setAttribute("pageTag", list);
        return "shop/shopList";
    }

    /**
     * 保存菜品
     * @throws Exception
     */
    @RequestMapping(value ="/saveShop")
    @ResponseBody
    public void saveGoods(){
        String name= this.getRequest().getParameter("name");
        String address= this.getRequest().getParameter("address");
        String mobile= this.getRequest().getParameter("mobile");
        String card= this.getRequest().getParameter("card");
        try {
            Shop shop=new Shop();
            shop.setName(name);
            shop.setAddress(address);
            shop.setMobile(mobile);
            shop.setCard(card);
            shopDao.saveShop(shop);
            this.success("新增商家成功",null);
        }catch (DuplicateKeyException d) {
            this.error("商家名称重复，请确认",null);
        } catch (Exception e) {
            e.printStackTrace();
            this.error("新增商家失败",null);
        }
    }

    /**
     * 新增菜品转发
     * @return
     * @throws Exception
     */
    @RequestMapping(value ="/addShopForworrd")
    public String addShopForworrd(){
        return "shop/shopAdd";
    }
    /**
     * 编辑菜品转发
     * @return
     * @throws Exception
     */
    @RequestMapping(value ="/updateShopForword",method = RequestMethod.GET)
    public String updateShopForword(){
        String id= this.getRequest().getParameter("id");
        Shop shop =null;
        shop=shopDao.findById(Integer.valueOf(id));
        this.getRequest().setAttribute("shop", shop);
        return "shop/shopUpdate";
    }

    /**
     * 编辑商家
     * @throws Exception
     */
    @RequestMapping(value ="/updateShop")
    @ResponseBody
    public void updateShop(){
        String id= this.getRequest().getParameter("id");
        String name= this.getRequest().getParameter("name");
        String address= this.getRequest().getParameter("address");
        String mobile= this.getRequest().getParameter("mobile");
        String card= this.getRequest().getParameter("card");
        try {
            Shop shop=new Shop();
            shop.setId(Integer.valueOf(id));
            shop.setName(name);
            shop.setAddress(address);
            shop.setMobile(mobile);
            shop.setCard(card);
            shopDao.updateShop(shop);
            this.success("编辑商家成功",null);
        } catch (DuplicateKeyException d) {
            this.error("商家名称重复，请确认",null);
        }catch (Exception e) {
            e.printStackTrace();
            this.error("编辑商家失败",null);
        }
    }

    /**
     * 删除商家
     * @throws Exception
     */
    @RequestMapping(value ="/deleteShop")
    @ResponseBody
    public void deleteShop(){
        String id= this.getRequest().getParameter("id");

        try {
            shopDao.deleteShop(Integer.valueOf(id));
            this.success("删除商家成功",null);
        } catch (Exception e) {
            e.printStackTrace();
            this.error("删除商家失败",null);
        }
    }


}

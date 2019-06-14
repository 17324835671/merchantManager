package com.sparksys.inventory.goods;

import com.sparksys.common.controller.CommonController;
import com.sparksys.common.dao.OrderDao;
import com.sparksys.common.dao.OrderInfoDao;
import com.sparksys.common.dao.ShopDao;
import com.sparksys.common.dao.VegetablesDao;
import com.sparksys.common.entity.*;
import com.sparksys.common.utils.DateUtil;
import org.mvel2.ast.Or;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

@Controller
public class OrderController extends CommonController {

    @Resource
    OrderService orderService;

    @Resource
    ShopDao shopDao;

    @Resource
    private VegetablesDao vegetablesDao;

    @Resource
    private OrderDao orderDao;

    @Resource
    private OrderInfoDao orderInfoDao;

    @RequestMapping(value = "/orderSearch", method = RequestMethod.GET)
    public String orderSearch() {
        return "order/orderSearch";
    }

    @RequestMapping(value = "/findOrderList")
    public String findOrderList() throws Exception {
        int pageNum = 0;
        String currentNum = this.getRequest().getParameter("currentNum");
        String timeDesc = this.getRequest().getParameter("timeDesc");
        String name = this.getRequest().getParameter("shopName");
        if (currentNum != null && !"".equals(currentNum)) {
            pageNum = Integer.valueOf(currentNum);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("shopName", name);
        if(timeDesc==""||timeDesc==null){
            timeDesc=DateUtil.getDateAsYYMMDD(new Date());
        }
        map.put("timeDesc", timeDesc);
        PageBean<Order> list = orderService.findByName(pageNum, 10, map);
        this.getRequest().setAttribute("orders", list.getItems());
        this.getRequest().setAttribute("pageTag", list);
        return "order/orderList";
    }


    /**
     * 新增菜品转发
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addOrderForworrd")
    public String addOrderForworrd() {
        List<Shop> shopList = shopDao.findAll();
        List<Vegetables> vegetablesList = vegetablesDao.findAll();
        List<String> units = new ArrayList<>();
        units.add("斤");
        units.add("颗");
        units.add("袋");
        this.getRequest().setAttribute("shops", shopList);
        this.getRequest().setAttribute("vegetables", vegetablesList);
        this.getRequest().setAttribute("units", units);
        return "order/orderAdd";
    }

    /**
     * 保存菜品
     *
     * @throws Exception
     */
    @RequestMapping(value = "/saveOrder")
    @ResponseBody
    public void saveOrder(Order order) {

        try {
            order.setTimeDesc(DateUtil.getDateAsYYMMDD(new Date()));
            orderService.saveOrder(order);
            this.success("新增订单成功", null);
        } catch (DuplicateKeyException d) {
            d.printStackTrace();
            this.error("此订单今日已创建，无需再次添加", null);
        } catch (Exception e) {
            e.printStackTrace();
            this.error("新增订单失败", null);
        }
    }

    /**
     * 编辑菜品转发
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateOrderForword")
    public String updateOrderForworrd() {

        String orderId= this.getRequest().getParameter("id");

        List<OrderInfo> orderInfos=orderInfoDao.findByOrderId(Integer.valueOf(orderId));
        Order order=orderDao.findById(Integer.valueOf(orderId));
        List<Shop> shopList = shopDao.findAll();
        List<Vegetables> vegetablesList = vegetablesDao.findAll();
        List<String> units = new ArrayList<>();
        units.add("斤");
        units.add("颗");
        units.add("袋");
        this.getRequest().setAttribute("shops", shopList);
        this.getRequest().setAttribute("vegetables", vegetablesList);
        this.getRequest().setAttribute("units", units);
        this.getRequest().setAttribute("orderInfos", orderInfos);
        this.getRequest().setAttribute("order", order);
        return "order/orderUpdate";
    }

    /**
     * 编辑订单
     * @throws Exception
     */
    @RequestMapping(value ="/updateOrder")
    @ResponseBody
    public void updateOrder(Order order){

        try {
            orderService.updateOrder(order);
            this.success("编辑订单成功",null);
        } catch (Exception e) {
            e.printStackTrace();
            this.error("编辑订单失败",null);
        }
    }

    /**
     * 删除订单
     * @throws Exception
     */
    @RequestMapping(value ="/deleteOrder")
    @ResponseBody
    public void deleteOrder(){
        String id= this.getRequest().getParameter("id");

        try {
            orderService.deleteOrder(Integer.valueOf(id));
            this.success("删除订单成功",null);
        } catch (Exception e) {
            e.printStackTrace();
            this.error("删除订单失败",null);
        }
    }

}

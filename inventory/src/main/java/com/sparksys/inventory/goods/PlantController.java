package com.sparksys.inventory.goods;

import com.sparksys.common.controller.CommonController;
import com.sparksys.common.dao.PlantDao;
import com.sparksys.common.dao.ShopDao;
import com.sparksys.common.entity.PageBean;
import com.sparksys.common.entity.Plant;
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
public class PlantController extends CommonController {

    @Resource
    PlantService plantService;

    @Resource
    PlantDao plantDao;

    @RequestMapping(value = "/plantSearch", method = RequestMethod.GET)
    public String shopSearch() {
        return "plant/plantSearch";
    }

    @RequestMapping(value = "/findPlantList")
    public String findShopList() throws Exception {
        int pageNum = 0;
        String currentNum = this.getRequest().getParameter("currentNum");
        String name = this.getRequest().getParameter("name");
        if (currentNum != null && !"".equals(currentNum)) {
            pageNum = Integer.valueOf(currentNum);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        PageBean<Plant> list = plantService.findByName(pageNum, 10, map);
        this.getRequest().setAttribute("plants", list.getItems());
        this.getRequest().setAttribute("pageTag", list);
        return "plant/plantList";
    }

    /**
     * 保存工厂
     *
     * @throws Exception
     */
    @RequestMapping(value = "/savePlant")
    @ResponseBody
    public void saveGoods() {
        String name = this.getRequest().getParameter("name");
        String address = this.getRequest().getParameter("address");
        String mobile = this.getRequest().getParameter("mobile");
        try {
            Plant plant = new Plant();
            plant.setName(name);
            plant.setAddress(address);
            plant.setMobile(mobile);
            plantDao.saveShop(plant);
            this.success("新增工厂成功", null);
        } catch (DuplicateKeyException d) {
            this.error("工厂名称重复，请确认", null);
        } catch (Exception e) {
            e.printStackTrace();
            this.error("新增工厂失败", null);
        }
    }

    /**
     * 新增工厂转发
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addPlantForworrd")
    public String addShopForworrd() {
        return "plant/plantAdd";
    }

    /**
     * 编辑工厂转发
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updatePlantForword", method = RequestMethod.GET)
    public String updateShopForword() {
        String id = this.getRequest().getParameter("id");
        Plant plant = plantDao.findById(Integer.valueOf(id));
        this.getRequest().setAttribute("plant", plant);
        return "plant/plantUpdate";
    }

    /**
     * 编辑工厂
     *
     * @throws Exception
     */
    @RequestMapping(value = "/updatePlant")
    @ResponseBody
    public void updateShop() {
        String id = this.getRequest().getParameter("id");
        String name = this.getRequest().getParameter("name");
        String address = this.getRequest().getParameter("address");
        String mobile = this.getRequest().getParameter("mobile");
        try {
            Plant plant = new Plant();
            plant.setId(Integer.valueOf(id));
            plant.setName(name);
            plant.setAddress(address);
            plant.setMobile(mobile);
            plantDao.updateShop(plant);
            this.success("编辑工厂成功", null);
        } catch (DuplicateKeyException d) {
            this.error("商家工厂重复，请确认", null);
        } catch (Exception e) {
            e.printStackTrace();
            this.error("编辑工厂失败", null);
        }
    }

    /**
     * 删除工厂
     *
     * @throws Exception
     */
    @RequestMapping(value = "/deletePlant")
    @ResponseBody
    public void deleteShop() {
        String id = this.getRequest().getParameter("id");

        try {
            plantDao.deleteShop(Integer.valueOf(id));
            this.success("删除工厂成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            this.error("删除工厂失败", null);
        }
    }


}

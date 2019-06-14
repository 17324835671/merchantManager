package com.sparksys.inventory.goods;

import com.sparksys.common.controller.CommonController;
import com.sparksys.common.dao.VegetablesDao;
import com.sparksys.common.entity.Account;
import com.sparksys.common.entity.Buy;
import com.sparksys.common.entity.PageBean;
import com.sparksys.common.entity.Vegetables;
import com.sparksys.common.utils.DateUtil;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BuyController extends CommonController {

    @Resource
    BuyService buyService;

    @Resource
    PdfService pdfService;

    @RequestMapping(value ="/buySearch",method = RequestMethod.GET)
    public String goodsSearch(){
        return "buy/buySearch";
    }
    @RequestMapping(value ="/findBuyList")
    public String findBuyList() throws Exception {
        int pageNum = 0;
        String currentNum = this.getRequest().getParameter("currentNum");
        String name = this.getRequest().getParameter("name");
        String timeDesc = this.getRequest().getParameter("timeDesc");
        if (currentNum!=null&&!"".equals(currentNum)) {
            pageNum=Integer.valueOf(currentNum);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        if(timeDesc==""||timeDesc==null){
            timeDesc= DateUtil.getDateAsYYMMDD(new Date());
        }
        map.put("timeDesc", timeDesc);
        PageBean<Buy> list = buyService.findByName(pageNum, 10, map);
        this.getRequest().setAttribute("buys", list.getItems());
        this.getRequest().setAttribute("pageTag", list);
        return "buy/buyList";
    }

    @RequestMapping(value ="/accountShopSearch",method = RequestMethod.GET)
    public String accountShopSearch(){
        return "buy/accountShopSearch";
    }
    @RequestMapping(value ="/findAccountShopList")
    public String findAccountShopList() throws Exception {
        int pageNum = 0;
        String currentNum = this.getRequest().getParameter("currentNum");
        String name = this.getRequest().getParameter("name");
        String timeDesc = this.getRequest().getParameter("timeDesc");
        if (currentNum!=null&&!"".equals(currentNum)) {
            pageNum=Integer.valueOf(currentNum);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        if(timeDesc==""||timeDesc==null){
            timeDesc= DateUtil.getDateAsYYMMDD(new Date());
        }
        map.put("timeDesc", timeDesc);
        PageBean<Account> list = buyService.findAccountShopByName(pageNum, 10, map);

        List<Account> accounts=buyService.findAllAccountShopByName(map);
        double totalPrize=0.0;
        for (Account account:accounts){
            totalPrize+=account.getTotalPrize();
        }
        this.getRequest().setAttribute("totalPrize", totalPrize);
        this.getRequest().setAttribute("accountShops", list.getItems());
        this.getRequest().setAttribute("pageTag", list);
        return "buy/accountShopList";
    }

    @RequestMapping(value ="/accountSearch",method = RequestMethod.GET)
    public String accountSearch(){
        return "buy/accountSearch";
    }
    @RequestMapping(value ="/findAccountList")
    public String findAccountList() throws Exception {
        int pageNum = 0;
        String currentNum = this.getRequest().getParameter("currentNum");
        String name = this.getRequest().getParameter("name");
        String timeDesc = this.getRequest().getParameter("timeDesc");
        if (currentNum!=null&&!"".equals(currentNum)) {
            pageNum=Integer.valueOf(currentNum);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        if(timeDesc==""||timeDesc==null){
            timeDesc= DateUtil.getDateAsYYMMDD(new Date());
        }
        map.put("timeDesc", timeDesc);
        PageBean<Account> list = buyService.findAccountByName(pageNum, 10, map);
        List<Account> accounts=buyService.findAllAccountByName(map);
        double totalPrize=0.0;
        for (Account account:accounts){
            totalPrize+=account.getTotalPrize();
        }
        this.getRequest().setAttribute("totalPrize", totalPrize);
        this.getRequest().setAttribute("accounts", list.getItems());
        this.getRequest().setAttribute("pageTag", list);
        return "buy/accountList";
    }

    @RequestMapping(value ="/exportPDF")
    public void exportPDF(HttpServletResponse response) throws Exception {
        String timeDesc = this.getRequest().getParameter("timeDesc");
        pdfService.exportPDF(timeDesc, response );
    }


}

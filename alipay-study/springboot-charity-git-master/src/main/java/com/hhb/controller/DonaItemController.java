package com.hhb.controller;

import com.hhb.VO.dona_item;
import com.hhb.pojo.Dona_item;
import com.hhb.pojo.Donation;
import com.hhb.pojo.User;
import com.hhb.service.DonaItemService;
import com.hhb.service.DonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class DonaItemController {
    @Autowired
    DonaItemService donaItemService;
    @Autowired
    DonaService donaService;

    //参与募捐,修改募捐项目已筹金额 并且 添加一个募捐条款
    @RequestMapping("/donaItem/support")
    public String Item_support(HttpSession session, Model model,
                               @RequestParam("dona_id") int dona_id,@RequestParam("dona_money") float dona_money){
        //创建一个 dona_item 对象
        Dona_item dona_item = new Dona_item();
        //通过 session 拿到捐款人 id
        User loginUser = (User) session.getAttribute("loginUser");
        int user_id = loginUser.getUser_id();

        //格式化
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获得当前系统时间
        Date date = new Date(System.currentTimeMillis());
        //将日期Date转为字符串
        String time = formatter.format(date);

        dona_item.setDona_id(dona_id);
        dona_item.setUser_id(user_id);
        dona_item.setMoney(dona_money);
        dona_item.setTime(time);
        //向 dona_item 表中添加一个捐款条目
        int result1 = donaItemService.addItem(dona_item);

        //获取dona项目并修改 其中 已筹善款的数额
        Donation donation = donaService.queryByDonaid(dona_id);
        float dona_current = donation.getDona_current();
        dona_current+=dona_money;
        donation.setDona_current(dona_current);     //修改
        //访问数据库
        int result2 = donaService.updateById(donation);

        //跳转到提示页面（成功或者失败的提示页面）
        model.addAttribute("flag",result1&result2);
        model.addAttribute("msg","参与募捐");
        return "common/tips";
    }

    //查询自己参与贡献的项目条款
    @RequestMapping("/donaItem/myItem")
    public String myItem(HttpSession session,Model model){
        //通过 session 拿到自己的 id
        User loginUser = (User) session.getAttribute("loginUser");
        int user_id = loginUser.getUser_id();

        //查询出我的贡献
        List<dona_item> dona_items = donaItemService.myItem(user_id);
        model.addAttribute("items",dona_items);

        return "dona_item/myItem-list";
    }

    //查询所有的捐款条目
    @RequestMapping("/donaItem/allItem")
    public String allItem(Model model){


        return "dasda";
    }
}

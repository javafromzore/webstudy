package com.hhb.controller;

import com.hhb.pojo.Donation;
import com.hhb.VO.Donation02;
import com.hhb.pojo.User;
import com.hhb.service.DonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class DonaController {
    @Autowired
    DonaService donaService;

    //跳转到 donation-add.html
    @RequestMapping("/dona/addpage")
    public String addpage(){
        return "donation/donation-add";
    }


    //在donation表中添加一条数据
    @RequestMapping("/dona/add")
    public String add(@RequestParam("DonaName") String DonaName, @RequestParam("DonaDesc") String DonaDesc,
                      @RequestParam("DonaPrice") float DonaPrice, @RequestParam("dona_state") int dona_state,
                      @RequestParam("dona_current") float dona_current, HttpSession session, Model model){
        User loginUser = (User) session.getAttribute("loginUser");

        Donation dona = new Donation(0, DonaName, loginUser.getUser_id(), DonaDesc, dona_state, DonaPrice, dona_current);
        int result = donaService.addDonation(dona);

        //跳转到提示页面（成功或者失败的提示页面）
        model.addAttribute("flag",result);
        model.addAttribute("msg","发起求助");
        return "common/tips";
    }

    //通过user_id查询 发起的募捐
    @RequestMapping("/dona/myDona")
    public String myDona(HttpSession session,Model model){
        //通过session拿到当前用户的id
        User loginUser = (User) session.getAttribute("loginUser");
        int id = loginUser.getUser_id();
        //通过user_id查询募捐项目
        List<Donation> donas = donaService.queryById(id);
        model.addAttribute("donas",donas);
        return "donation/MyDonation";
    }

    //查询已经通过审核的所有募捐
    //审核状态为 1 的募捐
    @RequestMapping("/dona/queryState_1")
    public String queryState_1(Model model){
        //仅查询审核状态为 1 的 donation
        // 注意接收的实体类为 donation02
        List<Donation02> donas = donaService.queryState_1();
        model.addAttribute("donas",donas);


        for (Donation02 dona : donas) {
            System.out.println(dona);
        }

        return "donation/donation-list";
    }

    //根据 donation的 id查询donation
    @RequestMapping("/dona/{dona_id}")
    public String queryById(@PathVariable("dona_id") int dona_id,
                            @RequestParam("user_name") String user_name, Model model){
        //user_name是求助人的姓名
        System.out.println(user_name);

        Donation dona = donaService.queryByDonaid(dona_id);
        model.addAttribute("dona",dona);
        //user_name是求助人的姓名
        model.addAttribute("user_name",user_name);

        return "donation/donation-support";
    }

    //根据 donation的 id查询donation
    @RequestMapping("/dona/queryByDonaid")
    public String queryByDonaid(@RequestParam("user_name") String user_name, @RequestParam("dona_id") int dona_id,Model model){
        //user_name是求助人的姓名
        System.out.println(user_name);
        //通过dona_id 查询出 dona 的信息
        Donation dona = donaService.queryByDonaid(dona_id);
        model.addAttribute("dona",dona);
        //user_name是求助人的姓名
        model.addAttribute("user_name",user_name);

        return "donation/donation-support";
    }
}

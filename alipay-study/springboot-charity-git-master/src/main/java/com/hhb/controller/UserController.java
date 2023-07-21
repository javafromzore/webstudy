package com.hhb.controller;

import com.hhb.pojo.User;
import com.hhb.pojo.User_category;
import com.hhb.service.UserCateService;
import com.hhb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserCateService userCateService;

    //登陆
    @RequestMapping("/user/login")
    public String login(@RequestParam("account") String account, @RequestParam("password") String password,
                        @RequestParam("verify") String verify, HttpSession session){

        //判断验证码是否正确
        String code = (String)session.getAttribute("RANDOMVALIDATECODEKEY");
        if(!code.equals(verify)){   //验证码不正确
            session.setAttribute("error","验证码错误");
            return "redirect:/login.html";
        }

        User loginUser = userService.getLoginUser(account, password);
        if(loginUser!=null){//查询到此用户

            //放置用户session
            session.setAttribute("loginUser",loginUser);

            int user_type = loginUser.getUser_type();
            //通过判断身份跳转不同的index
            switch (user_type){
                //普通用户
                case 1:
                    return "redirect:/userIndex.html";
                case 2:
                    return "redirect:/managerIndex.html";   //管理员
                case 3:
                    return "redirect:/organizationIndex.html";  //慈善组织
                default:
                    return "redirect:/login.html";
            }
        }else { //登陆失败
            //设置登陆失败的消息
            session.setAttribute("error","用户名或密码错误");
            return "redirect:/login.html";
        }
    }

    //跳转登陆界面
    @RequestMapping("/user/toReg")
    public String toReg(){
        return "register";
    }


    //查询个人信息
    @RequestMapping("/user/info")
    public String info(HttpSession session,Model model){
        //通过user_type 查询角色表 得到角色名称
        User loginUser = (User) session.getAttribute("loginUser");
        //得到用户的type 的id
        int cate_id = loginUser.getUser_type();
        //通过type_id查询角色
        User_category Cate = userCateService.getCateById(cate_id);
        model.addAttribute("role",Cate.getCate_name());

        return "user/information";
    }

    //跳转修改个人信息的页面
    @RequestMapping("/user/modify_page")
    public String modify_page(){
        return "user/user-modify";
    }

    //提交修改个人信息
    @RequestMapping("/user/modify")
    public String modify(@RequestParam("name") String name, @RequestParam("gender") int gender,
                         @RequestParam("age") int age,@RequestParam("phone") String phone,HttpSession session,Model model){
        //从session中得到用户
        System.out.println("user_age = "+age);
        User loginUser = (User) session.getAttribute("loginUser");
        loginUser.setUser_name(name);
        loginUser.setUser_gender(gender);
        loginUser.setUser_age(age);
        loginUser.setUser_phone(phone);

        int flag = userService.modifyUser(loginUser);
        //跳转到提示页面（成功或者失败的提示页面）
        model.addAttribute("flag",flag);
        model.addAttribute("msg","修改个人信息");
        return "common/tips";
    }

    //注销登陆
    @RequestMapping("/user/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login.html";
    }

    //点击注册，进行验证码校验并注册用户
    @PostMapping("/user/enroll")
    public String enroll(@RequestParam("name") String name,@RequestParam("account") String account,
                         @RequestParam("pwd") String pwd,@RequestParam("gender") int gender,
                         @RequestParam("age") int age,@RequestParam("mobile") String mobile,
                         @RequestParam("code") String code,HttpSession session,Model model){
        //先验证 短信验证码
        //判断code是否与session中的code相同
        String session_code = (String) session.getAttribute("code");

        if(!StringUtils.isEmpty(session_code)){
            if(!session_code.equals(code)){
                //验证码错误
                model.addAttribute("error","验证码错误!");
                return "register";
            }else{
                //验证码正确，进行注册
                session.removeAttribute("code");
                User user = new User();
                user.setUser_name(name);
                user.setUser_account(account);
                user.setUser_password(pwd);
                user.setUser_type(1);
                user.setUser_gender(gender);
                user.setUser_age(age);
                user.setUser_phone(mobile);
                user.setUser_donate(0);

                int flag = userService.registerUser(user);
                if(flag==0){
                    model.addAttribute("error","注册失败！");
                    return "register";
                }else{
                    model.addAttribute("error","注册成功,请登录！");
                    return "login";
                }
            }
        }else{
            //session中的验证码为空,说明没有发送验证码
            model.addAttribute("error","请发送验证码!");
            return "register";
        }
    }
}

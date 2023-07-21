package com.hhb.controller;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
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
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class PayController {
    @Autowired
    DonaItemService donaItemService;
    @Autowired
    DonaService donaService;
    //appid
    private final String APP_ID = "2021000119625133";
    //应用私钥
    private final String APP_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCDA1Y0+IjB/z4sIhJA0sgEaNGXV9+8t0fUMhrbjHjW88tz8d7jR8ev1aFMmyDKy/cSnlsoNDLwx71GCLiNQENYTvqdcsrYLMvQkmeIYT1OWcPYSl65P0TQRDBhakVdAIXvnts7KJ/nCROGMy8qrBG8ilSlNaOkinFp9ylxBbgrxElIqWe1E0G2xSACrzfKx0vxrXvs0dhl26EqFsjffm6G8Q0KHy9YL/alkyiP+mWCdydhXuXgp1OPIqLe2dCkrJp1spOJ3Wfq9uyqJO5xrgx6tby3MVPLu/nJ2PtzXt62Kpcj/S9Xxxk9FXO1/tJR3picLQ5NEIo0LZf20/nmH+R9AgMBAAECggEAOBwhu6YFzDAozHX7I1Ai4eCSgyLba9ODK9oZrpXG2yimpg2cy/JeD2yLHGgD71bThmHT4LAacQzafzgU8knnjdaldvFG9raYw8cwGDStMPU471VKpEP4kMnjfpUpB9/FfQ6oDfokIaq9dehN9xSitAD6p5cFct7oG94DHai5a+ST6X+OfTv03oE2DuA7ejaJn6cDGSB4IYmZRuDGdicqmPmLEHt3Kq79+EPxBYKUcWI4zQPXlA06Kcgb8HrJntjC836svSUrewWaG32g5MlJB8e1Z+1yL75E6k6R4rBJiVW2MC5VW+p/nQiY3FLfRq4a8V+VJ9uXM1h7L8TbOHtCAQKBgQDCSljMB8VnLFMKHOrcHCZNtjnDHsRYJQYEgNhhvwx2xUIgZNhzpiDmUSwR4TOaqF+Eg5bLfC+sQboANXpP5YUyt/rqUWqQ1fmEV5US9cfiYOi5rfjETK0q6VwagYRjXe84e039Q+D8bGypY+T+uuzFcJjudfWTjBkvkOzwt57/4QKBgQCsn/BLJvO4zueqIF/A5lHdA0yWU81/prcWLlnTYY1cC+ywgOE9vLqG8TA+QNt/L9bSoRrXOoE6clhZLglGW/jM3oArIzK77+CAuDScPLo4+d8iDLvXy2Ax/tihmVzekCq86c0rouBaQaUNQh+0M0LNTR6pwB1FQBHwu2r3jFnoHQKBgQCnc7R8hOYaOhWHzcyN0KUirT+zb70By0cYcoQMGsD/TJvll4UGh9QrLSKydkonrZ5Fc7RvxKTB7hQFQhIc8lCOmgrkES+rjP/0lBejs7mG3sc14T9a0EsfhOkg3yffDIK1R3C8K9ipNjmg9LvU3/5ZXOYx//CcLk82s83C2j9rgQKBgQCNTcaW3eW3K7a6rhFzh5UPQzoX+7VFfqR0BbEFtN7Pr/spZ/X4YVqWRyvkaZbC/9frRnEAKSXLP/pN1b9F1tdMa/2iUgefi3XMJ/z4v0T4iqE58A57UEEH68JMVYuNq5kwVnnZ9FONzhpbRftJuIRAJZaqAVZ0b0PAOMoQpNSbiQKBgHtAnpTuE0QFby+7hDsnTz+qC9dyQQWH3cBOn4RQzo1DUxvyQpZjAy0Oqn/F5x6RGMQU6SrirdUQbGWcANOpp9/L3YGHUrUjlT5Ehx2nPO//yTZSTWKM+p6+XALn1DGZbTChnL/5aEZsg5R4f55wL6RYezRzhq+w4wMixTQDyFLZ";
    private final String CHARSET = "UTF-8";
    // 支付宝公钥
    private final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA3CPgueDLkfB66s9ZsEUwyUbmkRknTFVyuBG4PkKI93OTOVC457ijEKknRYi8eKYo4Wl+7z4/nRXYCxc9XBynqtnJzronKm9Wv+7sswJI6g3QnJUcawgsbMzgJY+3vZnyNI4zUkGsHsxW31VUpR+vRIBUfY4eln/wQwYKYCKza89AK/eFVBbJTiS2SdjFxWHoQDrEqXKQALv2YVqE7R+BZqTS5TkDerQI1l+Nq3m7oemztrlx+96iAR5KxYO+tTr1u3XQZmjtjlqbty50DmxRCgEqJKYEu6CD+r1vi+2SXOUKnCJzsE8vHojS+Vk5oGbZYnX6Esw2TVeiCkmQ814CBwIDAQAB";
    //这是沙箱接口路径,正式路径为https://openapi.alipay.com/gateway.do
    private final String GATEWAY_URL ="https://openapi.alipaydev.com/gateway.do";
    private final String FORMAT = "JSON";
    //签名方式
    private final String SIGN_TYPE = "RSA2";
    //支付宝异步通知路径,付款完毕后会异步调用本项目的方法,必须为公网地址
    private final String NOTIFY_URL = "http://127.0.0.1/notifyUrl";
    //支付宝同步通知路径,也就是当付款完毕后跳转本项目的页面,可以不是公网地址
    private final String RETURN_URL = "http://localhost:8081/returnUrl";

    //必须加ResponseBody注解
    @ResponseBody
    @RequestMapping("/pay/alipay")
    public String alipay(HttpSession session,Model model,
                         @RequestParam("dona_money") float dona_money,
                        @RequestParam("dona_id") int dona_id) throws AlipayApiException {
        //把dona_id项目id 放在session中
        session.setAttribute("dona_id",dona_id);

        //生成订单号（支付宝的要求？）
        String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String user = UUID.randomUUID().toString().replace("-","").toUpperCase();

        String OrderNum = time+user;

        //调用封装好的方法（给支付宝接口发送请求）
        return sendRequestToAlipay(OrderNum,dona_money,"捐款");
    }


    @RequestMapping("/returnUrl")
    public String returnUrlMethod(HttpServletRequest request,HttpSession session,Model model) throws AlipayApiException, UnsupportedEncodingException {
        System.out.println("=================================同步回调=====================================");
        // 获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        System.out.println("================查看参数"+params+"=============================");//查看参数都有哪些
        //验证签名（支付宝公钥）
        boolean signVerified = AlipaySignature.rsaCheckV1(params, ALIPAY_PUBLIC_KEY, CHARSET, SIGN_TYPE); // 调用SDK验证签名
        //验证签名通过
        if(signVerified){
            // 商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

            // 支付宝交易流水号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

            // 付款金额
            float dona_money = Float.parseFloat(new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8"));


            System.out.println("商户订单号="+out_trade_no);
            System.out.println("支付宝交易号="+trade_no);
            System.out.println("付款金额="+dona_money);


            //在这里编写自己的业务代码（对数据库的操作）
            //创建一个 dona_item 对象
            Dona_item dona_item = new Dona_item();
            //通过 session 拿到捐款人 id，项目id
            User loginUser = (User) session.getAttribute("loginUser");
            int dona_id = (int) session.getAttribute("dona_id");
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
            return "common/payTips";
        }else{
            //跳转到提示页面（成功或者失败的提示页面）
            model.addAttribute("flag",0);
            model.addAttribute("msg","参与募捐");
            return "common/payTips";
        }
    }

    /*
    参数1：订单号
    参数2：订单金额
    参数3：订单名称
     */
    private String sendRequestToAlipay(String outTradeNo,Float totalAmount,String subject) throws AlipayApiException {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL,APP_ID,APP_PRIVATE_KEY,FORMAT,CHARSET,ALIPAY_PUBLIC_KEY,SIGN_TYPE);
        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(RETURN_URL);
        alipayRequest.setNotifyUrl(NOTIFY_URL);

        //商品描述（可空）
        String body="11111";
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + outTradeNo + "\","
                        + "\"total_amount\":\"" + totalAmount + "\","
                        + "\"subject\":\"" + subject + "\","
                        + "\"body\":\"" + body + "\","
                        + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        return result;
    }
}

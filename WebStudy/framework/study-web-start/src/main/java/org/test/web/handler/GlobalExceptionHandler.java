package org.test.web.handler;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.test.common.model.vo.Result;

@RestControllerAdvice   //ControllerAdvice，专门用于增强Controller，因此无法处理拦截器异常
public class GlobalExceptionHandler {
    //ExceptionHandler拦截异常
    @ExceptionHandler(RuntimeException.class)
    public Result runtimeException(RuntimeException exception) {
        return Result.failed("抛出异常");
    }

    //ModelAttribute初始化全局数据
    @ModelAttribute
    public void modelAttribute(Model model) {
        model.addAttribute("testKey", "testValue");
        System.out.println("modeAttribute生效了");
    }

    //@InitBinder请求参数预处理
    @InitBinder
    public void processParam(WebDataBinder webDataBinder) {
    }
}
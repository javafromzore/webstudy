package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@WebFilter(urlPatterns = "/*", filterName = "filter2")
public class MyFilter implements Filter {
    //在过滤器创建时执行
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("MyFilter初始化了");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("MyFilter正在过滤");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    //在过滤器销毁时执行
    @Override
    public void destroy() {
        System.out.println("MyFilter销毁了");
    }
}

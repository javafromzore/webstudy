package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@WebFilter(urlPatterns = "/*", filterName = "filter1")
public class YouFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("YouFilter初始化了");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("YouFilter正在过滤");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    //在过滤器销毁时执行
    @Override
    public void destroy() {
        System.out.println("YouFilter销毁了");
    }
}

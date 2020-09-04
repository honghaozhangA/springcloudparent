package com.zhh.springcloud.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.http.protocol.RequestContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class MyZuulFilter extends ZuulFilter {

    Logger logger = LoggerFactory.getLogger(MyZuulFilter.class);

    // 过滤类型
    public String filterType() {
        // pre 在执行到微服务之前过滤
        // post 在执行到微服务之后过滤
        // route 
        // error 出错的时候过滤
        return "pre";
    }

    public int filterOrder() {
        return 0;
    }

    // 判断当前请求是否过滤
    // true过滤，false放行
    public boolean shouldFilter() {

        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest httpServletRequest = requestContext.getRequest();
        String str = httpServletRequest.getParameter("str");

        return "run".equals(str);
    }

    // 过滤执行的操作
    public Object run() throws ZuulException {
        logger.info("当前进行过滤，run执行了。。。");
        return null;
    }
}

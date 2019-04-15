package com.my.myshop.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.my.myshop.common.constants.Constants;
import com.my.myshop.common.resp.ApiResult;
import com.my.myshop.redis.JedisClient;
import com.my.myshop.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

/**
 * @Description:
 * @Author: aylfq5
 * @CreateDate: 2019/3/11 17:27
 * @Version: 1.0
 */
@Component
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private JedisClient jedisClient;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle被调用。。。");
        response.setCharacterEncoding("utf-8");

        ApiResult result = new ApiResult();
        result.setCode(Constants.NOT_LOGIN);
        result.setMessage("请重新登录");

        String x_auth_token = request.getHeader(Constants.X_AUTH_TOKEN);

            if (StringUtils.isBlank(x_auth_token)) {
                dealErrorReturn(response, result);
                return false;
            } else {
                //从springsession中获取user
                User ue = (User) request.getSession().getAttribute(Constants.REQUEST_TOKEN_KEY);
                if (ue == null) {
                    dealErrorReturn(response, result);
                    return false;
                }else {
                    //token不对
                    dealErrorReturn(response, result);
                }
            }

        //放行
        return true;
    }

    private void dealErrorReturn(HttpServletResponse response, ApiResult result) {
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(JSON.toJSONString(result));
        } catch (IOException ex) {
            log.info("IOException", ex);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle被调用了。。。");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion被调用了。。。");
    }
}

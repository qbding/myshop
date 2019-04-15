package com.my.myshop.exception;

import com.alibaba.fastjson.JSON;
import com.my.myshop.common.utils.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Description:
 * @Author: aylfq5
 * @CreateDate: 2019/3/7 17:00
 * @Version: 1.0
 */
@Aspect
@Component
@Slf4j
public class ExceptionAspect {

    @Resource
    private HttpServletRequest request;

    /**
     * 切入点
     * @param operation
     * 此处切入点为所有声明@Operation注解的方法
     */
    @Pointcut(value = "@annotation(operation)")
    public void serviceStatistics(Operation operation) {}

    /**
     * 前置通知
     * @param joinPoint
     * @param operation
     */
    @Before("serviceStatistics(operation)")
    public void doBefore(JoinPoint joinPoint, Operation operation) {
        // 获取切入点参数
        Map<String, Object> joinPointInfo = RequestUtils.getJoinPointInfoMap(joinPoint);
        // 设置请求信息
        StringBuilder requestInfo = new StringBuilder();
        requestInfo.append("RequestInfo: [ip:");
        requestInfo.append(RequestUtils.getRequestIp(request)).append(", classpath: ").append(joinPointInfo.get("classpath").toString())
                .append(", methodName: ").append(joinPointInfo.get("methodName")).append(", requestMethod:").append(request.getMethod())
                .append(", questParam: ").append(joinPointInfo.get("paramMap")).append(", url: ").append(request.getRequestURI())
                .append(", Operation: ").append(operation.value());
        log.info(requestInfo.toString());
    }
    /**
     * 返回通知
     * @param operation
     * @param returnValue
     */
    @AfterReturning(value = "serviceStatistics(operation)", returning = "returnValue")
    public void doAfterReturning(Operation operation, Object returnValue) {
        // 完善请求信息
        log.info(JSON.toJSONString(returnValue));
    }
    /**
     * 异常通知
     * @param operation
     * @param e
     */
    @AfterThrowing(value = "serviceStatistics(operation)", throwing = "e")
    public void doAfterThrowing(Operation operation, Throwable e) {
        // 设置异常信息
        String json = JSON.toJSONString(e);
        System.out.println(json);
        System.out.println("EXECEPTION MESSAGE:" + e.getMessage());
    }
}

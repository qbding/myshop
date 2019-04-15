package com.my.myshop.user.controller;

import com.alibaba.fastjson.JSON;
import com.my.myshop.common.constants.Constants;
import com.my.myshop.exception.MyShopException;
import com.my.myshop.common.resp.ApiResult;
import com.my.myshop.exception.Operation;
import com.my.myshop.redis.JedisClient;
import com.my.myshop.user.entity.User;
import com.my.myshop.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName UserController
 * @Description TODO
 * @Auther QingBin Ding
 * @Date 2019/4/3 13:47
 * @Version 1.0
 **/
@RequestMapping(value = "user")
@Controller
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JedisClient jedisClient;

    @RequestMapping(value = "goLogin")
    public String goLogin(ModelMap map, HttpServletRequest request) {
        String a = (String) request.getSession().getAttribute("userToken");
        System.out.println("token---------------"+a);
        request.getSession().setAttribute("userToken","1111111111111");
        return "login";
    }



    @RequestMapping(value = "login")
    @ResponseBody
    @Operation("登录")
    public ApiResult login( @RequestBody User user, BindingResult bindingResult, HttpServletRequest request) {
        log.info("-----------"+JSON.toJSONString(userService));
        if (bindingResult.hasErrors()) {
            String message = bindingResult.getFieldError().getDefaultMessage();
            System.out.println(message);
            throw new  MyShopException(Constants.PARAM_VALID_ERROR,message);
        }
//        user.setPhone("11111");
        ApiResult<User> resp = new ApiResult<>("登陆成功");
        UsernamePasswordToken token = new UsernamePasswordToken(user.getPhone(),user.getPassword(),false);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        if(subject.isAuthenticated()){
            resp.setData(null);
            resp.setMessage("success");
        }
        System.out.println(subject.hasRole("coder"));

//        User result = userService.login(user);
return resp;
//        request.getSession().setAttribute(Constants.REQUEST_TOKEN_KEY,result);
    }
    @RequestMapping(value = "test")
    @ResponseBody
    public ApiResult test(HttpSession session) {
        ApiResult result = new ApiResult();
        Map<String, Object> sessionIdPortMap = new HashMap<String, Object>();
        // 获取session信息
        sessionIdPortMap.put("sessionId：", session.getId());
        session.setAttribute("id","111111111111111111");
        result.setData(sessionIdPortMap);
        return result;
    }

    @RequestMapping(value = "test2")
    @ResponseBody
    public ApiResult test2(HttpSession session,String key) {
        ApiResult result = new ApiResult();
        Map<String, Object> sessionIdPortMap = new HashMap<String, Object>();
        // 获取session信息
        String s = JSON.toJSONString(jedisClient.hvals(key));
        result.setData(s);
        return result;
    }

    @RequestMapping(value = "test1")
    @ResponseBody
    public ApiResult test1(HttpSession session) {
        ApiResult result = new ApiResult();
        Map<String, Object> sessionIdPortMap = new HashMap<String, Object>();
        // 获取session信息
        sessionIdPortMap.put("sessionId：", session.getId());
        String s = (String) session.getAttribute("id");
        sessionIdPortMap.put("s",s);
        result.setData(sessionIdPortMap);
        return result;
    }

    //发送验证码
    @RequestMapping(value = "sendCode")
    @ResponseBody
    public void sendCode(ModelMap map) {
        userService.sendVercode("19955791681");
    }


    @RequestMapping(value = "index")
    public String go(ModelMap map) {
        User user = userService.getUserById(1L);
        map.put("user", user);
        return "index";
    }

}

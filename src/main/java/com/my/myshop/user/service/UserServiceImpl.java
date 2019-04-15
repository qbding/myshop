package com.my.myshop.user.service;

import com.alibaba.fastjson.JSON;
import com.my.myshop.common.constants.Constants;
import com.my.myshop.exception.MyShopException;
import com.my.myshop.common.utils.MD5Util;
import com.my.myshop.common.utils.RandomNumberCode;
import com.my.myshop.jms.SmsProcessor;
import com.my.myshop.user.dao.UserMapper;
import com.my.myshop.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Auther QingBin Ding
 * @Date 2019/4/3 14:43
 * @Version 1.0
 **/
@Service
@Slf4j
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SmsProcessor smsProcessor;

    private static final String SMS_QUEUE = "sms.queue";

    @Override
    public User getUserById(long l) {
        return userMapper.selectByPrimaryKey(1L);
    }

    @Override
    public void sendVercode(String s) {
        String verCode = RandomNumberCode.verCode();
        //验证码推送到队列
        Destination destination = new ActiveMQQueue(SMS_QUEUE);
        Map<String,String> smsParam = new HashMap<>();
        smsParam.put("mobile",s);
        smsParam.put("tplId", Constants.MDSMS_VERCODE_TPLID);
        smsParam.put("vercode",verCode);
        String message = JSON.toJSONString(smsParam);
        smsProcessor.sendSmsToQueue(destination,message);
    }

    @Override
    public User login(User user) {

        User result = null;
            User existUser = userMapper.selectByPhone(user.getPhone());
            if(existUser==null){
                throw new MyShopException(Constants.RESP_STATUS_BADREQUEST,"请注册");
            }else {
                    if (user.getPassword().equals(user.getPassword())) {
                        result = userMapper.selectByPrimaryKey(existUser.getId());
                    } else {
                        throw new MyShopException(Constants.RESP_STATUS_BADREQUEST,"用户名密码不匹配");
                    }

            }

        return result;
    }

    @Override
    public User selectUserByPhone(String phone) {
        return userMapper.selectByPhone(phone);
    }


    private String generateToken(User user)
            throws Exception {
        String source = user.getId() + ":" + user.getPhone() + System.currentTimeMillis();
        return MD5Util.getMD5(source);
    }
}

package com.my.myshop;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

/**
 * @ClassName ShiroTest1
 * @Description TODO
 * @Auther QingBin Ding
 * @Date 2019/4/13 9:17
 * @Version 1.0
 **/
public class ShiroTest1 {

    @Test
    public void testPermissionRealm() {
        Subject subject = login("anyUsername", "anyPassword");
        //使用断言判断用户是否已经登录
        Assert.assertTrue(subject.isAuthenticated());
        //---------登录结束------------

        //---------检查当前用户的角色信息------------
        System.out.println(subject.hasRole("coder"));
        //---------如果当前用户有此角色，无返回值。若没有此权限，则抛 UnauthorizedException------------
        subject.checkRole("coder");
        //---------检查当前用户的权限信息------------
        System.out.println(subject.isPermitted("code:insert"));
        //---------如果当前用户有此权限，无返回值。若没有此权限，则抛 UnauthorizedException------------
        subject.checkPermissions("code:insert", "code:update");
    }

    private Subject  login(String yourInputUsername, String yourInputPassword) {
            //读取配置文件，相当于在加载数据源
            Factory<SecurityManager> factory =
                    new IniSecurityManagerFactory("classpath:shiro_1.ini");
            //SecurityManager 是Shiro内部的底层实现，几乎所有功能都由其实现
            org.apache.shiro.mgt.SecurityManager sm = factory.getInstance();
            //SecurityUtils是一个工具，方便用户调用，它封装了SecurityManager
            SecurityUtils.setSecurityManager(sm);
            //生成一个SecurityManager的门面类，即Subject。
            Subject subject = SecurityUtils.getSubject();
            //封装用户的数据
            UsernamePasswordToken token = new UsernamePasswordToken("19955791681", "123456789");
            //Subject接收到的方法参数，最终将会传到SecurityManager中进行验证
            //将用户的数据token 最终传递到Realm中进行对比
            subject.login(token);
            //判断本帐号是否已经被认证
           return subject;
        }

    @Test
    public void generatePassword() {
        String password = "123456";
        String csdnName = "小大宇";
        Md5Hash md5Hash = new Md5Hash(password, csdnName, 3);
        //打印结果：e36e406088fcbe05c70ec1ab33bffa64
        System.out.println(md5Hash);
    }
}

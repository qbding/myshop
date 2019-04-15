package com.my.myshop.common.shiro;

import com.my.myshop.user.entity.User;
import com.my.myshop.user.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName MyShiroRealm
 * @Description TODO
 * @Auther QingBin Ding
 * @Date 2019/4/12 11:45
 * @Version 1.0
 **/
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;


    //自定义   密码比对
   /* public MyShiroRealm() {
        //采用md5算法
        HashedCredentialsMatcher passwordMatcher = new HashedCredentialsMatcher("md5");
        //循环加密3次
        passwordMatcher.setHashIterations(3);
        //再将这个加密组件注入到我们的Realm中
        this.setCredentialsMatcher(passwordMatcher);
    }

    @Override
    public String getName() {
        return "PasswordRealm";
    }


    private String getPasswordFromDB(String clientUsername) {
        //模拟从数据库查出来的密文
        return "e36e406088fcbe05c70ec1ab33bffa64";
    }

    private ByteSource generateSalt(String salt) {
        return ByteSource.Util.bytes(salt);
    }
*/
    // 认证登陆
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {
        //获取基于用户名和密码的令牌
        //实际上这个authcToken是从LoginController里面currentUser.
        // (token)传过来的
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String phone =  token.getUsername();
        User user = userService.selectUserByPhone(phone);//根据登陆名account从库中查询user对象
/*        User user= new User();
        user.setPhone("19955791681");
        user.setPassword("123456789");*/
        if(user==null){throw new AuthenticationException("用户不存在");}

        //进行认证，将正确数据给shiro处理
        //密码不用自己比对，AuthenticationInfo认证信息对象，一个接口，new他的实现类对象SimpleAuthenticationInfo
        /*	第一个参数随便放，可以放user对象，程序可在任意位置获取 放入的对象
         * 第二个参数必须放密码，
         * 第三个参数放 当前realm的名字，因为可能有多个realm*/

        AuthenticationInfo authcInfo=new SimpleAuthenticationInfo(user.getPhone(), user.getPassword(), this.getName());

        return authcInfo;//返回给安全管理器，securityManager，由securityManager比对数据库查询出的密码和页面提交的密码
        //如果有问题，向上抛异常，一直抛到控制器
        /*String clientUsername = (String) token.getPrincipal();
        String yourExpectedSalt = "小大宇";
        return new SimpleAuthenticationInfo(clientUsername, getPasswordFromDB(clientUsername),
                generateSalt(yourExpectedSalt), getName());*/

    }

    // 用户授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
//        User  user  = (User) principal.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
//            //从数据库加载当前用户的角色，权限
//        simpleAuthorInfo.addRole(getYourRoleByUsernameFromDB(""));
//        simpleAuthorInfo.addStringPermissions(getYourPermissionByUsernameFromDB(""));
//
//        System.out.println("经试验：并不是每次调用接口就会执行，而是调用需要操作码（permission）的接口就会执行");
        return simpleAuthorInfo;
    }

    private String getYourRoleByUsernameFromDB(String username) {
        return "coder";
    }

    private List<String> getYourPermissionByUsernameFromDB(String username) {
        return Arrays.asList("code:insert", "code:update");
    }

}

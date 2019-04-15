<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>后台登录-X-admin2.1</title>
    <#--<link rel="stylesheet" href="../static/x-admin/css/font.css">-->
	<link rel="stylesheet" href="../static/css/1.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <#--<script src="../static/x-admin/lib/layui/layui.js" charset="utf-8"></script>-->
    <#--<script type="text/javascript" src="../static/x-admin/js/xadmin.js"></script>-->
<#--    <script type="text/javascript" src="../static/x-admin/js/cookie.js"></script>-->

</head>
<body class="login-bg">
    <#--
    <div class="login layui-anim layui-anim-up">
        <div class="message">x-admin2.0-管理登录</div>
        <div id="darkbannerwrap"></div>
        
        <form method="post" class="layui-form" id="loginForm">
            <input name="username" placeholder="用户名"  type="text" lay-verify="required" class="layui-input" >
            <hr class="hr15">
            <input name="password" lay-verify="required" placeholder="密码"  type="password" class="layui-input">
            <hr class="hr15">
            <input value="登录" lay-submit lay-filter="login" style="width:100%;" type="submit">
            <hr class="hr20" >
        </form>
    </div>-->

    <div class="login layui-anim layui-anim-up">
        <div class="message">MyShop管理登录</div>
        <div id="darkbannerwrap"></div>

        <form method="post"id="loginForm">
            <input name="phone" placeholder="用户名"  type="text" lay-verify="required" id="phone">
            <hr class="hr15">
            <input name="password" lay-verify="required" placeholder="密码"  type="password" id="password">
            <hr class="hr15">
            <input value="登录"  style="width:100%;" type="button" id="submitForm">
            <hr class="hr20" >
        </form>
    </div>

    <script>
        $("#submitForm").click(function () {
            var s= {"phone":$("#phone").val(),"password":$("#password").val()};
            $.ajax({
                type: 'post',
                data: JSON.stringify(s),
                url: '/myShop/user/login',
                cache:false,
                contentType:'application/json',
                dataType:"json",
                success: function (data) {
                    console.log(data.code)
                    location.href='/myShop/user/index'
                    /* layer.msg(JSON.stringify(data.field),function(){
                         location.href='index.html'
                     });*/
                }
            });
        })
        
    </script>

    
    <!-- 底部结束 -->
    <script>
    //百度统计可去掉
    var _hmt = _hmt || [];
    (function() {
      var hm = document.createElement("script");
      hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
      var s = document.getElementsByTagName("script")[0]; 
      s.parentNode.insertBefore(hm, s);
    })();
    </script>
</body>
</html>
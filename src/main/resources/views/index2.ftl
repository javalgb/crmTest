<!DOCTYPE html>
<html lang="en">
  <!-- 
    Author: Minyoung
    CreateAt: 2021年10月14日23:44:45
    License: MIT
  -->
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Landing...</title>
  <script type="text/javascript">
    var ctx="${ctx}";
  </script>
  <script src="${ctx}/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
  <script src="${ctx}/js/lay-config.js" charset="utf-8"></script>
  <link rel="stylesheet" href="${ctx}/css/style.css">
  <script src="https://cdn.jsdelivr.net/gh/1999cyx/cdn@2.0/js/sakura.js"></script>
</head>
<body>
  <div class="MyContainer">
    <div class="form-warp">
      <form class="sign-in-form">
        <h2 class="form-title">登录</h2>
        <input name="logonUsername" placeholder="用户名" />
        <input name="logonPassword" type="password" placeholder="密码" />
        <div class="submit-btn" id="logonBtn">立即登录</div>
      </form>
      <form class="sign-up-form">
        <h2 class="form-title">注册</h2>
        <input name="loginUsername" placeholder="用户名" />
        <input name="loginPassword" type="password" placeholder="密码" />
        <input name="loginTrueName" placeholder="真实姓名" />
        <input name="loginPhone" placeholder="手机号" />
        <input name="loginEmail"  placeholder="邮箱" />
        <div class="submit-btn" id="loginBtn">立即注册</div>
      </form>
    </div>
    <div class="desc-warp">
      <div class="desc-warp-item sign-up-desc">
        <div class="content">
          <button id="sign-up-btn">注册</button>
        </div>
        <img src="${ctx}/images/log.svg" alt="">
      </div>
      <div class="desc-warp-item sign-in-desc">
        <div class="content">
          <button id="sign-in-btn">登录</button>
        </div>
        <img src="${ctx}/images/register.svg" alt="">
      </div>
    </div>
  </div>
  <script src="${ctx}/js/jquery-1.12.3.min.js" charset="utf-8"></script>
  <script>
    layui.use(['form','jquery','jquery_cookie'], function(){
      var form = layui.form,
              layer = layui.layer,
              $ = layui.jquery,
              $ = layui.jquery_cookie($);

      const singUpBtn = document.querySelector('#sign-up-btn')
      const singInBtn = document.querySelector('#sign-in-btn')
      const MyContainer = document.querySelector('.MyContainer')

      singUpBtn.addEventListener('click', () => {
        MyContainer.classList.add('sign-up-mode')
      })
      singInBtn.addEventListener('click', () => {
        MyContainer.classList.remove('sign-up-mode')
      })
      //登录
      $("#logonBtn").click(function (){
        var username=$("input[name='logonUsername']").val()
        var password=$("input[name='logonPassword']").val()
        console.log(username)
        console.log(password)
        $.post(ctx+"/user/logon",{
          userName:username,
          userPwd:password
        },function (data){
          if(data.code==200){
            layer.msg("登录成功",{icon: 1,time: 1000},function (){
              var result=data.result;
              $.cookie("userIdStr",result.userIdStr);
              $.cookie("userName",result.userName);
              $.cookie("trueName",result.trueName);
              $.cookie("userIdStr",result.userIdStr,{expires:7});
              $.cookie("userName",result.userName,{expires:7});
              $.cookie("trueName",result.trueName,{expires:7});

              window.location.href=ctx+"/main";
            })
          }else {
            layer.msg(data.msg,{icon: 2,time: 1000})
          }
        })
      })
      //注册
      $("#loginBtn").click(function (){
        var userName=$("input[name='loginUsername']").val()
        var trueName=$("input[name='loginTrueName']").val()
        var userPwd=$("input[name='loginPassword']").val()
        var phone=$("input[name='loginPhone']").val()
        var email=$("input[name='loginEmail']").val()
        console.log(userName)
        console.log(userPwd)
        $.post(ctx+"/user/login",{
          userName:userName,
          trueName:trueName,
          userPwd:userPwd,
          phone:phone,
          email:email,
        },function (data){
          if(data.code==200){
            layer.msg("注册成功",{icon: 1,time: 1000},function (){
              MyContainer.classList.remove('sign-up-mode')
            })
          }else {
            layer.msg(data.msg,{icon: 2,time: 1000})
          }
        })
      })


    });

  </script>
</body>
</html>
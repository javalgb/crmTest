layui.use(['form','jquery','jquery_cookie'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);

    /**
     * 用户登录 表单提交
     */
    form.on('submit(saveBtn)', function(data){
        // layer.msg(JSON.stringify(data.field));
        var filed=data.field;
        console.log(filed.old_password)
        console.log(filed.new_password)
        console.log(filed.again_password)
        $.post(ctx+"/user/changePwd",{
            oldPassword:filed.old_password,
            newPassword:filed.new_password,
            confirmPwd:filed.again_password
        },function (data){
            if(data.code==200){
                layer.msg("修改密码成功了，系统两秒钟后退出",{icon: 1,time: 2000},function (){
                    //清空Cookie
                    $.removeCookie("userIdStr",{domain:"localhost",path:"/crm"});
                    $.removeCookie("userName",{domain:"localhost",path:"/crm"});
                    $.removeCookie("trueName",{domain:"localhost",path:"/crm"});
                    //跳转页面
                    window.parent.location.href=ctx+"/index";
                });
            }else {
                layer.msg(data.msg)
            }
        })

        return false;
    });

});
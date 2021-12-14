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
        console.log(filed.userName)
        console.log(filed.phone)
        console.log(filed.email)
        console.log(filed.trueName)
        $.post(ctx+"/user/setting",{
            userName:data.field.userName,
            phone:data.field.phone,
            email:data.field.email,
            trueName:data.field.trueName,
            id:data.field.id
        },function (data){
            if(data.code==200){
                layer.msg("修改成功了",{icon: 1,time: 1000},function(){
                    //清空Cookie
                    $.removeCookie("userIdStr",{domain:"localhost",path:"/crm"});
                    $.removeCookie("userName",{domain:"localhost",path:"/crm"});
                    $.removeCookie("trueName",{domain:"localhost",path:"/crm"});
                    //页面跳转
                    window.parent.location.href=ctx+"/index";
                });
            }else{
                //修改失败了提示
                layer.msg(data.msg);
            }
        })

        return false;
    });

});
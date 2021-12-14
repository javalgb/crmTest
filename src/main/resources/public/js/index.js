layui.use(['form','jquery','jquery_cookie'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);

    /**
     * 用户登录 表单提交
     */
    form.on('submit(login)', function(data){
        // layer.msg(JSON.stringify(data.field));
        var filed=data.field;
        console.log(filed)
        $.post(ctx+"/user/login",{
            userName:filed.username,
            userPwd:filed.password
        },function (data){
            if(data.code==200){
                layer.msg("登录成功",{icon: 1,time: 1000},function (){
                    var result=data.result;
                    $.cookie("userIdStr",result.userIdStr);
                    $.cookie("userName",result.userName);
                    $.cookie("trueName",result.trueName);
                    if($("#rememberMe").prop("checked")==true){
                        $.cookie("userIdStr",result.userIdStr,{expires:7});
                        $.cookie("userName",result.userName,{expires:7});
                        $.cookie("trueName",result.trueName,{expires:7});
                    }

                    window.location.href=ctx+"/main";
                })
            }else {
                layer.msg(data.msg)
            }
        })

        return false;
    });

});
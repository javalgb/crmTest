layui.use(['form', 'layer','upload','element','laydate'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        upload = layui.upload,
        element = layui.element,
        $ = layui.jquery;
    var laydate = layui.laydate;

    //常规用法
    laydate.render({
        elem: '#testDate'
    });


    /**
     * 监听表单事件
     */
    form.on("submit(addOrUpdateSaleChance)", function (obj) {
        var index = layer.msg("数据正在提交中，请稍等", {icon: 16, time: false, shade: 0.8});

        console.log(obj.field + "<<");

        //判断是添加还是修改，id==null,添加，id!=null 修改

        var url = ctx + "/customer/save";

        //判断当前页面的隐藏域有没有id,有id做修改，否则添加操作
        if ($("input[name=id]").val()) {
            url = ctx + "/customer/update"
        }

        /*发送ajax*/
        $.ajax({
            type: "post",
            url: url,
            data: obj.field,
            dataType: "json",
            success: function (obj) {
                if (obj.code == 200) {
                    //提示一下
                    layer.msg("添加OK", {icon: 1});
                    //关闭加载层
                    layer.close(index);
                    //关闭iframe
                    layer.closeAll("iframe");
                    //刷新页面
                    window.parent.location.reload();
                } else {
                    layer.msg(obj.msg, {icon: 1});
                }
            }
            //取消跳转
        });
        return false;
    })

        /*取消功能*/
        $("#closeBtn").click(function () {
            //假设这是iframe页
            // var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
            // parent.layer.close(index); //再执行关闭
            //获取当前弹出层的索引
            var idx = parent.layer.getFrameIndex(window.name);
            //根据索引关闭
            parent.layer.close(idx);
        });

        // /*添加下拉框*/
        // $.ajax({
        //     type:"post",
        //     url:ctx+"/roomType/roomTypes",
        //     dataType:"json",
        //     success:function(data){
        //         //遍历
        //         for (var x in data) {
        //             $("#roomType").append("<option  value='"+data[x].roomType+"'>"+data[x].roomType+"</option>");
        //             console.log("<option  value='"+data[x].roomType+"'>"+data[x].roomType+"</option>")
        //         }
        //
        //
        //         //重新渲染
        //         layui.form.render("select");
        //     }
        // });


})
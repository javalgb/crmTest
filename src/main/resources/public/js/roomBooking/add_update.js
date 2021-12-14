layui.use(['form', 'layer','upload','element','laydate'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        upload = layui.upload,
        element = layui.element,
        $ = layui.jquery;
    var laydate = layui.laydate;

    var roomType=false;
    var roomId=false;
    form.on('select(roomType)', function (data) {        //对应lay-filter
        roomType= data.value;
        $.ajax({
            type:"post",
            url:ctx+"/roomDetails/roomIds?roomType="+roomType,
            dataType:"json",
            success:function(data){
                $("#roomId").empty()
                //遍历
                for (var x in data) {
                    $("#roomId").append("<option  value='"+data[x].roomId+"'>"+data[x].roomId+"</option>");
                    console.log("<option  value='"+data[x].roomId+"'>"+data[x].roomId+"</option>")
                }


                //重新渲染
                layui.form.render("select");
            }
        });
        console.log(roomType)//获取value值
        // text= data.elem[data.elem.selectedIndex].text;;    //获取显示的值

    });

    form.on('select(roomId)', function (data) {
        $.ajax({
            type:"post",
            url:ctx+"/roomDetails/findPrice?roomId="+data.value,
            dataType:"json",
            success:function(data){
                    console.log(data)
                    console.log(data[0].roomPrice)
                    $("input[name=price]").val(data[0].roomPrice)
                }
            });
            if(!roomType){
                layer.msg("请选择房间类型")
                // data.elem[data.elem.selectedIndex].text="请选择房间号"
                layui.form.render("select")
            }
        });


        // text= data.elem[data.elem.selectedIndex].text;;    //获取显示的值


    /**
     * 监听表单事件
     */
    form.on("submit(addOrUpdateSaleChance)", function (obj) {
        var index = layer.msg("数据正在提交中，请稍等", {icon: 16, time: false, shade: 0.8});

        console.log(obj.field + "<<");

        //判断是添加还是修改，id==null,添加，id!=null 修改

        var url = ctx + "/roomBooking/save";

        //判断当前页面的隐藏域有没有id,有id做修改，否则添加操作
        if ($("input[name=id]").val()) {
            url = ctx + "/roomBooking/update"
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



        /*添加下拉框*/
        $.ajax({
            type:"post",
            url:ctx+"/roomType/roomTypes",
            dataType:"json",
            success:function(data){
                //遍历
                for (var x in data) {
                    $("#roomType").append("<option  value='"+data[x].roomType+"'>"+data[x].roomType+"</option>");
                    console.log("<option  value='"+data[x].roomType+"'>"+data[x].roomType+"</option>")
                }


                //重新渲染
                layui.form.render("select");

            }
        });

    /*添加下拉框*/
    if(!roomType){
        $.ajax({
            type:"post",
            url:ctx+"/roomDetails/roomIds",
            dataType:"json",
            success:function(data){
                //遍历
                for (var x in data) {
                    $("#roomId").append("<option  value='"+data[x].roomId+"'>"+data[x].roomId+"</option>");
                    console.log("<option  value='"+data[x].roomId+"'>"+data[x].roomId+"</option>")
                }


                //重新渲染
                layui.form.render("select");
            }
        });
    }



})
layui.use(['form', 'layer','upload','element','laydate'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        upload = layui.upload,
        element = layui.element,
        $ = layui.jquery;
    var laydate = layui.laydate;

    var foodType=false;
    var foodName=false;
    form.on('select(foodType)', function (data) {        //对应lay-filter
        foodType= data.value;
        $.ajax({
            type:"post",
            url:ctx+"/foodDetails/foodNames?foodTypeName="+foodType,
            dataType:"json",
            success:function(data){
                $("#foodName").empty()
                //遍历
                for (var x in data) {
                    $("#foodName").append("<option  value='"+data[x].foodName+"'>"+data[x].foodName+"</option>");
                    console.log("<option  value='"+data[x].foodName+"'>"+data[x].foodName+"</option>")
                }
                //重新渲染
                layui.form.render("select");
            }
        });


        // console.log(foodName)//获取value值
        // text= data.elem[data.elem.selectedIndex].text;;    //获取显示的值

    });

    //监听房间号下拉框，根据房间号加载点单人
    form.on('select(roomIdUsed)', function (data) {
        $.ajax({
            type:"post",
            url:ctx+"/roomBooking/findOrder?roomId="+data.value,
            dataType:"json",
            success:function(data){
                    console.log(data)
                    console.log(data[0].customerName)
                    $("input[name=orderer]").val(data[0].customerName)
                }
            });
        });
    //监听餐品名下拉框
    form.on('select(foodName)', function (data) {
        if(!foodType){
            layer.msg("请先选择餐品类型")
        }
        foodName= data.value;
        $.get(ctx+"/foodDetails/findFoodPriceByFoodName?foodName="+foodName,function (data){
            console.log(data[0].price)
            $("input[name=foodPrice]").val(data[0].price)
            $("#allPrice").val($("#foodCount").val()*$("#foodPrice").val())

        })
        console.log(foodName)

    });
    $("#foodCount").on("input",function(e){
        //获取input输入的值
        console.log(e.delegateTarget.value);
        var count=e.delegateTarget.value;
        if(!$("#foodPrice").val()){
            layer.msg("请先选择菜品")
            $("#foodCount").empty()
        }else{
            if(count<=0){
                layer.msg("参数错误啦")
                $("#foodCount").empty()
            }
            if(count>10000){
                layer.msg("不能一次性点那么多哦")
            }
            $("#allPrice").val(count*$("#foodPrice").val())
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

        var url = ctx + "/foodBooking/save";

        //判断当前页面的隐藏域有没有id,有id做修改，否则添加操作
        if ($("input[name=id]").val()) {
            url = ctx + "/foodBooking/update"
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



        /*房间类型添加下拉框*/
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
        //
        //     }
        // });

    /*添加下拉框*/
    // if(!roomType){
    //加载可用房间号
        $.ajax({
            type:"post",
            url:ctx+"/roomDetails/roomIdsByUsed",
            dataType:"json",
            success:function(data){
                //遍历
                for (var x in data) {
                    $("#roomIdUsed").append("<option  value='"+data[x].roomId+"'>"+data[x].roomId+"</option>");
                    console.log("<option  value='"+data[x].roomId+"'>"+data[x].roomId+"</option>")
                }


                //重新渲染
                layui.form.render("select");
            }
        });
    // }

    if(!foodType){
            $.ajax({
                type:"post",
                url:ctx+"/foodDetails/foodNames",
                dataType:"json",
                success:function(data){
                    //遍历
                    for (var x in data) {
                        $("#foodName").append("<option  value='"+data[x].foodName+"'>"+data[x].foodName+"</option>");
                        console.log("<option  value='"+data[x].foodName+"'>"+data[x].foodName+"</option>")
                    }


                    //重新渲染
                    layui.form.render("select");
                }
            });
    }


    //加载菜品类型
    $.ajax({
        type:"post",
        url:ctx+"/foodDetails/foodTypes",
        dataType:"json",
        success:function(data){
            //遍历
            for (var x in data) {
                $("#foodType").append("<option  value='"+data[x].foodTypeName+"'>"+data[x].foodTypeName+"</option>");
                console.log("<option  value='"+data[x].foodTypeName+"'>"+data[x].foodTypeName+"</option>")
            }


            //重新渲染
            layui.form.render("select");
        }
    });


})
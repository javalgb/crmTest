layui.use(['form', 'layer','upload','element'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        upload = layui.upload,
        element= layui.element,
        $ = layui.jquery;


    /**
     * 监听表单事件
     */
    form.on("submit(addOrUpdateSaleChance)",function(obj){

        if ($("input[name=foodImg]").val()) { /*加载层*/
            var index = layer.msg("数据正在提交中，请稍等", {icon: 16, time: false, shade: 0.8});

            console.log(obj.field + "<<");

            //判断是添加还是修改，id==null,添加，id!=null 修改

            var url = ctx + "/foodType/save";

            //判断当前页面的隐藏域有没有id,有id做修改，否则添加操作
            if ($("input[name=id]").val()) {
                url = ctx + "/foodType/update"
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
            });
        }else {
            layer.msg("请上传图片")
        }
        //取消跳转
        return false;
    });


    /*取消功能*/
    $("#closeBtn").click(function(){
        //假设这是iframe页
        // var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        // parent.layer.close(index); //再执行关闭
        //获取当前弹出层的索引
       var idx= parent.layer.getFrameIndex(window.name);
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

    var fileName;
    //常规使用 - 普通图片上传
    var uploadInst = upload.render({
        elem: '#test1'
        ,url: ctx+"/upload/image"
        ,before: function(obj){

            //预读本地文件示例，不支持ie8
            obj.preview(function(index, file, result){
                $('#demo1').attr('src', result); //图片链接（base64）
                fileName=file.name;
                console.log(index)
                console.log(file)
                console.log(result)
            });

            element.progress('demo', '0%'); //进度条复位
            layer.msg('上传中', {icon: 16, time: 0});
        }
        ,done: function(res){
            //如果上传失败
            if(res.code > 0){
                return layer.msg('上传失败');
            }
            //上传成功的一些操作
            //……
            $('#demoText').html(''); //置空上传失败的状态
        }
        ,error: function(){
            //演示失败状态，并实现重传
            var demoText = $('#demoText');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function(){
                uploadInst.upload();
            });
        }
        //进度条
        ,progress: function(n, elem, e){
            element.progress('demo', n + '%'); //可配合 layui 进度条元素使用
            if(n == 100){
                layer.msg('上传完毕', {icon: 1});
                $("input[name=foodImg]").val(fileName)
                console.log(fileName)
            }
        }
    });
});
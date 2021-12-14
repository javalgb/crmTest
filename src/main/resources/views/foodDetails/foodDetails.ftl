<!DOCTYPE html>
<html>
<head>
    <title>营销机会管理</title>
    <#include "../common.ftl">
    <script src="${ctx}/js/jquery-1.12.3.min.js" charset="utf-8"></script>
</head>
<body class="childrenBody">
<form class="layui-form" >
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" name="foodName"
                           class="layui-input
							searchVal" placeholder="菜品名称" />
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="foodIntro" class="layui-input
							searchVal" placeholder="菜品介绍" />
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="foodTypeName" class="layui-input
							searchVal" placeholder="菜品类型" />
                </div>
                <a class="layui-btn search_btn" data-type="reload">
                    <i class="layui-icon">&#xe615;</i> 搜索
                </a>
            </div>
        </form>
    </blockquote>

    <!-- 数据表格 -->
    <table id="FoodDetailsList" class="layui-table"  lay-filter="saleChances">
    </table>

    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
                <i class="layui-icon">&#xe608;</i>
                添加
            </a>
            <a class="layui-btn layui-btn-normal delNews_btn" lay-event="del">
                <i class="layui-icon">&#xe608;</i>
                删除
            </a>
        </div>
    </script>


    <!--操作-->
    <script id="roomTypeListBar" type="text/html">
        <a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
    </script>

</form>

<script type="text/javascript" src="${ctx}/js/foodDetails/foodDetails.js"></script>
<script>
    var t;
    function photograph(tar){
        t=$(tar).find("img").attr('src');
        console.log(t)
        layui.use(['table','layer'],function() {
            var layer = parent.layer === undefined ? layui.layer : top.layer,
                $ = layui.jquery,
                table = layui.table;
            //图片变大
            console.log("test")
            console.log(t)
            layui.layer.open({
                type: 1 ,
                skin: 'layui-layer-rim', //加上边框
                area: ['100%', '100%'], //宽高
                shadeClose: false, //开启遮罩关闭
                end: function (index, layero) {
                    return false;
                },
                content: '<div style="text-align:center"><img src="' +t + '" /></div>'
            })


        })



    }


        // layer.open({
        //     type: 1,
        //     skin: 'layui-layer-rim', //加上边框
        //     area: ['75%', '85%'], //宽高
        //     shadeClose: true, //开启遮罩关闭
        //     end: function (index, layero) {
        //         return false;
        //     },
        //     content: '<div style="text-align:center"><img src="' +"localhost"+$(t).attr('src') + '" /></div>'
        // });

        // layui.layer.msg("你好")
        // console.log("你好");
        //
        // layui.layer.open({
        //     type: 2 ,
        //     skin: 'layui-layer-rim',
        //     area: ['60%', '60%'],
        //     shadeClose: false,
        //     end: function (index, layero) {
        //         return false;
        //     },
        //     content: '<div style="text-align:center"><img src="' + "https://img.ivsky.com/img/tupian/li/202105/30/songguoju-006.jpg" + '" /></div>'
        // })

</script>
</body>
</html>
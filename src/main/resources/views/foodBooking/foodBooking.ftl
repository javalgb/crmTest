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
                    <input type="text" name="roomId"
                           class="layui-input
							searchVal" placeholder="房间号" />
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="foodName"
                           class="layui-input
							searchVal" placeholder="餐品名" />
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="foodTypeName"
                           class="layui-input
							searchVal" placeholder="餐品类型" />
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="orderer" class="layui-input
							searchVal" placeholder="顾客名" />
                </div>
                <a class="layui-btn search_btn" data-type="reload">
                    <i class="layui-icon">&#xe615;</i> 搜索
                </a>
            </div>
        </form>
    </blockquote>

    <!-- 数据表格 -->
    <table id="customerList" class="layui-table"  lay-filter="saleChances">
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

<script type="text/javascript" src="${ctx}/js/foodBooking/foodBooking.js"></script>

</body>
</html>
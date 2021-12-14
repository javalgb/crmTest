<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
    <style type="text/css">
        .layui-upload-img{width: 92px;height: 92px;}
    </style>
    <script src="${ctx}/js/jquery-1.12.3.min.js" charset="utf-8"></script>
</head>
<body class="childrenBody">
<form class="layui-form" style="width:100%;">

<#--    <div class="layui-form-item layui-row layui-col-xs12">-->
<#--        <label class="layui-form-label">房间类型</label>-->
<#--        <div class="layui-input-block">-->
<#--            <select name="roomType" id="roomType" lay-filter="roomType">-->
<#--                <option value="">请选择房间类型</option>-->
<#--            </select>-->
<#--        </div>-->
<#--    </div>-->

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">房间号</label>
        <div class="layui-input-block">
            <select name="roomId" id="roomIdUsed" lay-filter="roomIdUsed">
                <option value="">请选择</option>
            </select>
        </div>
    </div>


    <div class="layui-form-item layui-row layui-col-xs12">
        <#--营销机会的ID-->
        <input type="hidden" name="id" value="${(foodBooking.id)!}">
        <#--营销机会的分配人id-->
<#--        <input type="hidden" name="man" value="${(saleChance.assignMan)!}">-->
        <label class="layui-form-label">顾客名</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"  name="orderer"
                   id="orderer" value="${(foodBooking.orderer)!}" placeholder="请输入顾客名">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">餐品类型</label>
        <div class="layui-input-block">
            <select name="foodTypeName" id="foodType" lay-filter="foodType">
                <option value="">请选择</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">餐品名</label>
        <div class="layui-input-block">
            <select name="foodName" id="foodName" lay-filter="foodName">
                <option value="">请选择</option>

            </select>
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">餐品价格</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="foodPrice" id="foodPrice"
                   lay-verify="required" disabled  value="${(foodBooking.foodPrice?c)!}" placeholder="餐品价格">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">份数</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="foodCount" id="foodCount"
                   lay-verify="required"  value="${(foodBooking.foodCount)!}" placeholder="请输入份数">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">总价格</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="allPrice" id="allPrice"
                   lay-verify="required"  disabled value="${(foodBooking.allPrice?c)!}" placeholder="价格">
        </div>
    </div>

    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addOrUpdateSaleChance">
                确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/foodBooking/add_update.js"></script>
<script>



</script>
</body>
</html>
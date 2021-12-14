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

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">房间类型</label>
        <div class="layui-input-block">
            <select name="roomType" id="roomType" lay-filter="roomType">
                <option value="">请选择房间类型</option>
            </select>
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">房间号</label>
        <div class="layui-input-block">
            <select name="roomId" id="roomId" lay-filter="roomId">
                <option value="">请选择</option>
            </select>
        </div>
    </div>


    <div class="layui-form-item layui-row layui-col-xs12">
        <#--营销机会的ID-->
        <input type="hidden" name="id" value="${(roomBooking.id)!}">
        <#--营销机会的分配人id-->
<#--        <input type="hidden" name="man" value="${(saleChance.assignMan)!}">-->
        <label class="layui-form-label">顾客名</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"  name="customerName"
                   id="customerName" value="${(roomBooking.customerName)!}" placeholder="请输入顾客名">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">顾客手机</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="customerPhone"
                   lay-verify="required"  value="${(roomBooking.customerPhone)!}" placeholder="请输入手机号">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">天数</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="bookingDay"
                   lay-verify="required"  value="${(roomBooking.bookingDay)!}" placeholder="请输入天数">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">价格</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="price"
                   lay-verify="required"  disabled value="${(roomBooking.price?c)!}" placeholder="选中房间号获取价格">
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
<script type="text/javascript" src="${ctx}/js/roomBooking/add_update.js"></script>
<script>



</script>
</body>
</html>
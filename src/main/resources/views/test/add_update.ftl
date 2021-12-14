<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <div class="layui-form-item layui-row layui-col-xs12">
        <#--营销机会的ID-->
        <input type="hidden" name="id" value="${(roomType.id)!}">
        <#--营销机会的分配人id-->
<#--        <input type="hidden" name="man" value="${(saleChance.assignMan)!}">-->
        <label class="layui-form-label">房间类型编号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required"
                   name="roomTypeId" id="roomTypeId"  value="${(roomType.roomTypeId)!}" placeholder="请输入房间类型编号">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">房间类型</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"  name="roomType"
                   id="roomType" value="${(roomType.roomType)!}" placeholder="请输入房间类型">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">房间介绍</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="roomRemark"
                   lay-verify="required"  value="${(roomType.roomRemark)!}" placeholder="请输入房间介绍">
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
<script type="text/javascript" src="${ctx}/js/roomType/add_update.js"></script>
</body>
</html>
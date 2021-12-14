<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
    <style type="text/css">
        .layui-upload-img{width: 92px;height: 92px;}
    </style>
</head>
<body class="childrenBody">
<form class="layui-form" style="width:100%;">
    <div class="layui-form-item layui-row layui-col-xs12">
        <#--营销机会的ID-->
        <input type="hidden" name="id" value="${(customer.id)!}">
        <#--营销机会的分配人id-->
<#--        <input type="hidden" name="man" value="${(saleChance.assignMan)!}">-->
        <label class="layui-form-label">顾客名</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"  name="cName"
                   id="Cname" value="${(customer.cName)!}" placeholder="请输入顾客名">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">顾客地址</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="cAddr"
                   lay-verify="required"  value="${(customer.cAddr)!}" placeholder="请输入地址">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">手机号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="cPhone"
                   lay-verify="required"  value="${(customer.cPhone)!}" placeholder="请输入手机号">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">邮箱</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="cEmai"
                   lay-verify="required"  value="${(customer.cEmai)!}" placeholder="请输入邮箱">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-inline">
            <label class="layui-form-label">日期</label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input" id="testDate" name="cDate" value="${(customer.cDate?string('yyyy-MM-dd'))!}" placeholder="yyyy-MM-dd">
            </div>
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">是否会员</label>
        <div class="layui-input-block">
            <select name="cIsvip" id="isHave">
                <option value="">请选择</option>
                <option selected="selected" value="1">是的</option>
                <option value="0">不是</option>
            </select>
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
<script type="text/javascript" src="${ctx}/js/customer/add_update.js"></script>
</body>
</html>
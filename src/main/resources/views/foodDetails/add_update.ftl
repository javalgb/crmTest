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
        <input type="hidden" name="id" value="${(foodDetails.id)!}">
        <#--营销机会的分配人id-->
<#--        <input type="hidden" name="man" value="${(saleChance.assignMan)!}">-->
        <label class="layui-form-label">菜品名</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"  name="foodName"
                   id="foodName" value="${(foodDetails.foodName)!}" placeholder="请输入菜品名">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">菜品介绍</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="foodIntro"
                   lay-verify="required"  value="${(foodDetails.foodIntro)!}" placeholder="请输入菜品介绍">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">菜品类型</label>
        <div class="layui-input-block">
            <select name="foodTypeName" id="foodTypeName">
                <option value="">请选择</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">菜品价格</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="price"
                   lay-verify="required"  value="${(foodDetails.price?c)!}" placeholder="请输入菜品价格">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">有无存货</label>
        <div class="layui-input-block">
            <select name="isHave" id="isHave">
                <option value="">请选择</option>
                <option selected="selected" value="1">有的</option>
                <option value="0">没有</option>
            </select>
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12 layui-upload" align="center">
            <button type="button" class="layui-btn" id="test1">上传图片</button>
            <div class="layui-upload-list">
                <img class="layui-upload-img" id="demo1">
                <p id="demoText"></p>
            </div>
            <div style="width: 95px;">
                <div class="layui-progress layui-progress-big" lay-showpercent="yes" lay-filter="demo">
                    <div class="layui-progress-bar" lay-percent=""></div>
                </div>
            </div>

        <a name="list-progress"> </a>

        <div style="margin-top: 10px;">
            <!-- 示例-970 -->
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
    <div class="layui-form-item">
        <div class="layui-input-block">
            <input type="hidden" name="foodImg" value="">
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/foodDetails/add_update.js"></script>
</body>
</html>
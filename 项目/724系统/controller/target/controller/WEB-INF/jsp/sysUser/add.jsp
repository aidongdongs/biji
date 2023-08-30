<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp" %>

<div class="right">
    <div class="location">
        <strong>当前位置:</strong>
        <span>用户管理页面 >> 用户添加页面</span>
    </div>
<%--    enctype="multipart/form-data"--%>
    <div class="supplierAdd">
        <form id="userForm" name="userForm"  method="post" action="${pageContext.request.contextPath }/sys/user/add">
            <!--div的class 为error是验证错误，ok是验证成功-->
            <div>
                <label for="account">账号：</label>
                <input type="text" name="account" id="account" value="">
                <!-- 放置提示信息 -->
                <font color="red"></font>
            </div>
            <div>
                <label for="realName">真实姓名：</label>
                <input type="text" name="realName" id="realName" value="">
                <font color="red"></font>
            </div>
            <div>
                <label for="password">密码：</label>
                <input type="password" name="password" id="password" value="">
                <font color="red"></font>
            </div>
            <div>
                <label for="rpassword">确认密码：</label>
                <input type="password" name="rpassword" id="rpassword" value="">
                <font color="red"></font>
            </div>
            <div>
                <label >性别：</label>
                <select name="sex" id="sex">
                    <option value="1" selected="selected">女</option>
                    <option value="2">男</option>
                </select>
            </div>
            <div>
                <label for="birthday">出生日期：</label>
                <input type="text" Class="Wdate" id="birthday" name="birthday"
                       readonly="readonly" onclick="WdatePicker();">
                <font color="red"></font>
            </div>
            <div>
                <label for="phone">手机号码：</label>
                <input type="text" name="phone" id="phone" value="">
                <font color="red"></font>
            </div>
            <div>
                <label for="address">地址：</label>
                <input name="address" id="address"  value="">
            </div>
            <div>
                <label >角色：</label>
                <!-- 列出所有的角色分类 -->
                <select name="roleId" id="roleList"></select>
                <%--<select name="roleId" id="roleId">
                    <option value="1">系统管理员</option>
                    <option value="2">经理</option>
                    <option value="3" selected="selected">普通用户</option>
                </select>--%>
                <font color="red"></font>
            </div>
<%--            <div>--%>
<%--                <input type="hidden" id="errorinfo" value="${uploadFileError}"/>--%>
<%--                <label for="idPic">证件照</label>--%>
<%--                <input type="file" name="idPic" id="idPic"/>--%>
<%--                <font color="red"></font>--%>
<%--            </div>--%>
<%--            <div>--%>
<%--                <input type="hidden" id="errorinfo_wp" value="${uploadWpError}"/>--%>
<%--                <label for="workPic">工作证照片：</label>--%>
<%--                <input type="file" name="workPic" id="workPic"/>--%>
<%--                <font color="red"></font>--%>
<%--            </div>--%>
            <div class="supplierAddBtn">
                <input type="button" name="add" id="add" value="保存" >
                <input type="button" id="back" name="back" value="返回" >
            </div>
        </form>
    </div>
</div>
</section>
<%@include file="/WEB-INF/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/sysUser/add.js"></script>

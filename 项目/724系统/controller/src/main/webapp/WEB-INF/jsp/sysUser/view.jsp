<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>
 <div class="right">
        <div class="location">
            <strong>当前位置:</strong>
            <span>用户管理页面 >> 用户查看详情页面</span>
        </div>
        <div class="supplierView">
            <p><strong>用户编号：</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>${sysUser.account }</span></p>
            <p><strong>账号：</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>${sysUser.realName }</span></p>
            <p><strong>性别：</strong>
            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	<span>
            		<c:if test="${sysUser.sex == 1 }">男</c:if>
					<c:if test="${sysUser.sex == 2 }">女</c:if>
				</span>
			</p>
            <p><strong>出生日期：</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span><fmt:formatDate value="${birthday }" pattern="yyyy-MM-dd"/></span></p>
            <p><strong>手机号码：</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>${sysUser.phone }</span></p>
            <p><strong>地址：</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>${sysUser.address }</span></p>
            <p><strong>角色：</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>${sysUser.roleId}</span></p>
			<div class="supplierAddBtn">
            	<input type="button" id="back" name="back" value="返回" >
            </div>
        </div>
    </div>
</section>
<%@include file="/WEB-INF/jsp/common/foot.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/sysUser/view.js"></script>
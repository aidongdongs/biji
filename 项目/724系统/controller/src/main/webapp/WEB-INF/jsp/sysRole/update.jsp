<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>
    <div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>角色管理页面 >> 角色修改页面</span>
        </div>
        <div class="supplierAdd">
        <form id="roleForm" name="roleForm" method="post" action="${pageContext.request.contextPath }/sys/role/update">
			<input type="hidden" name="id" value="${sysRole.id }"/>
			 <div>
                    <label for="code">用户编码：</label>
                    <input type="text" value="${sysRole.code }" readonly="readonly">
             </div>
			 <div>
                    <label for="roleName">用户名称：</label>
                    <input type="text" name="roleName" id="roleName" value="${sysRole.roleName }">
					<font color="red"></font>
             </div>
			
			 <div class="supplierAddBtn">
                    <input type="button" name="save" id="save" value="保存" />
                    <input type="button" id="back" name="back" value="返回"/>
                </div>
            </form>
        </div>
    </div>
</section>
<%@include file="/WEB-INF/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/sysRole/update.js"></script>

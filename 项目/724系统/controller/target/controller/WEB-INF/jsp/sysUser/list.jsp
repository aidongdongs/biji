<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>
        <div class="right">
            <div class="location">
                <strong>当前位置:</strong>
                <span>用户管理页面</span>
            </div>
            <div class="search">
           		<form method="get" action="${pageContext.request.contextPath }/sys/user/list">
					 <span>用户名：</span>
					 <input name="queryRealName" class="input-text"	type="text" value="${queryRealName }">
					 
					 <span>角色：</span>
					 <select name="queryRoleId">
						<c:if test="${roleList != null }">
						   <option value="">--全部--</option>
						   <c:forEach var="role" items="${roleList}">
						   		<option <c:if test="${role.id == queryRoleId }">selected="selected"</c:if>
						   		value="${role.id}">${role.roleName}</option>
						   </c:forEach>
						</c:if>
	        		</select>
					 
					 <input type="hidden" name="pageIndex" value="1"/>
					 <input	value="查 询" type="submit" id="searchbutton">
					 <a href="${pageContext.request.contextPath}/sys/user/toAdd" >添加</a>
				</form>
            </div>
            <!--用户-->
            <table class="supplierTable" cellpadding="0" cellspacing="0">
                <tr class="firstTr">
                    <th width="15%">账号</th>
                    <th width="15%">真实姓名</th>
                    <th width="10%">角色</th>
                    <th width="10%">性别</th>
                    <th width="10%">年龄</th>
                    <th width="10%">电话</th>
                    <th width="30%">操作</th>
                </tr>
                   <c:forEach var="user" items="${userList }" varStatus="status">
					<tr>
						<td>
						<span>${user.account }</span>
						</td>
						<td>
						<span>${user.realName }</span>
						</td>
						<td>
							<span>${user.roleId}</span>
						</td>
						<td>
							<span>
								<c:if test="${user.sex==1}">女</c:if>
								<c:if test="${user.sex==2}">男</c:if>
							</span>
						</td>
						<td>
						<span>${user.birthday}</span>
						</td>
						<td>
						<span>${user.phone}</span>
						</td>
						<td>
						<span><a class="viewUser" href="javascript:;" userid=${user.id } account=${user.account }><img src="${pageContext.request.contextPath }/statics/images/view.png" alt="查看" title="查看"/></a></span>
						<span><a class="modifyUser" href="javascript:;" userid=${user.id } account=${user.account }><img src="${pageContext.request.contextPath }/statics/images/upd.png" alt="修改" title="修改"/></a></span>
						<span><a class="deleteUser" href="javascript:;" userid=${user.id } account=${user.account }><img src="${pageContext.request.contextPath }/statics/images/del.png" alt="删除" title="删除"/></a></span>
						</td>
					</tr>
				</c:forEach>
			</table>
			<input type="hidden" id="totalPageCount" value="${totalPageCount}"/>
		  	<c:import url="/WEB-INF/jsp/sysRole/rollpage.jsp">
	          	<c:param name="totalCount" value="${totalCount}"/>
	          	<c:param name="currentPageNo" value="${currentPageNo}"/>
	          	<c:param name="totalPageCount" value="${totalPageCount}"/>
          	</c:import>
			<%--<div class="supplierAdd">
				<div>
					<label>用户编码：</label>
					<input type="text" id="v_account" value="" readonly="readonly">
				</div>
				<div>
					<label>用户名称：</label>
					<input type="text" id="v_realName" value="" readonly="readonly">
				</div>
				<div>
					<label>用户性别：</label>
					<input type="text" id="v_sex" value="" readonly="readonly">
				</div>
				<div>
					<label>出生日期：</label>
					<input type="text" Class="Wdate" id="v_birthday" value=""
						   readonly="readonly" onclick="WdatePicker();">
				</div>
				<div>
					<label>创建日期：</label>
					<input type="text" Class="Wdate" id="v_createdTime" value=""
						   readonly="readonly" onclick="WdatePicker();">
				</div>
				<div>
					<label>用户电话：</label>
					<input type="text" id="v_phone" value="" readonly="readonly">
				</div>
				<div>
					<label>用户角色：</label>
					<input type="text" id="v_roleIdName" value="" readonly="readonly">
				</div>
				<div>
					<label>用户地址：</label>
					<input type="text" id="v_address" value="" readonly="readonly">
				</div>
			</div>
        </div>--%>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeUse">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>确定删除该用户吗？</p>
            <a href="#" id="yes">是</a>
            <a href="#" id="no">否</a>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/sysUser/list.js"></script>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="right">
        <div class="location">
            <strong>当前位置:</strong>
            <span>供货商管理页面</span>
        </div>
        <div class="search">
        	<form method="get" action="${pageContext.request.contextPath }/sys/supplier/list">
				<span>供货商编码：</span>
				<input name="supCode" type="text" value="${supCode }">
				
				<span>供货商名称：</span>
				<input name="supName" type="text" value="${supName }">
				<input type="hidden" name="pageIndex" value="1"/>
				<input value="查 询" type="submit" id="searchbutton">
				<a href="${pageContext.request.contextPath }/sys/supplier/toAdd">添加</a>
			</form>
        </div>
        <!--供货商操作表格-->
        <table class="supplierTable" cellpadding="0" cellspacing="0">
            <tr class="firstTr">
                <th width="10%">供货商编码</th>
                <th width="20%">供货商名称</th>
                <th width="10%">联系人</th>
                <th width="10%">联系电话</th>
                <th width="10%">传真</th>
                <th width="10%">创建时间</th>
                <th width="30%">操作</th>
            </tr>
            <c:forEach items="${pageInfo.getList()}" var="supplier"  varStatus="status">
				<tr>
					<td>
					<span>${supplier.supCode }</span>
					</td>
					<td>
					<span>${supplier.supName }</span>
					</td>
					<td>
					<span>${supplier.supContact}</span>
					</td>
					<td>
					<span>${supplier.supPhone}</span>
					</td>
					<td>
					<span>${supplier.supFax}</span>
					</td>
					<td>
					<span>
					<fmt:formatDate value="${supplier.createdTime}" pattern="yyyy-MM-dd"/>
					</span>
					</td>
					<td>
					<span><a class="viewSupplier" href="javascript:;" supId=${supplier.id } supName=${supplier.supName }><img src="${pageContext.request.contextPath }/statics/images/view.png" alt="查看" title="查看"/></a></span>
					<span><a class="modifySupplier" href="javascript:;" supId=${supplier.id } supName=${supplier.supName }><img src="${pageContext.request.contextPath }/statics/images/upd.png" alt="修改" title="修改"/></a></span>
					<span><a class="deleteSupplier" href="javascript:;" supId=${supplier.id } supName=${supplier.supName }><img src="${pageContext.request.contextPath }/statics/images/del.png" alt="删除" title="删除"/></a></span>
					</td>
				</tr>
			</c:forEach>
        </table>
	    <input type="hidden" id="totalPageCount" value="${pageInfo.getPages()}"/>
		<c:import url="/jsp/sysRole/rollpage.jsp">
			<c:param name="totalCount" value="${totalCount}"/>
			<c:param name="currentPageNo" value="${currentPageNo}"/>
			<c:param name="totalPageCount" value="${totalPageCount}"/>
		</c:import>
    </div>
</section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeProv">
   <div class="removerChid">
       <h2>提示</h2>
       <div class="removeMain" >
           <p>确定删除该供货商吗？</p>
           <a href="#" id="yes">是</a>
           <a href="#" id="no">否</a>
       </div>
   </div>
</div>

<%@include file="/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/supplier/list.js"></script>

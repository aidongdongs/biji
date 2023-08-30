<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>
<meta content="text/html; charset=UTF-8" http-equiv="content-type">

<div class="right">
       <div class="location">
           <strong>当前位置:</strong>
           <span>入库记录管理页面</span>
       </div>
       <div class="search">
       <form method="get" action="${pageContext.request.contextPath }/sys/storageRecord/list">
			<span>商品名称：</span>
			<input name="queryGoodsName" type="text" value="${queryGoodsName }">
			<span>供货商：</span>
			<select name="querySupplierId">
				<c:if test="${supplierList != null }">
				   <option value="">--全部--</option>
				   <c:forEach var="supplier" items="${supplierList}">
				   		<option <c:if test="${supplier.id == querySupplierId }">selected="selected"</c:if>
				   		value="${supplier.id}">${supplier.supName}</option>
				   </c:forEach>
				</c:if>
       		</select>
			 
			<span>付款状态：</span>
			<select name="queryPayStatus">
				<option value="">--全部--</option>
				<option value="1" ${querypayStatus == 1 ? "selected=\"selected\"":"" }>未付款</option>
				<option value="2" ${querypayStatus == 2 ? "selected=\"selected\"":"" }>已付款</option>
       		</select>
		   <input type="hidden" name="pageIndex" value="1"/>
		   <input	value="查 询" type="submit" id="searchbutton">
			 <a href="${pageContext.request.contextPath }/sys/storageRecord/toAdd">添加</a>
		</form>
       </div>
       <!--账单表格 样式和供货商公用-->
      <table class="supplierTable" cellpadding="0" cellspacing="0">
          <tr class="firstTr">
              <th width="15%">入库编号</th>
              <th width="10%">商品名称</th>
              <th width="10%">金额</th>
              <th width="20%">供货商</th>
              <th width="10%">付款状态</th>
              <th width="10%">创建时间</th>
              <th width="25%">操作</th>
          </tr>
          <c:forEach var="storageRecord" items="${storageRecordList}" varStatus="status">
				<tr>
					<td>
					<span>${storageRecord.srCode }</span>
					</td>
					<td>
					<span>${storageRecord.goodsName }</span>
					</td>
					<td>
					<span>${storageRecord.totalAmount}</span>
					</td>
					<td>
					<span>${storageRecord.supplierId}</span>
					</td>
					<td>
					<span>
						<c:if test="${storageRecord.payStatus == 1}">未付款</c:if>
						<c:if test="${storageRecord.payStatus == 2}">已付款</c:if>
					</span>
					</td>
					<td>
					<span>
					<fmt:formatDate value="${storageRecord.createdTime}" pattern="yyyy-MM-dd"/>
					</span>
					</td>
					<td>
					<span><a class="viewStorageRecord" href="javascript:;" storageRecordId=${storageRecord.id } StorageRecordcc=${storageRecord.srCode }><img src="${pageContext.request.contextPath }/statics/images/view.png" alt="查看" title="查看"/></a></span>
					<span><a class="modifyStorageRecord" href="javascript:;" storageRecordId=${storageRecord.id } StorageRecordcc=${storageRecord.srCode }><img src="${pageContext.request.contextPath }/statics/images/upd.png" alt="修改" title="修改"/></a></span>
					<span><a class="deleteStorageRecord" href="javascript:;" storageRecordId=${storageRecord.id } StorageRecordcc=${storageRecord.srCode }><img src="${pageContext.request.contextPath }/statics/images/del.png" alt="删除" title="删除"/></a></span>
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
  </div>
</section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeBi">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>确定删除该订单吗？</p>
            <a href="#" id="yes">是</a>
            <a href="#" id="no">否</a>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/storageRecord/list.js"></script>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>
<div class="right">
     <div class="location">
         <strong>当前位置:</strong>
         <span>入库记录管理页面 >> 查看详情</span>
     </div>
     <div class="supplierView">
         <p><strong>入库编号：</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>${storageRecord.srCode }</span></p>
         <p><strong>商品名称：</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>${storageRecord.goodsName }</span></p>
         <p><strong>入库数量：</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>${storageRecord.goodsCount }</span></p>
         <p><strong>商品单位：</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>${storageRecord.goodsUnit }</span></p>
         <p><strong>总金额：</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>${storageRecord.totalAmount }</span></p>
         <p><strong>供货商：</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>${storageRecord.supName }</span></p>
         <p><strong>付款状态：</strong>
         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         	<span>
         		<c:if test="${storageRecord.payStatus == 1}">未付款</c:if>
				<c:if test="${storageRecord.payStatus == 2}">已付款</c:if>
			</span>
		</p>
		<div class="supplierAddBtn">
         	<input type="button" id="back" name="back" value="返回" >
        </div>
     </div>
 </div>
</section>
<%@include file="/WEB-INF/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/storageRecord/view.js"></script>
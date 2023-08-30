<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>
<meta content="text/html; charset=UTF-8" http-equiv="content-type">

<div class="right">
        <div class="location">
            <strong>当前位置:</strong>
            <span>入库记录管理页面 >> 订单添加页面</span>
        </div>
        <div class="supplierAdd">
          <form id="storageRecordForm" name="storageRecordForm" method="post" action="${pageContext.request.contextPath }/sys/storageRecord/update">
				<input type="hidden" name="id" value="${storageRecord.id }">
                <!--div的class 为error是验证错误，ok是验证成功-->
                <div class="">
                    <label for="srCode">入库编号：</label>
                    <input type="text" name="srCode" id="srCode" value="${storageRecord.srCode }" readonly="readonly">
                </div>
                <div>
                    <label for="goodsName">商品名称：</label>
                    <input type="text" name="goodsName" id="goodsName" value="${storageRecord.goodsName }">
					<font color="red"></font>
                </div>
                <div>
                    <label for="goodsUnit">商品单位：</label>
                    <input type="text" name="goodsUnit" id="goodsUnit" value="${storageRecord.goodsUnit }">
					<font color="red"></font>
                </div>
                <div>
                    <label for="goodsCount">入库数量：</label>
                    <input type="text" name="goodsCount" id="goodsCount" value="${storageRecord.goodsCount }">
					<font color="red"></font>
                </div>
                <div>
                    <label for="totalAmount">总金额：</label>
                    <input type="text" name="totalAmount" id="totalAmount" value="${storageRecord.totalAmount }">
					<font color="red"></font>
                </div>
                <div>
                    <label for="supplierId">供货商：</label>
                    <input type="hidden" value="${storageRecord.supplierId }" id="pid" />
					<select name="supplierId" id="supplierId">
		        	</select>
					<font color="red"></font>
                </div>
                <div>
                    <label >付款状态：</label>
                    <c:if test="${storageRecord.payStatus == 1 }">
						<input type="radio" name="payStatus" value="1" checked="checked">未付款
						<input type="radio" name="payStatus" value="2" >已付款
					</c:if>
					<c:if test="${storageRecord.payStatus == 2 }">
						<input type="radio" name="payStatus" value="1">未付款
						<input type="radio" name="payStatus" value="2" checked="checked">已付款
					</c:if>
                </div>
                <div class="supplierAddBtn">
                  <input type="button" name="save" id="save" value="保存">
				  <input type="button" id="back" name="back" value="返回" >
              	</div>
            </form>
        </div>

    </div>
</section>

<%@include file="/WEB-INF/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/storageRecord/update.js"></script>
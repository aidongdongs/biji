<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/head.jsp"%>
<meta content="text/html; charset=UTF-8" http-equiv="content-type">

<div class="right">
     <div class="location">
         <strong>当前位置:</strong>
         <span>入库记录管理页面 >> 入口单添加页面</span>
     </div>
     <div class="supplierAdd">
         <form id="storageRecordForm" name="storageRecordForm" method="post" action="${pageContext.request.contextPath }/sys/storageRecord/add">
             <!--div的class 为error是验证错误，ok是验证成功-->
             <div class="">
                 <label for="srCode">入库编号：</label>
                 <input type="text" name="srCode" class="text" id="srCode" value="">
				 <!-- 放置提示信息 -->
				 <font color="red"></font>
             </div>
             <div>
                 <label for="goodsName">商品名称：</label>
                 <input type="text" name="goodsName" id="goodsName" value="">
				 <font color="red"></font>
             </div>
             <div>
                 <label for="goodsCount">入库数量：</label>
                 <input type="text" name="goodsCount" id="goodsCount" value="">
                 <font color="red"></font>
             </div>
             <div>
                 <label for="goodsUnit">商品单位：</label>
                 <input type="text" name="goodsUnit" id="goodsUnit" value="">
				 <font color="red"></font>
             </div>
             <div>
                 <label for="totalAmount">总金额：</label>
                 <input type="text" name="totalAmount" id="totalAmount" value="">
				 <font color="red"></font>
             </div>
             <div>
                 <label >供货商：</label>
                 <select name="supplierId" id="supplierId">

                         <c:if test="${supplierList != null }">
                             <option value="">--请选择--</option>
                             <c:forEach var="supplier" items="${supplierList}">
                                 <option
                                         value="${supplier.id}">${supplier.supName}
                                 </option>
                             </c:forEach>
                         </c:if>

		         </select>
				 <font color="red"></font>
             </div>
             <div>
                 <label >付款状态：</label>
                 <input type="radio" name="payStatus" value="1" checked="checked">未付款
				 <input type="radio" name="payStatus" value="2" >已付款
             </div>
             <div class="supplierAddBtn">
                  <input type="button" name="add" id="add" value="保存">
				  <input type="button" id="back" name="back" value="返回" >
             </div>
         </form>
     </div>
 </div>
</section>
<%@include file="/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/storageRecord/add.js"></script>
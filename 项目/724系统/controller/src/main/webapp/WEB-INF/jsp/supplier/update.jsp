<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>

  <div class="right">
      <div class="location">
          <strong>当前位置:</strong>
          <span>供货商管理页面 >> 供货商修改页</span>
      </div>
      <div class="supplierAdd">
          <form id="supplierForm" name="supplierForm" method="post" action="${pageContext.request.contextPath }/sys/supplier/update">
          	<input type="hidden" name="id" value="${supplier.id }">
              <!--div的class 为error是验证错误，ok是验证成功-->
              <div class="">
                  <label for="supCode">供货商编码：</label>
                  <input type="text" name="supCode" id="supCode" value="${supplier.supCode }" readonly="readonly">
              </div>
              <div>
                  <label for="supName">供货商名称：</label>
                 <input type="text" name="supName" id="supName" value="${supplier.supName }">
			<font color="red"></font>
              </div>
              
              <div>
                  <label for="supPhone">联系电话：</label>
                  <input type="text" name="supPhone" id="supPhone" value="${supplier.supPhone }">
			<font color="red"></font>
              </div>
              <div>
                  <label for="supContact">联系人：</label>
                  <input type="text" name="supContact" id="supContact" value="${supplier.supContact }">
                  <font color="red"></font>
              </div>

              <div>
                  <label for="supAddress">联系地址：</label>
                  <input type="text" name="supAddress" id="supAddress" value="${supplier.supAddress }">
              </div>
              
              <div>
                  <label for="supFax">传真：</label>
                  <input type="text" name="supFax" id="supFax" value="${supplier.supFax }">
              </div>
              
              <div>
                  <label for="supDesc">备注：</label>
                  <input type="text" name="supDesc" id="supDesc" value="${supplier.supDesc }">
              </div>
              <div>
                  ${message}
              </div>
              <div class="supplierAddBtn">
                  <input type="button" name="save" id="save" value="保存">
				  <input type="button" id="back" name="back" value="返回" >
              </div>
          </form>
      </div>
  </div>
</section>
<%@include file="/WEB-INF/jsp/common/foot.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/supplier/update.js"></script>
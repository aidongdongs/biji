<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>

<div class="right">
    <div class="location">
        <strong>当前位置:</strong>
        <span>供货商管理页面 >> 供货商添加页面</span>
    </div>
    <div class="supplierAdd">
        <form id="supplierForm" name="supplierForm"  method="post" action="${pageContext.request.contextPath }/sys/supplier/add">
            <input type="hidden" name="method" value="add">
            <!--div的class 为error是验证错误，ok是验证成功-->
            <div class="">
                <label for="supCode">供货商编码：</label>
                <input type="text" name="supCode" id="supCode" value="">
                <!-- 放置提示信息 -->
                <font color="red"></font>
            </div>
            <div>
                <label for="supName">供货商名称：</label>
                <input type="text" name="supName" id="supName" value="">
                <font color="red"></font>
            </div>
            <div>
                <label for="supContact">联系人：</label>
                <input type="text" name="supContact" id="supContact" value="">
                <font color="red"></font>

            </div>
            <div>
                <label for="supPhone">联系电话：</label>
                <input type="text" name="supPhone" id="supPhone" value="">
                <font color="red"></font>
            </div>
            <div>
                <label for="supAddress">联系地址：</label>
                <input type="text" name="supAddress" id="supAddress" value="">
            </div>
            <div>
                <label for="supFax">传真：</label>
                <input type="text" name="supFax" id="supFax" value="">
            </div>
            <div>
                <label for="supDesc">备注：</label>
                <input type="text" name="supDesc" id="supDesc" value="">
            </div>
<%--            <div>--%>
<%--                <input type="hidden" id="errorinfo" value="${uploadFileError}"/>--%>
<%--                <label for="companyLicPic">企业营业执照：</label>--%>
<%--                <input type="file" name="attachs" id="companyLicPic"/>--%>
<%--                <font color="red"></font>--%>
<%--            </div>--%>
<%--            <div>--%>
<%--                <input type="hidden" id="errorinfo_oc" value="${uploadOcError}"/>--%>
<%--                <label for="orgCodePic">组织机构代码证：</label>--%>
<%--                <input type="file" name="attachs" id="orgCodePic"/>--%>
<%--                <font color="red"></font>--%>
<%--            </div>--%>
            <div class="supplierAddBtn">
                <input type="button" name="add" id="add" value="保存">
                <input type="button" id="back" name="back" value="返回" >
            </div>
        </form>
    </div>
</div>
</section>
<%@include file="/WEB-INF/jsp/common/foot.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/supplier/add.js"></script>


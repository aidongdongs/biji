<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>
<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>供应商管理页面 >> 信息查看</span>
    </div>
    <div class="supplierView">
        <p><strong>供应商编码：</strong><span>${supplier.supCode }</span></p>
        <p><strong>供应商名称：</strong><span>${supplier.supName }</span></p>
        <p><strong>联系人：</strong><span>${supplier.supContact }</span></p>
        <p><strong>联系电话：</strong><span>${supplier.supPhone }</span></p>
        <p><strong>传真：</strong><span>${supplier.supFax }</span></p>
        <p><strong>描述：</strong><span>${supplier.supDesc}</span></p>
<%--        <p><strong>企业营业执照：</strong><span>--%>
<%--            <c:choose>--%>
<%--                <c:when test="${supplier.companyLicPicPath == null || supplier.companyLicPicPath == ''}">--%>
<%--                    暂无--%>
<%--                </c:when>--%>
<%--                <c:otherwise>--%>
<%--                    <img src="${supplier.companyLicPicPath}"/>--%>
<%--                </c:otherwise>--%>
<%--            </c:choose>--%>
<%--            </span></p>--%>

<%--        <p><strong>组织机构代码证：</strong><span>--%>
<%--            <c:choose>--%>
<%--                <c:when test="${supplier.orgCodePicPath == null || supplier.orgCodePicPath == ''}">--%>
<%--                    暂无--%>
<%--                </c:when>--%>
<%--                <c:otherwise>--%>
<%--                    <img src="${supplier.orgCodePicPath}"/>--%>
<%--                </c:otherwise>--%>
<%--            </c:choose>--%>
<%--            </span></p>--%>

        <div class="supplierAddBtn">
            <input type="button" id="back" name="back" value="返回" >
        </div>
    </div>
</div>
</section>
<%@include file="/WEB-INF/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/supplier/view.js"></script>

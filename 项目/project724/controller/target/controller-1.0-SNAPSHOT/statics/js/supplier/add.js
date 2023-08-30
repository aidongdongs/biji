var supCode = null;
var supName = null;
var supContact = null;
var supPhone = null;
var addBtn = null;
var backBtn = null;
var companyLicPic = null;
var errorinfo = null;
var errorinfo_oc = null;
var orgCodePic = null;

$(function(){
	supCode = $("#supCode");
	supName = $("#supName");
	supContact = $("#supContact");
	supPhone = $("#supPhone");
    companyLicPic = $("#companyLicPic");
    errorinfo = $("#errorinfo");
    orgCodePic = $("#orgCodePic");
    errorinfo_oc = $("#errorinfo_oc");
	addBtn = $("#add");
	backBtn = $("#back");
	//初始化的时候，要把所有的提示信息变为：* 以提示必填项，更灵活，不要写在页面上
	supCode.next().html("*");
	supName.next().html("*");
	supContact.next().html("*");
	supPhone.next().html("*");
    if(errorinfo.val() == null || errorinfo.val() == ""){
        companyLicPic.next().html("* 上传大小不能超过500K * 上传文件类型必须为：jpg、jpeg、png、pneg");
    }else{
        companyLicPic.next().html(errorinfo.val());
    }
    if(errorinfo_oc.val() == null || errorinfo_oc.val() == ""){
        orgCodePic.next().html("* 上传大小不能超过500K * 上传文件类型必须为：jpg、jpeg、png、pneg");
    }else{
        orgCodePic.next().html(errorinfo_oc.val());
    }
	/*
	 * 验证
	 * 失焦\获焦
	 * jquery的方法传递
	 */
	supCode.on("blur",function(){
		if(supCode.val() != null && supCode.val() != ""){
			validateTip(supCode.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(supCode.next(),{"color":"red"},imgNo+" 编码不能为空，请重新输入",false);
		}
	}).on("focus",function(){
		//显示友情提示
		validateTip(supCode.next(),{"color":"#666666"},"* 请输入供货商编码",false);
	}).focus();
	
	supName.on("focus",function(){
		validateTip(supName.next(),{"color":"#666666"},"* 请输入供货商名称",false);
	}).on("blur",function(){
		if(supName.val() != null && supName.val() != ""){
			validateTip(supName.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(supName.next(),{"color":"red"},imgNo+" 供货商名称不能为空，请重新输入",false);
		}
		
	});
	
	supContact.on("focus",function(){
		validateTip(supContact.next(),{"color":"#666666"},"* 请输入联系人",false);
	}).on("blur",function(){
		if(supContact.val() != null && supContact.val() != ""){
			validateTip(supContact.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(supContact.next(),{"color":"red"},imgNo+" 联系人不能为空，请重新输入",false);
		}
		
	});
	
	supPhone.on("focus",function(){
		validateTip(supPhone.next(),{"color":"#666666"},"* 请输入手机号",false);
	}).on("blur",function(){
		var patrn=/^(13[0-9]|15[0-9]|18[0-9])\d{8}$/;
		if(supPhone.val().match(patrn)){
			validateTip(supPhone.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(supPhone.next(),{"color":"red"},imgNo + " 您输入的手机号格式不正确",false);
		}
	});
	
	addBtn.bind("click",function(){
		if(supCode.attr("validateStatus") != "true"){
			supCode.blur();
		}else if(supName.attr("validateStatus") != "true"){
			supName.blur();
		}else if(supContact.attr("validateStatus") != "true"){
			supContact.blur();
		}else if(supPhone.attr("validateStatus") != "true"){
			supPhone.blur();
		}else{
			if(confirm("是否确认提交数据")){
				$("#supplierForm").submit();
			}
		}
	});
	
	backBtn.on("click",function(){
		if(referer != undefined 
			&& null != referer 
			&& "" != referer
			&& "null" != referer
			&& referer.length > 4){
		 window.location.href = referer;
		}else{
			history.back(-1);
		}
	});
});
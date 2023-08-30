var supContact = null;
var supPhone = null;
var saveBtn = null;
var backBtn = null;

$(function(){
	supContact = $("#supContact");
	supPhone = $("#supPhone");
	saveBtn = $("#save");
	backBtn = $("#back");
	
	//初始化的时候，要把所有的提示信息变为：* 以提示必填项，更灵活，不要写在页面上
	supContact.next().html("*");
	supPhone.next().html("*");
	
	/*
	 * 验证
	 * 失焦\获焦
	 * jquery的方法传递
	 */
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
	
	saveBtn.on("click",function(){
		supContact.blur();
		supPhone.blur();
		if(supContact.attr("validateStatus") == "true" &&
				supPhone.attr("validateStatus") == "true"){
			if(confirm("是否确认提交数据")){
				$("#supplierForm").submit();
			}
		}
	});
	
	backBtn.on("click",function(){
		//alert("modify: "+referer);
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
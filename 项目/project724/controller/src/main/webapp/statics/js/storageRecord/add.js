var srCode = null;
var goodsName = null;
var goodsUnit = null;
var goodsCount = null;
var totalAmount = null;
var supplierId = null;
var addBtn = null;
var backBtn = null;

function priceReg (value){
	value = value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符
		value = value.replace(/^\./g,"");  //验证第一个字符是数字而不是.
    value = value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的.
    value = value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");//去掉特殊符号￥
	if(value.indexOf(".")>0){
		value = value.substring(0,value.indexOf(".")+3);
	}
	return value;
}


$(function(){
	srCode = $("#srCode");
	goodsName = $("#goodsName");
	goodsUnit = $("#goodsUnit");
	goodsCount = $("#goodsCount");
	totalAmount = $("#totalAmount");
	supplierId = $("#supplierId");
	addBtn = $("#add");
	backBtn = $("#back");
	//初始化的时候，要把所有的提示信息变为：* 以提示必填项，更灵活，不要写在页面上
	srCode.next().html("*");
	goodsName.next().html("*");
	goodsUnit.next().html("*");
	goodsCount.next().html("*");
	totalAmount.next().html("*");
	supplierId.next().html("*");
	
	$.ajax({
		type:"GET",//请求类型
		url:path+"/sys/supplier/simpleList",//请求的url
		dataType:"json",//ajax接口（请求url）返回的数据类型
		success:function(data){//data：返回数据（json对象）
			if(data != null){
				$("select").html("");//通过标签选择器，得到select标签，适用于页面里只有一个select
				var options = "<option value=\"0\">请选择</option>";
				for(var i = 0; i < data.length; i++){
					//alert(data[i].id);
					//alert(data[i].supName);
					options += "<option value=\""+data[i].id+"\">"+data[i].supName+"</option>";
				}
				$("select").html(options);
			}
		},
		error:function(data){//当访问时候，404，500 等非200的错误状态码
			validateTip(supplierId.next(),{"color":"red"},imgNo+" 获取供货商列表error",false);
		}
	});
	/*
	 * 验证
	 * 失焦\获焦
	 * jquery的方法传递
	 */
	srCode.on("blur",function(){
		if(srCode.val() != null && srCode.val() != ""){
			validateTip(srCode.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(srCode.next(),{"color":"red"},imgNo+" 编码不能为空，请重新输入",false);
		}
	}).on("focus",function(){
		//显示友情提示
		validateTip(srCode.next(),{"color":"#666666"},"* 请输入入库编号",false);
	}).focus();
	
	goodsName.on("focus",function(){
		validateTip(goodsName.next(),{"color":"#666666"},"* 请输入商品名称",false);
	}).on("blur",function(){
		if(goodsName.val() != null && goodsName.val() != ""){
			validateTip(goodsName.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(goodsName.next(),{"color":"red"},imgNo+" 商品名称不能为空，请重新输入",false);
		}
		
	});
	
	goodsUnit.on("focus",function(){
		validateTip(goodsUnit.next(),{"color":"#666666"},"* 请输入商品单位",false);
	}).on("blur",function(){
		if(goodsUnit.val() != null && goodsUnit.val() != ""){
			validateTip(goodsUnit.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(goodsUnit.next(),{"color":"red"},imgNo+" 单位不能为空，请重新输入",false);
		}
		
	});
	
	supplierId.on("focus",function(){
		validateTip(supplierId.next(),{"color":"#666666"},"* 请选择供货商",false);
	}).on("blur",function(){
		if(supplierId.val() != null && supplierId.val() != "" && supplierId.val() != 0){
			validateTip(supplierId.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(supplierId.next(),{"color":"red"},imgNo+" 供货商不能为空，请选择",false);
		}
		
	});
	
	goodsCount.on("focus",function(){
		validateTip(goodsCount.next(),{"color":"#666666"},"* 请输入大于0的正自然数，小数点后保留2位",false);
	}).on("keyup",function(){
		this.value = priceReg(this.value);
	}).on("blur",function(){
		this.value = priceReg(this.value);
	});
	
	totalAmount.on("focus",function(){
		validateTip(totalAmount.next(),{"color":"#666666"},"* 请输入大于0的正自然数，小数点后保留2位",false);
	}).on("keyup",function(){
		this.value = priceReg(this.value);
	}).on("blur",function(){
		this.value = priceReg(this.value);
	});
	
	addBtn.on("click",function(){
		if(srCode.attr("validateStatus") != "true"){
			srCode.blur();
		}else if(goodsName.attr("validateStatus") != "true"){
			goodsName.blur();
		}else if(goodsUnit.attr("validateStatus") != "true"){
			goodsUnit.blur();
		}else if(supplierId.attr("validateStatus") != "true"){
			supplierId.blur();
		}else{
			if(confirm("是否确认提交数据")){
				$("#storageRecordForm").submit();
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
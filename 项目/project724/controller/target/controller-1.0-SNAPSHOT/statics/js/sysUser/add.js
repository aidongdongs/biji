var realName = null;
var account = null;
var password = null;
var rpassword = null;
var phone = null;
var birthday = null;
var roleList = null;
var addBtn = null;
var backBtn = null;
var idPic = null;
var errorinfo = null
var errorinfo_wp = null;
var workPic = null;

$(function(){
	realName = $("#realName");
	account = $("#account");
	password = $("#password");
	rpassword = $("#rpassword");
	phone = $("#phone");
	birthday = $("#birthday");
	roleList = $("#roleList");
    idPic = $("#idPic");
    errorinfo = $("#errorinfo");
    workPic = $("#workPic");
    errorinfo_wp = $("#errorinfo_wp");
	addBtn = $("#add");
	backBtn = $("#back");
	//初始化的时候，要把所有的提示信息变为：* 以提示必填项，更灵活，不要写在页面上
	realName.next().html("*");
	account.next().html("*");
	password.next().html("*");
	rpassword.next().html("*");
	phone.next().html("*");
	birthday.next().html("*");
	roleList.next().html("*");
    if(errorinfo.val() == null || errorinfo.val() == ""){
        idPic.next().html("* 上传大小不能超过500K * 上传文件类型必须为：jpg、jpeg、png、pneg");
    }else{
        idPic.next().html(errorinfo.val());
    }
    if(errorinfo_wp.val() == null || errorinfo_wp.val() == ""){
        workPic.next().html("* 上传大小不能超过500K * 上传文件类型必须为：jpg、jpeg、png、pneg");
    }else{
        workPic.next().html(errorinfo_wp.val());
    }

    $.ajax({
        type:"GET",//请求类型
        url:path+"/sys/role/simpleList",//请求的url
        dataType:"json",//ajax接口（请求url）返回的数据类型
        success:function(data){//data：返回数据（json对象）
            if(data != null){
                roleList.html("");
                var options = "<option value=\"0\">请选择</option>";
                for(var i = 0; i < data.length; i++){
                    //alert(data[i].id);
                    //alert(data[i].roleName);
                    options += "<option value=\""+data[i].id+"\">"+data[i].roleName+"</option>";
                }
                roleList.html(options);
            }
        },
        error:function(data){//当访问时候，404，500 等非200的错误状态码
            validateTip(roleList.next(),{"color":"red"},imgNo+" 获取角色列表error",false);
        }
    });



	/*
	 * 验证
	 * 失焦\获焦
	 * jquery的方法传递
	 */
    account.bind("blur",function(){
        //ajax后台验证--account是否已存在
        //user.do?method=ucexist&account=**
        $.ajax({
            type:"GET",//请求类型
            url:path+"/sys/user/userExist",//请求的url
            data:{account:account.val()},//请求参数
            dataType:"json",//ajax接口（请求url）返回的数据类型
            success:function(data){//data：返回数据（json对象）
                if(data.exist == 1){//账号已存在，错误提示
                    validateTip(account.next(),{"color":"red"},imgNo + " 该用户账号已存在",false);
                }else if(data.exist == 0){//账号可用，正确提示
                    validateTip(account.next(),{"color":"green"},imgYes+" 该账号可以使用",true);
                }else if(data.exist == -1){//错误
                    validateTip(account.next(),{"color":"green"},imgNo + data.msg  ,true);
                }
            },
            error:function(data){//当访问时候，404，500 等非200的错误状态码
                validateTip(account.next(),{"color":"red"},imgNo+" 您访问的页面不存在",false);
            }
        });
    }).bind("focus",function(){
        //显示友情提示
        validateTip(account.next(),{"color":"#666666"},"* 用户编码是您登录系统的账号",false);
    }).focus();

	realName.bind("focus",function(){
		validateTip(realName.next(),{"color":"#666666"},"* 用户名长度必须是大于1小于10的字符",false);
	}).bind("blur",function(){
		if(realName.val() != null && realName.val().length > 1
				&& realName.val().length < 10){
			validateTip(realName.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(realName.next(),{"color":"red"},imgNo+" 用户名输入的不符合规范，请重新输入",false);
		}

	});

	password.bind("focus",function(){
		validateTip(password.next(),{"color":"#666666"},"* 密码长度必须是大于6小于20",false);
	}).bind("blur",function(){
		if(password.val() != null && password.val().length > 6
				&& password.val().length < 20 ){
			validateTip(password.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(password.next(),{"color":"red"},imgNo + " 密码输入不符合规范，请重新输入",false);
		}
	});

	rpassword.bind("focus",function(){
		validateTip(rpassword.next(),{"color":"#666666"},"* 请输入与上面一只的密码",false);
	}).bind("blur",function(){
		if(rpassword.val() != null && rpassword.val().length > 6
				&& rpassword.val().length < 20 && password.val() == rpassword.val()){
			validateTip(rpassword.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(rpassword.next(),{"color":"red"},imgNo + " 两次密码输入不一致，请重新输入",false);
		}
	});


	birthday.bind("focus",function(){
		validateTip(birthday.next(),{"color":"#666666"},"* 点击输入框，选择日期",false);
	}).bind("blur",function(){
		if(birthday.val() != null && birthday.val() != ""){
			validateTip(birthday.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(birthday.next(),{"color":"red"},imgNo + " 选择的日期不正确,请重新输入",false);
		}
	});

	phone.bind("focus",function(){
		validateTip(phone.next(),{"color":"#666666"},"* 请输入手机号",false);
	}).bind("blur",function(){
		var patrn=/^(13[0-9]|15[0-9]|18[0-9])\d{8}$/;
		if(phone.val().match(patrn)){
			validateTip(phone.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(phone.next(),{"color":"red"},imgNo + " 您输入的手机号格式不正确",false);
		}
	});

	roleList.bind("focus",function(){
		validateTip(roleList.next(),{"color":"#666666"},"* 请选择角色",false);
	}).bind("blur",function(){
		if(roleList.val() != null && roleList.val() > 0){
			validateTip(roleList.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(roleList.next(),{"color":"red"},imgNo + " 请重新选择角色",false);
		}
	});

	addBtn.bind("click",function(){
		/*if(account.attr("validateStatus") != "true"){
			account.blur();
		}else */if(realName.attr("validateStatus") != "true"){
			realName.blur();
		}else if(password.attr("validateStatus") != "true"){
			password.blur();
		}else if(rpassword.attr("validateStatus") != "true"){
			rpassword.blur();
		}else if(birthday.attr("validateStatus") != "true"){
			birthday.blur();
		}else if(phone.attr("validateStatus") != "true"){
			phone.blur();
		/*}else if(roleList.attr("validateStatus") != "true"){
			roleList.blur();*/
		}else{
			if(confirm("是否确认提交数据")){
				$("#userForm").submit();
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
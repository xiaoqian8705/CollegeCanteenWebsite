function doregister(){
	var _unameValue = $("#username").val();
	var _passValue = $("#password").val();
	var _passValue2 = $("#password2").val();
	var _telephone = $("#telephone").val();

	// 逻辑判断
	if(_passValue != _passValue2) {
		alert("两次输入密码不一致");
		return;
	}
	// 向服务器发送数据，注册用户
	$.ajax({
		type: "post",
		contenType: "application/json",
		url: "/login/doRegister",
		dataType: "json",
		async:false,
		data:JSON.stringify({
			phone:_telephone,
			userName:_unameValue,
			password:_passValue
		}),
		success:function(data){
			if(data.code == 1) {
				alert(data.message);
			} else {
				alert("恭喜您注册成功，请使用新账号登录");
				window.location.href = "/login.html";
			}
		},
		error:function() {
			alert("系统繁忙，请稍后再试...");
		}
	});

}

function doLogin(){
	var _unameValue = $("#username").val();
	var _passValue = $("#password").val();

	// 向服务器发送数据，注册用户
	$.ajax({
		type: "post",
		contenType: "application/json",
		url: "/login/doLogin",
		dataType: "json",
		async:false,
		data:JSON.stringify({
			userName:_unameValue,
			password:_passValue
		}),
		success:function(data){
			if(data.code == 1) {
				alert(data.message);
			} else {
				alert("登陆成功");
				window.location.href = "/index.html?userName="+_unameValue;
			}
		},
		error:function() {
			alert("系统繁忙，请稍后再试...");
		}
	});

}



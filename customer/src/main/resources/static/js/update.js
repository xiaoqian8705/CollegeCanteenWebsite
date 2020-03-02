

function updateCustomer(){
	var password = $("#password").val();
	var phone = $("#phone").val();
	var sex = $("#sex").val()=='女'?0:1;
	var school = $("#school").val();
	var grade = $("#grade").val();


	// 向服务器发送数据，注册用户
	$.ajax({
		type: "post",
		contenType: "application/json",
		url: "/updateCustomer",
		dataType: "json",
		async:false,
		data:JSON.stringify({
			password:password,
			phone:phone,
			sex:sex,
			school:school,
			grade:grade
		}),
		success:function(data){
			if(data.code == 1) {
				alert(data.message);
			} else {
				alert("修改成功");
			}
		},
		error:function() {
			alert("系统繁忙，请稍后再试...");
		}
	});

}



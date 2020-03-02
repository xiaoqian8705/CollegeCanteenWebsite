

function updateAddress(){
	var name = $("#name").val();
	var address = $("#address").val();
	var phone = $("#phone").val();
	var addressId = $("#addressId").val();


	$.ajax({
		type: "post",
		contenType: "application/json",
		url: "/updateAddress",
		dataType: "json",
		async:false,
		data:JSON.stringify({
			id:addressId,
			name:name,
			address:address,
			phone:phone

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



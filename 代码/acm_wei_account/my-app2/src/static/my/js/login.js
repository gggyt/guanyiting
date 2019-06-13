$(document).ready(function() {
	$("#message").css("display","none"); 
	$("#username").keyup(function() {
		$("#message").css("display","none");
		var text = $("#username").val();
		var textlen = $("#username").val().length;
		if(textlen>10){
            var lenText=text.substring(0,10);
            $("#username").val(lenText);
        }
	});
	$("#password").keyup(function() {
		$("#message").css("display","none");
		var text = $("#password").val();
		var textlen = $("#password").val().length;
		if(textlen>20){
            var lenText=text.substring(0,20);
            $("#password").val(lenText);
        }
	});
	$("#login").click( function() {
		var username = $("#username").val();
		var password = $("#password").val();
		if (username.length==0||password.length==0) {
			$("#message").css("display","block");
			$("#message").text("用户名或密码不能为空");
			return false;
		} 
		else if (password.length<6) {
			$("#message").css("display","block");
			$("#message").text("密码长度不足");
			return false;
		}
		else{
			var url="http://localhost:9999/userLogin/webLogin";
			$.ajax({
				type: "post",
				url: encodeURI(url),
				headers: {
					"Access-Token": "xxxxxxx",
				},
				data: "username="+$("#username").val()+"&password="+$("#password").val(),
			    
				dataType: "json",
				xhrFields: {
					withCredentials: true
				},
				crossDomain: true,
				success: function(data,status){
					var obj1 = eval(data);
					alert("xxxxxx");
					if (data['code']==0&&status==200) {
						alert(data['msg']);
					}
					else {
						alert(data['msg']);
						$("#message").css("display","block");
						$("#message").text(data['msg']);
						return;
					}
				},
				error: function(){
				  alert('fail');
				  return false;
				}
			});
		}
	});
	
});


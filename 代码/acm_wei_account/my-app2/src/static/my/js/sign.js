$(document).ready(function() {
	$("#message").css("display","none"); 
});

$(function() {
	$("#sign").click(function() {
		var mobile = $("#mobile").val();
		if (mobile.length==0) {
			$("#message").css("display","block");
			$("#message").text("请输入手机号");
			return false;
		}
		var number1 = $("#number").val();
		if (number1.length==0) {
			$("#message").css("display","block");
			$("#message").text("请输入学号");
			return false;
		}
		var password = $("#password").val();
		if (password.length==0) {
			$("#message").css("display","block");
			$("#message").text("请输入密码");
			return false;
		}
		if (password.length<6||password.length>20) {
			$("#message").css("display","block");
			$("#message").text("请控制密码长度在6-20字符");
			return false;
		}
		var repassword = $("#repassword").val();
		if (repassword.length==0) {
			$("#message").css("display","block");
			$("#message").text("请输入密码");
			return false;
		}
		if (repassword.length<6||password.length>20) {
			$("#message").css("display","block");
			$("#message").text("请控制密码长度在6-20字符");
			return false;
		}
		if(password!=repassword) {
			$("#message").css("display","block");
			$("#message").text("两次密码不一致");
			return false;
		}
		
		var pattern = /^1[345789]\d{9}$/;
		if (!pattern.test(mobile)) {
			$("#message").css("display","block");
			$("#message").text("手机格式不对");
			return false;
		} else {
			$("#message").css("display","block"); 
		}
	});
	$("#mobile").keypress(function (event) {
        var eventObj = event || e;
        var keyCode = eventObj.keyCode || eventObj.which;
        if ((keyCode >= 48 && keyCode <= 57))
            return true;
        else
            return false;
    }).focus(function () {
    //禁用输入法
        this.style.imeMode = 'disabled';
    }).bind("paste", function () {
    //获取剪切板的内容
        var clipboard = window.clipboardData.getData("Text");
        if (/^\d+$/.test(clipboard))
            return true;
        else
            return false;
    });
	$("#number").keypress(function (event) {
        var eventObj = event || e;
        var keyCode = eventObj.keyCode || eventObj.which;
        if ((keyCode >= 48 && keyCode <= 57))
            return true;
        else
            return false;
    }).focus(function () {
    //禁用输入法
        this.style.imeMode = 'disabled';
    }).bind("paste", function () {
    //获取剪切板的内容
        var clipboard = window.clipboardData.getData("Text");
        if (/^\d+$/.test(clipboard))
            return true;
        else
            return false;
    });
	$("#number").keyup(function () {
       $("#message").css("display","none");
		var text = $("#number").val();
		var textlen = $("#number").val().length;
		if(textlen>20){
            var lenText=text.substring(0,20);
            $("#number").val(lenText);
        }
    });
});
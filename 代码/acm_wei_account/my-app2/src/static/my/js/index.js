$(document).ready(function() {
	$.ajax({
		type: 'get',
		url: 'localhost:8080/tokens?username=admin&password=password',
		dataType: "json",
		beforeSend: function (xhr) {
            xhr.setRequestHeader('Access-Token', 'xxxxxxxxxxxx');
        },
		async:false,
		success: function(res) {
			$("#userName").html(res['msg']+"<b class='caret'></b>");
			//alert(res['msg']);
			 
		}
	});
});

$(document).ready(function(){
	$("li").click(function(){
		$("li").siblings().removeClass("active");
		$(this).addClass("active");
	 });
	 
});
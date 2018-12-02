$(document).ready(function(){
	var width = Number($(window).width());
	if(width > 974){
		$("body nav .nav.toggle").hide();
		$("#logo").attr("style","margin-left: 70px;");
		//$(".company-name").removeAttr("style");
	}else{
		$(".nav.toggle").show();
		$("#logo").removeAttr("style");
		//$(".company-name").attr("style","font-size:11px");
	}
});
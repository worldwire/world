layui.use(['jquery'], function(){
	$ = layui.jquery;
	if($(window).height()>$(document.body).height()+60){
			$(".foot").css("position","fixed")
			$(".foot").css("bottom","0")
		}
})

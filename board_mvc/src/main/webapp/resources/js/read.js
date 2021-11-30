/**
 * 
 */

$(function(){
	let form = $("#actionForm")
	// list를 클릭하면 전체 리스트 보여주기
	$(".btn-info").click(function(){
		location.href = "/board/list";		
	})
	// modify를 클릭하면 actionForm 보여주기
	// /board/modify
	$(".btn-default").click(function(){
		form.attr("action","/board/modify");
		form.submit();
	})
})
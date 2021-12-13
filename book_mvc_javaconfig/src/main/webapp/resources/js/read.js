/**
 * 
 */
$(function(){
	let formObj = $("#actionForm");
	
	// 목록버튼 클릭시 목록화면 보여주기
	$(".btn-secondary").click(function(){
		location.href= "/book/list";
	})
	// 삭제버튼 클릭 시  폼 전송(get)
	$(".btn-danger").click(function(){
		formObj.attr("action","/book/remove");
		formObj.submit();
	})
	// 수정버튼 클릭시 폼 전송(get)
	$(".btn-primary").click(function(){
		formObj.attr("action","/book/modify");
		formObj.submit();
	})
	
})
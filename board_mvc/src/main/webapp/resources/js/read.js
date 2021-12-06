/**
 * 
 */

$(function(){
	let form = $("#actionForm")

	// list를 클릭하면 전체 리스트 보여주기
	$(".btn-info").click(function(){
		
		// actionForm에서 bno는 제거
		form.find("input[name='bno']").remove();
		// actionForm action 수정 /board/list
		form.attr("action","/board/list");
		// actionForm 전송
		form.submit();
				
	})
	// modify를 클릭하면 actionForm 보여주기
	// /board/modify
	$(".btn-default").click(function(){
		form.attr("action","/board/modify");
		form.submit();
	})
	
	
	//댓글 작업 	
	
	// 댓글 삽입 - 
	replyService.add(
		{bno:bno,replyer:'test',reply:'댓글 작성중....'},
		 function(result){
			if(result){
				alert(result);
			}
		
	});// add end;
	
	
})
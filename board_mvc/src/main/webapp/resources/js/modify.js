/**
 * modify.jsp 스크립트
 */

$(function(){
	// remove, list 일 떄 전송될 Form
	let formObj = $("#actionForm");
	
	$("button").click(function(e){
		e.preventDefault(); //submit막기
		
		// 어느 버튼에서 명령이 왔는가? 확인을 해줘야한다 
		// data-* 왓더 퍽... 이런게 있엉?
		let oper = $(this).data("oper");
		
		if(oper=='modify'){
			formObj = $("form[role='form']");
		}else if(oper=='remove'){
			formObj.attr("action","/board/remove").attr("method","post");
		}else{
			formObj.attr("action","/board/list").attr("method","get").find("input[name='bno']").remove();
		}
		
		formObj.submit();
	})
	
})
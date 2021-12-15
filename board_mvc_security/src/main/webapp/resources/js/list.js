/**
 *  list.jsp스크립트 
 */
$(function(){
	checkModal(result);
	
	history.replaceState({}, null, null);
		
	function checkModal(){
		if(result=='' || history.state){
			return;
		}
		if(parseInt(result) >0){
			$(".modal-body").html("게시글"+parseInt(result)+"번이 등록되었습니다.");
		}
		$('#myModal').modal("show");
	}
	
	// 페이지 나누기 스크립트
	let actionForm = $("#actionForm");
	// 번호 클릭시 클릭된 번호 값 가져오기 
	$(".pageinate_button a").click(function(e){
		
		// a 태그의 속성(페이지 이동)을 중지
		e.preventDefault(); // 속성 중지
		
		// 번호 값 가져오기 
		let pageNum = $(this).attr("href");
		
		// 가져온 번호를 actionForm 안의 pageNum 값으로 대체
		actionForm.find("input[name='pageNum']").val(pageNum);
		
		// action 수정
		actionForm.attr("action", "/board/list");
		// bno제거
		actionForm.find("input[name='bno']").remove();
		
		
		
		// 폼전송
		actionForm.submit();
	})
	
	//amount 값이 변경이되면 
	$("#amount").change(function(){
		// amount 값 가져오기
		let amount = $(this).val();		
		$(this).attr("option",$(this).val());
		// 가져온 값을 actionForm에 amount 값 수정하기
		actionForm.find("input[name='amount']").val(amount);
		// bno제거
		actionForm.find("input[name='bno']").remove();
		// actionForm 보내기
		actionForm.submit();
	})
	
	// 글제목 클릭시
	$(".move").click(function(e){
		// a 태그 속성 중지
		e.preventDefault();
		
		let bno = $(this).attr('href');
		
		//actionForm 에 bno 값을 추가한다.
		//actionForm.append("<input type='hidden' name='bno' value='"+bno+"'>");
		actionForm.find("input[name='bno']").val(bno);
		//actionForm action 설정 /board/read 
		actionForm.attr("action","/board/read");
		
		// actionFrom 보내기
		actionForm.submit();
	})
	
	// 검색버튼 클릭시
	$(".btn-default").click(function(e){
		e.preventDefault();
		let type = $("select[name='type']").val();
		let keyword = $("input[name='keyword']").val();
		//type 값이 들어있는지 확인
		// 값이 없는 경우 메세지를 띄우고 돌아가기
		if(type===''){
			alert("type 체크해");
			return;
		}else if(keyword===''){
			//keyword 값이 없는 경우 메세지 띄우고 돌아가기
			alert("keyword체크해 ");
			return;
		}
		
		// pageNum의 값을 1로 변경하기
		$("#searchForm").find("input[name='pageNum']").val("1");
		
		// 검색 폼 보내기
		$("#searchForm").submit();
	})
	
	
	
	
	
	
})
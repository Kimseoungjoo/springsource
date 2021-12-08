/**
 *  uplaodFormAjax.jsp 와 관련된 js
 */
$(function() {
	$("#uploadBtn").click(function(e) {
		e.preventDefault();
		
		console.log("업로드 요청");
		
		// FormData 객체 생성 - ajax 형태로 데이터를 보낼 때
		// key/value 형태로 쌍을 생성해 줌
		var formData = new FormData();
		
		// 첨부 파일 목록 가져오기 
		var inputFile = $("input[name='uploadFile']");
		
		//console.log(inputFile);
		
		var files = inputFile[0].files;
		
		// 가져온  목록 formData에 추가하기
		
		for(var i=0;i<files.length;i++){
			if(!checkExtension(files[i].name,files[i].size)){
				return false;
			}
			formData.append("uploadFile",files[i]);
		}
		
		// processData:false : (data를 query string 형태로 변환할 것인가?) 
		//*query string : http://localhost:8080/upload?)name=~~~~~??) << 이부분을 말한다.
		// contentType:false : (application/x-www-form-urlencoded로 보낼 것인가?) 		  
		
		$.ajax({
			url:'/uploadAjax',
			type:'post',
			processData:false, // 꼭 들어와야하는 코드
			contentType:false,
			data:formData,
			success:function(result){
				console.log(result);
				showUploadFile(result);
			},
			error:function(xhr,status,error){
				console.log(xhr.responseText);
			}
			
		})
	})//uploadBtn END
	
	// 첨부된 파일 목록 보여주기 
	function showUploadFile(uploadResultArr){
		
		// 첨부파일 결과를 보여줄 영역 가져오기 
		var uploadResult = $(".uploadResult ul");
		
		var str = "";
		$(uploadResultArr).each(function(idx,obj){
			if(obj.fileType){
				str += "<li>";
				str += "<img src=''>"+obj.fileName+"</li>";
				
			}else{
				str += "<li>";
				str += "<img src='/resources/img/attach.png'>"+obj.fileName+"</li>";
			}
		})
		uploadResult.append(str);
		

	}
	
	
	function checkExtension(fileName,fileSize){
		var regex = new RegExp("(.*?)\.(txt|jpg|png|gif|hwp|exe)$"); // regexp 확장명 확인 
		var maxSize = 2097152; // 2MB
		
		if(fileSize > maxSize){
			alert("파일 사이즈 초과");
			return false;
		}
		if(!regex.test(fileName)){
			alert("해당 종류의 파일은 업로드 할 수 없습니다.");
			return false;
		}
		return true;
	}
})

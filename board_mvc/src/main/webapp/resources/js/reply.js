/**
 * reply와 관련된 script
 */

// 자바 스크립트 모듈화
let replyService = (function(){
	
	function add(reply,callback){
		console.log("add method 실행");
		
		$.ajax({
			url:'/replies/new',
			type:'post',
			contentType:'application/json',
			data:JSON.stringify(reply),
			success:function(result){
				if(callback){
					callback(result);
				}
			},
			error:function(xhr,status,err){
				error(err);
			}
		})
	}
	
	return {add:add};
	
})();
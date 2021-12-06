/**
 *  /rest/leave.jsp 회원 탈퇴
 */

$(function(){
	$(":submit").click(function(e){
		e.preventDefault();
		
		let param = {
			userid:$("#userid").val(),
			password:$("#password").val()
		}
		
		$.ajax({
			url:'leave',
			type:'delete',
			contentType:'application/json',
			data:JSON.stringify(param),
			success:function(data){
				alert(data);
			},
			error:function(xhr,status,error){
				alert(xhr,responseText);
			}
			
		})
		
		
		
		
		
	})
})
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>main page</h1>
<%-- isAuthenticated() : 인증된 사용자의 경우 true --%>
<sec:authorize access="!isAuthenticated()">
<p>
	<a href="/login">login</a>
</p>
</sec:authorize>	

<sec:authorize access="isAuthenticated()">
<p>
	<a href="/user-page">userPage</a>
</p>
</sec:authorize>	

<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var ="info"/>
	<c:if test="${info.username=='admin'}">
		<p>
			<a href="/admin-page">adminPage</a>
		</p>
	</c:if>
</sec:authorize>	

<sec:authorize access="isAuthenticated()">
<div>
	<form action="/logout" method = "post">
		<button>logout</button>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
</div>
</sec:authorize>

</body>
</html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
	<title>Home</title>
	<link href="<c:url value="/resources/css/login.css" />" rel='stylesheet' type='text/css'>
</head>
<body>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%-- <form:form commandName="user">
	<div class="box">
	<h1>SIGN IN</h1>
		<form:input path="username" placeholder="username" class="email"/>
			<div><form:errors path="username"/></div>
		<form:input path="password" placeholder="password" class="email"/>
			<div><form:errors path="password"/></div>
		<input type="submit" class="btn" value="Sign In"/>
		
		<a href="${contextPath}/signUp"><div id="btn2">Sign UP</div></a>
	</div>
</form:form> --%>
<h1>FANTASY PREMIER LEAGUE</h1>
<form method="post" action="${contextPath}/signIn">
<div class="box">
<h1>SIGN IN</h1>
	
	
	<input type="username" name="username"  placeholder="email" class="email" required />
	  
	<input type="password" name="password" placeholder="password" class="email" required />
	
	<input type="submit" class="btn" value="Sign IN"/>
	
	<a href="${contextPath}/signUp"><div id="btn2">Sign Up</div></a> <!-- End Btn2 -->
	  
	</div> <!-- End Box -->
  
</form>

	<script type="text/javascript" src="<c:url value="/resources/scripts/fplApi.js" />"></script>
</body>
</html>

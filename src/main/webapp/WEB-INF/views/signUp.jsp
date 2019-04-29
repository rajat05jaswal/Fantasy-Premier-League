<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
	<title>Home</title>
	<link href="<c:url value="/resources/css/login.css" />" rel='stylesheet' type='text/css'>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<h1>FANTASY PREMIER LEAGUE</h1>
<form:form commandName="user">
	<div class="box">
	<h1>SIGN UP</h1>
		<form:input path="name" placeholder="name" class="email"/>
			<div><form:errors path="name"/></div>
		<form:input path="username" placeholder="username" class="email"/>
			<div><form:errors path="username"/></div>
		<form:input type="password" path="password" placeholder="password" class="email"/>
			<div><form:errors path="password"/></div>
		<input type="submit" class="btn" value="Sign Up"/>
		
		<a href="${contextPath}/signIn"><div id="btn2">Sign In</div></a>
	</div>
</form:form>
<%-- <form method="post" action="${contextPath}/user">
<div class="box">
<h1>SIGN UP</h1>
	
	<input type="text" name="name"  placeholder="name" class="email" />
	
	<input type="username" name="username"  placeholder="email" class="email" />
	  
	<input type="password" name="password" placeholder="password" class="email" />
	
	<input type="submit" class="btn" value="Sign Up"/>
	
	<a href="${contextPath}/user"><div id="btn2">Sign In</div></a> <!-- End Btn2 -->
	  
	</div> <!-- End Box -->
  
</form> --%>
</body>
</html>

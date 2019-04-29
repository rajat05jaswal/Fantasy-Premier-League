<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Admin Register</title>

	<link href="<c:url value="/resources/css/admin.css" />" rel='stylesheet' type='text/css'>
</head>
<%@ page import="com.fpl.pojo.Position" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<body>
	<div class="main-wrapper">
		<div class="left-wrapper">
			<div>
				<a href="${contextPath}/addPlayers">Add Players</a>
			</div>
			<div>
				<a href="${contextPath}/addTeams">Add Teams</a>
			</div>
			
			<div>
				<a href="${contextPath}/registerPoints">Register Points</a>
			</div>
			<div>
				<a href="${contextPath}/generateMatches">Generate Matches</a>
			</div>
		</div>
		<div class="right-wrapper">
			<div class="players">
				<h2>Register Team</h2>
				<h4>${message}</h4>
				<form method="post" action="${contextPath}/addTeams">
					<div>
						Team Name : <input type="text" name="name" required/>
					</div>
					<div>
						<input type="submit" value="Register Team"/>
					</div>
				</form>
			</div>
			<div>
				<h2><a href="logout">Logout</a></h2>
			</div>
		</div>
	</div>
</body>	
</html>
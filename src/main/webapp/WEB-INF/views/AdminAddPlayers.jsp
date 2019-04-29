<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
	<link href="<c:url value="/resources/css/admin.css" />" rel='stylesheet' type='text/css'>
</head>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<%@ page import="com.fpl.pojo.Position" %>
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
		</div>
		<div class="right-wrapper">
			<div class="players">
				<h2>Register Players</h2>
				<h4>${message}</h4>
				<form method="post" action="addPlayers">
					<div>
						Player Name : <input type="text" name="name" required/>
					</div>
					<div>
						Player Team : 
						<select name="team">
							<c:forEach items="${teams}" var="team">
								<option value="${team.name}">${team.name}</option>
							</c:forEach>
						</select>
					</div>
					<div>
						Player POSITION : 
						<select name="position">
							<c:forEach items="${Position.values()}" var="position">
								<option value="${position}">${position}</option>
							</c:forEach>
						</select>
					</div>
					<div>
						Player Price : <input type="text" name="price" required/>
					</div>
					<div>
						<input type="submit" value="Register Player"/>
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
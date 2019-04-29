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
				<h2>Register Matches</h2>
				<form method="post" action="${contextPath}/generateMatches">
					<h3>Current Week is ${currentWeek}</h3>
					<c:if test="${generatedAlready==false}">
						<div>
								Click Below to generate Matches for currentWeek
							</div>
							<div>
								<input type="submit" id="generateMatches" value="Generate Matches for current Week" />
							</div>
						</div>
					</c:if>
				</form>
			</div>
			<div class="matches">
				<ul>
					<c:forEach items="${matches}" var="match">
						<li>${match.hometeam.name} VS ${match.awayTeam.name}</li>
					</c:forEach>
				
				</ul>
			</div>
		</div>
	</div>
	<div>
		<h2><a href="logout">Logout</a></h2>
	</div>
</body>	
</html>
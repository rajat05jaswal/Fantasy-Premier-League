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
			<h2>${message}</h2>
			<h3>Current week is ${week}</h3>
		</div>
		<div>
			<h2><a href="logout">Logout</a></h2>
		</div>
		<div class="pdfLink">
			<a href="downloadPdf">Click Here to Download the Pdf</a>
		</div>	
	</div>
</body>	
</html>
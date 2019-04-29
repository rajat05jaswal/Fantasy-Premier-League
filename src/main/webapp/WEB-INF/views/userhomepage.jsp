<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Home Page</title>
<link href="<c:url value="/resources/css/user.css" />" rel='stylesheet' type='text/css'>
</head>
<body>
	<h1 class="welcome">Welcome user <input type="hidden" id="username" value="${id}">${username}</span></h1>
	<div class="logout">
			<h2><a href="logout">Logout</a></h2>
		</div>
	<div>
		<form method="post" action="${contextPath}/userTeam">
		<h2>Current game week is ${week}</h2>
		<h3>Total Budget is <span>70$</span></h3>
		<h2>Bank <span id="squad-bank">70</span>$</h2>
		<div>Points</div>
		<div>${userteam.points}</div>
		<input type="hidden" value="70" id="bank"/>
		<div class="squad-wrapper">
			<c:choose>
			<c:when test="${userTeam==null}">
				<div class="goalKeeper box add">
					<button class="add"></button>
				</div>
				<div>
					<div class="defender box add">
						<button class="add"></button>
					</div>
					<div class="defender box add">
						<button class="add"></button>
					</div>
					<div class="defender box add">
						<button class="add"></button>
					</div>
					<div class="defender box add">
						<button class="add"></button>
					</div>
				</div>
				<div>
					<div class="midFielder box add">
						<button class="add"></button>
					</div>
					<div class="midFielder box add">
						<button class="add"></button>
					</div>
					<div class="midFielder box add">
						<button class="add"></button>
					</div>
				</div>
				<div>
					<div class="attacker box add">
						<button class="add"></button>
					</div>
					<div class="attacker box add">
						<button class="add"></button>
					</div>
					<div class="attacker box add">
						<button class="add"></button>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<c:forEach items="${goalKeeper}" var="goalKeeper">
					<div class="goalKeeper box">
						<p>${goalKeeper.name}</p>
						<input type="hidden" name="team-player" value="${goalKeeper.playerId}">
					</div>
				</c:forEach>
				<c:forEach var="i" begin="1" end="${1-goalKeeper.size()}">
					<div class="goalKeeper box add">
						<button class="add"></button>
					</div>
				</c:forEach>
				<div>
					<c:forEach items="${defenders}" var="defender">
						<div class="defender box">
							<p>${defender.name}</p>
							<input type="hidden" name="team-player" value="${defender.playerId}">
						</div>
					</c:forEach>
					<c:forEach var="i" begin="1" end="${4-defenders.size()}">
						<div class="defender box add">
							<button class="add"></button>
						</div>
					</c:forEach>
				</div>
				<div>
					<c:forEach items="${midfielders}" var="midFielder">
						<div class="midFielder box">
							<p>${midFielder.name}</p>
							<input type="hidden" name="team-player" value="${midFielder.playerId}">
							<button class="remove-player">X</button>
						</div>
					</c:forEach>
					<c:forEach var="i" begin="1" end="${3-midfielders.size()}">
						<div class="midFielder box add">
							<button class="add"></button>
						</div>
					</c:forEach>
				</div>
				<div>
					<c:forEach items="${attackers}" var="attacker">
						<div class="attacker box">
							<p>${attacker.name}</p>
							<input type="hidden" name="team-player" value="${attacker.playerId}">
						</div>
					</c:forEach>
					<c:forEach var="i" begin="1" end="${3-attackers.size()}">
						<div class="attacker box add">
							<button class="add"></button>
						</div>
					</c:forEach>
				</div>
			</c:otherwise>
		</c:choose>
		
		<div class="overlay">
			<div class="playerPos"></div>
			<div class="playerData">
				<table >
				</table>
			</div>
			<div>
				<input id="save" type="button" value="Save">
				<input id="cancel" type="button" value="Cancel">
			</div>
		</div>
		<div class="overlay-info">
			<div class="playerInfo"></div>
			<div>
				<input id="cancel-browse" type="button" value="Close">
				<input id="remove-player" type="submit" value="Remove Player" />
			</div>
		</div>
		</div>
		<div>
			<div><h4>SET YOUR CAPTAIN, WILL RECEIVE 2*X POINTS</h4>
			<select name="captain" id="captain">
				<c:forEach items="${userTeam.userteam}" var="player">
					<option value="${player.playerId}">${player.name}</option>
				</c:forEach>
			</select>
			</div>
			<input type="submit" value="Save Team" />
		</div>
		</form>
	</div>
	
	<script type="text/javascript" src="<c:url value="/resources/scripts/user.js" />"></script>
</body>
</html>
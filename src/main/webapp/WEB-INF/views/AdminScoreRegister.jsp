<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Admin Score</title>

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
				<h2>Register Teams</h2>
				<%-- <form method="post" action="${contextPath}/registerPoints" id="matches-table"> --%>
				<form id="matches-table" action="${contextPath}/registerPoints" method="post">
					<h3>Set Score</h3>
					<h2>Current week is ${week}</h2>
					<table>
							<tr>
								<th>Home Team</th>
								<th>Away Team</th>
								<th>Analyse Match</th>
							</tr>
							<c:forEach items="${matches}" var="match">
								<tr>
									<td>${match.hometeam.name}</td>
									<td>${match.awayTeam.name}</td>
									<td>
										<c:choose>
											
											<c:when test="${match.analysisDone==true}">
												DONE
											</c:when>
											<c:otherwise>
												<button class="analyse-match" value="${match.id}">Analyse Match</button>
											</c:otherwise>
										</c:choose>
									</td>
								</tr>
							</c:forEach>
					
					</table>
					<c:if test="${weekAnalysisDone==true}"><input type="submit" value="Finish Scoring ${week}" /></c:if>
				</form>
				<div id="points-wrapper">
					<form method="post" action="${contextPath}/scoreViaMatch">
					<div>
						<div><h3> Register SCORE</h3></div>
						<div class="home-team">
							<h4><span class="home-team-name"></span></h4>
							<select name="home-score">
								<option value="0">0</option>
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
							</select>
						</div>
						<div class="away-team">
							<h4><span class="away-team-name"></span></h4>
							<select name="away-score">
								<option value="0">0</option>
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
							</select>
						</div>
					</div>
					<hr />
					<div>
						<div><h3>WHO SCORED ?</h3></div>
						<div class="home-team">
							<h4><span class="home-team-name"></span></h4>
							<div id="home-goal-scorers">
							</div>
						</div>
						<div class="away-team">
							<h4><span class="away-team-name"></span></h4>
							<div id="away-goal-scorers">
							</div>
						</div>
					</div>
					<hr />
					<div>
						<div><h3>Yellow Cards ?</h3></div>
						
						<div class="home-team">
							<h4><span class="home-team-name"></span></h4>
							<div><h4>How Many Yellow Cards? </h4>
								<select name="home-yellow-card-count">
									<option value="0">0</option>
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
								</select>
							</div>
							<div id="home-yellow-cards">
							</div>
						</div>
						<div class="away-team">
							<h4><span class="away-team-name"></span></h4>
							<div><h4>How Many Yellow Cards? </h4>
								<select name="away-yellow-card-count">
									<option value="0">0</option>
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
								</select>
							</div>
							<div id="away-yellow-cards">
							</div>
						</div>
					</div>
					<hr />
					<div>
						<div><h3>RED Cards ?</h3></div>
						<div class="home-team">
							<h4><span class="home-team-name"></span></h4>
							<div><h4>How Many RED Cards? </h4>
								<select name="home-red-card-count">
									<option value="0">0</option>
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
								</select>
							</div>
							<div id="home-red-cards">
							</div>
						</div>
						<div class="away-team">
							<h4><span class="away-team-name"></span></h4>
							<div><h4>How Many RED Cards? </h4>
								<select name="away-red-card-count">
									<option value="0">0</option>
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
								</select>
							</div>
							<div id="away-red-cards">
							</div>
						</div>
					</div>
					<div>
						<input type="hidden" name="matchId" id="match-id" />
						<input type="submit" value="Done" />
					</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div>
		<h2><a href="logout">Logout</a></h2>
	</div>
	
	<script type="text/javascript" src="<c:url value="/resources/scripts/admin.js" />"></script>
</body>	
</html>
/**
 * 
 */
var analysisButton=document.getElementsByClassName("analyse-match");
for(var i=0;i<analysisButton.length;i++){
	analysisButton[i].addEventListener('click',analyseClick);
}
var homeScore=document.querySelector("select[name='home-score'");
var awayScore=document.querySelector("select[name='away-score'");

var homeYellowCardBtn = document.querySelector("select[name='home-yellow-card-count'");
var awayYellowCardBtn = document.querySelector("select[name='away-yellow-card-count'");
var homeRedCardBtn= document.querySelector("select[name='home-red-card-count'");
var awayRedCardBtn= document.querySelector("select[name='away-red-card-count'");

var homeTeamPlayers;
var awayTeamPlayers;

homeYellowCardBtn.addEventListener('change',(e)=>{populateCardPlayers(e,homeTeamPlayers,"home-yellow-card", document.getElementById('home-yellow-cards'))});
awayYellowCardBtn.addEventListener('change',(e)=>{populateCardPlayers(e,awayTeamPlayers,"away-yellow-card", document.getElementById('away-yellow-cards'))});
homeRedCardBtn.addEventListener('change',(e)=>{populateCardPlayers(e,homeTeamPlayers,"home-red-card", document.getElementById('home-red-cards'))});
awayRedCardBtn.addEventListener('change',(e)=>{populateCardPlayers(e,awayTeamPlayers,"away-red-card", document.getElementById('away-red-cards'))});

function populateCardPlayers(e,players, type, wrapper){
	var scoreValue=e.target.value;
	wrapper.innerHTML="";
	var playerString='';
	console.log(players);
	for(var i=0;i<players.length;i++){
		playerString+=`<option value="${players[i].playerId}">${players[i].name}</option>`;
	}
	wrapper.innerHTML="<ul>";
	for(var i=0;i<scoreValue;i++){
		wrapper.innerHTML+=
			`<li><select name="${type}">${playerString}</select></li>`;
	}
}

homeScore.addEventListener("change",(e)=>{
	var homeScoreValue=e.target.value;
	document.getElementById('home-goal-scorers').innerHTML="";
	var playerString='';
	for(var i=0;i<homeTeamPlayers.length;i++){
		playerString+=`<option value="${homeTeamPlayers[i].playerId}">${homeTeamPlayers[i].name}</option>`;
	}
	document.getElementById('home-goal-scorers').innerHTML="<ul>";
	for(var i=0;i<homeScoreValue;i++){
		document.getElementById('home-goal-scorers').innerHTML+=
			`<li><select name="home-goal-scorer">${playerString}</select></li>`;
	}
	document.getElementById('home-goal-scorers').innerHTML+="</ul>";
});

awayScore.addEventListener("change",(e)=>{
	var awayScoreValue=e.target.value;
	document.getElementById('away-goal-scorers').innerHTML="";
	var playerString='';
	console.log(awayTeamPlayers);
	for(var i=0;i<awayTeamPlayers.length;i++){
		playerString+=`<option value="${awayTeamPlayers[i].playerId}">${awayTeamPlayers[i].name}</option>`;
	}
	console.log(playerString);
	document.getElementById('away-goal-scorers').innerHTML="<ul>";
	for(var i=0;i<awayScoreValue;i++){
		document.getElementById('away-goal-scorers').innerHTML+=
			`<li><select name="away-goal-scorer">${playerString}</select></li>`;
	}
	document.getElementById('away-goal-scorers').innerHTML+="<ul>";
});

function analyseClick(e){
	e.preventDefault();
	var matchId=e.target.value;
	var xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function(res) {
		if(this.readyState == 3){
		}
	    if (this.readyState == 4 && this.status == 200) {
	       // Typical action to be performed when the document is ready:
	    	document.getElementById("points-wrapper").style.display="block";
	    	var match=JSON.parse(xhttp.response);
	    	populatePointBox(match);
	    	console.log(match);
	    	document.getElementById("match-id").value=match.id;
	    	getPlayersViaTeam(match.awayTeam.name, "away");
	    	getPlayersViaTeam(match.hometeam.name, "home");
	    }
	};
	xhttp.open("GET", "http://localhost:8080/fpl/getMatch/"+matchId, true);
	xhttp.send();
	
}

function getPlayersViaTeam(teamName, teamPlayerMain){
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function(res) {
		if(this.readyState == 3){
			console.log("Loading");
		}
	    if (this.readyState == 4 && this.status == 200) {
	       // Typical action to be performed when the document is ready:
	    	var players=JSON.parse(xhttp.response);
	    	if(teamPlayerMain=="away"){
	    		awayTeamPlayers=players;
	    	}else if(teamPlayerMain=="home"){
	    		homeTeamPlayers=players;
	    	}
	    }
	};
	xhttp.open("GET", "http://localhost:8080/fpl/getPlayersByTeam/"+teamName, true);
	xhttp.send();
}

function populatePointBox(match){
	var awayTeamName=document.getElementsByClassName("away-team-name");
	var homeTeamName=document.getElementsByClassName("home-team-name");
	for(var i=0;i<awayTeamName.length;i++){
		awayTeamName[i].innerHTML=match.awayTeam.name;
		homeTeamName[i].innerHTML=match.hometeam.name;
	}
}


/**
 * 
 */
var overlay=document.getElementsByClassName('overlay')[0];
var overlayInfo=document.getElementsByClassName('overlay-info')[0];
var currentTeam=[];
var username=document.getElementById("username").value;
var playersFromController={};
getUserTeam(username);
function getUserTeam(userId){
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function(res) {
		if(this.readyState == 3){
			console.log("Loading");
		}
	    if (this.readyState == 4 && this.status == 200) {
	       // Typical action to be performed when the document is ready:
	    	var players=JSON.parse(xhttp.response);
	    	document.getElementById("captain").innerHTML="<option>Select Your Captain</option>";
	    	playersFromController=players;
	    	var priceOfTeam=0;
	    	for(var i=0;i<players.length;i++){
	    		currentTeam.push(players[i].playerId);
	    		priceOfTeam+=players[i].price;
		    	document.getElementById("captain").innerHTML+=`<option value="${players[i].playerId}">${players[i].name}</option>`;
	    	}
	    	document.getElementById("squad-bank").innerHTML=(70-priceOfTeam);
	    	document.getElementById("bank").value=(70-priceOfTeam);
	    	console.log(players);
	    }
	};
	xhttp.open("GET", "http://localhost:8080/fpl/getUserTeam/"+userId, true);
	xhttp.send();
}
function savePlayer(id){
	if(currentTeam.includes(parseInt(id))){
		alert("Player already exists in the team!! Select other");
		return;
	}
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function(res) {
		if(this.readyState == 3){
		}
	    if (this.readyState == 4 && this.status == 200) {
	       // Typical action to be performed when the document is ready:
	    	
	    	
	    	var player=JSON.parse(xhttp.response);
	    	var box=document.querySelector('.box.opened');
	    	if(box==null) return;
	    	console.log(player);
	    	box.innerHTML=`<p>${player.name}</p><input type="hidden" name="team-player" value="${player.playerId}"/>`;
	    	box.classList.remove("opened");
	    	box.classList.remove("add");
	    	overlay.classList.remove('show');
	    	box.removeEventListener("click",()=>{
	    		console.log("closed");
	    	});
	    	currentTeam.push(player.playerId);
	    	playersFromController.push(player);
	    	document.getElementById("captain").innerHTML+=`<option value="${player.playerId}">${player.name}</option>`;
	    	console.log(currentTeam);
	    	var bankValue=document.getElementById("bank").value;
	    	document.getElementById("bank").value=bankValue-player.price;
	    	document.getElementById("squad-bank").innerHTML=document.getElementById("bank").value;
	    	attachEventListeners();
	    }
	};
	xhttp.open("GET", "http://localhost:8080/fpl/getPlayerById/"+id, true);
	xhttp.send();
}
function selectBox(e){
	e.preventDefault();
	var left=e.target.offsetLeft;
	var top=e.target.offsetTop;
	overlay.classList.add("show");
	overlay.style.left=left+"px";
	overlay.style.top=top+"px";
	
	var pos;
	if(e.target.parentElement.classList.contains("defender")){
		pos="DEF";
	}else if(e.target.parentElement.classList.contains("goalKeeper")){
		pos="GK";
	}else if(e.target.parentElement.classList.contains("midFielder")){
		pos="MID";
	}else if(e.target.parentElement.classList.contains("attacker")){
		pos="ATT";
	}
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function(res) {
		if(this.readyState == 3){
			console.log("Loading");
		}
	    if (this.readyState == 4 && this.status == 200) {
	       // Typical action to be performed when the document is ready:
	    	var players=JSON.parse(xhttp.response);
	    	console.log(players);
	    	populateOverlay(players,pos);
	    	e.target.parentElement.classList.add('opened');
	    }
	};
	xhttp.open("GET", "http://localhost:8080/fpl/getPlayersByPosition/"+pos, true);
	xhttp.send();
	
	var html="<div class='overlay'></div>";
}
function populateOverlay(players,pos){
	var overlayPlayer=document.querySelector(".overlay .playerData table");
	overlayPlayer.innerHTML="";
	for(var i=0;i<players.length;i++){
		overlayPlayer.innerHTML+=`<tr><td><input type="radio" name="player" value="${players[i].playerId}" />${players[i].name}</td> <td>${players[i].price}</td></tr>`;
	}
}
function browsePlayer(e){
	e.preventDefault();
	var left=e.target.offsetLeft;
	var top=e.target.offsetTop;
	overlayInfo.classList.add("show");
	overlayInfo.style.left=(left-20)+"px";
	overlayInfo.style.top=(top-20)+"px";
	if(e.target.parentElement.classList.contains("box")){
		e.target.parentElement.classList.add("opened-browse");
	}else if(e.target.classList.contains("box")){
		e.target.classList.add("opened-browse");
	}
	var id=e.target.parentElement.children[1].value ? e.target.parentElement.children[1].value : e.target.children[1].value;
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function(res) {
		if(this.readyState == 3){
			console.log("Loading");
		}
	    if (this.readyState == 4 && this.status == 200) {
	       // Typical action to be performed when the document is ready:
	    	var player=JSON.parse(xhttp.response);
	    	console.log(player);
	    	var htmlString="<table>";
	    	htmlString+=`<tr><td>Player Name</td><td>${player.name}</td></tr>`;
	    	htmlString+=`<tr><td>Player Price</td><td>${player.price}</td></tr>`;
	    	htmlString+=`<tr><td>Player Team</td><td>${player.plTeam.name}</td></tr></table>`;
	    	htmlString+=`<input type="hidden" id="player-hidden" value="${player.playerId}"/>`;
	    	overlayInfo.firstElementChild.innerHTML=htmlString;
	    }
	};
	xhttp.open("GET", "http://localhost:8080/fpl/getPlayerById/"+id, true);
	xhttp.send();
}
attachEventListeners();
function attachEventListeners(){
	var boxesAdd=document.querySelectorAll('.box.add .add');
	var boxes=document.querySelectorAll('.box:not(.add)');

	for(var i=0;i<boxes.length;i++){
		boxes[i].addEventListener('click',browsePlayer);
	}
	for(var i=0;i<boxesAdd.length;i++){
		boxesAdd[i].addEventListener('click',selectBox);
	}
}
function removeEventListeners(){
	var boxesAdd=document.querySelectorAll('.box.add .add');
	var boxes=document.querySelectorAll('.box:not(.add)');

	for(var i=0;i<boxes.length;i++){
		boxes[i].removeEventListener('click',browsePlayer);
	}
	for(var i=0;i<boxesAdd.length;i++){
		boxesAdd[i].removeEventListener('click',selectBox);
	}
}

document.getElementById("cancel").addEventListener('click',function(e){
	e.preventDefault();
	overlay.classList.remove('show');
	document.querySelector('.box.opened').classList.remove("opened");
})
document.getElementById("cancel-browse").addEventListener('click',function(e){
	e.preventDefault();
	document.querySelector(".box.opened-browse").classList.remove("opened-browse");
	overlayInfo.classList.remove('show');
})
document.getElementById("save").addEventListener("click",function(e){
	e.preventDefault();
	if(document.querySelector('.playerData input[name="player"]:checked')===null){
		alert("Select a player First");
		return;
	}
	var playerId=document.querySelector('.playerData input[name="player"]:checked').value;
	
	savePlayer(playerId);
})

document.getElementById("remove-player").addEventListener("click",function(e){
	e.preventDefault();
	removePlayer();
})

function removePlayer(){
	var playerToRemove=parseInt(document.getElementById("player-hidden").value);
	
	currentTeam.splice(currentTeam.indexOf(playerToRemove),1);
	
	var indexToRemove=0;
	var price=0;
	for(var i=0;i<playersFromController.length;i++){
		if(playersFromController[i].playerId==playerToRemove){
			price=playersFromController[i].price;
			indexToRemove=i;
		}
	}
	var bankValue=Number(document.getElementById("bank").value);
	document.getElementById("bank").value=bankValue+price;
	document.getElementById("squad-bank").innerHTML=document.getElementById("bank").value;
	
	document.getElementById("captain").innerHTML="";
	playersFromController.splice(indexToRemove,1);
	for(var i=0;i<playersFromController.length;i++){
		var player=playersFromController[i];
		document.getElementById("captain").innerHTML+=`<option value="${player.playerId}">${player.name}</option>`;
	}
	document.querySelector(".box.opened-browse").innerHTML=`<button class="add"></button>`;
	document.querySelector(".box.opened-browse").classList.add("add");
	document.querySelector(".box.opened-browse").removeEventListener("click", browsePlayer);
	document.querySelector(".box.opened-browse").classList.remove("opened-browse");
	overlayInfo.classList.remove('show');
	attachEventListeners();
	console.log(currentTeam);
	console.log(playersFromController);
	
}


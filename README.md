# Fantasy-Premier-League
SUMMARY
Project is an implementation of a Fantasy Premier League where each user is provided 70$ of virtual money and he is given a set of 200 players from 10 different teams to choose a team of 11 players from. Each team comprises of 1 GK, 4 DEF, 3 MID, 3 ATT.

FUNCTIONALITY PERFORMED
Creation, Updating, Retrieval and Deletion of an entity based on different roles and inter dependence. 
I have generated a PDF view for all the players and their respective points.
Implemented session management in the project.
Implemented AJAX for smooth user interface and experience

TECHNOLOGIES USED
1) Backend - Spring MVC with Hibernate for persistence
2) Frontend – HTML, CSS, Javascript
3) Databases – MySQL

ROLES
Login as an Admin
•	An admin logs in and sets the price of each player (around 100 players) from the premier league from 10 teams (1 team=5 players)
•	Admin logs in and generates matches, also generates scores for each different match.
•	Points are set per player, if a player scores a goal, 5 points are allotted to him, -2 for a yellow card and -4 for a red card.
•	As soon as all matches are analyzed (5 matches for 10 teams) in a week. Admin has a power to change the week. 

Login as a User

•	User logs in and selects 11 players per week with a budget of 70$(virtual money)
•	User cannot have more than 3 players from one single Premier League Team
•	After 1-week admin sets points to each player (similar to live score and how each player performs)
•	User receive scores per week 
•	User selects a team and can also set a captain of his team. A captain receives 2X points for what ever points are being allotted to him in the current week.

SCREENSHOTS

Sign In
 



Admin Main View Page - ADMIN

 
Matches Generated Page – ADMIN

 


REGISTER POINTS - ADMIN

 


WELCOME PAGE – USER

 

DEFENDERS LISTED AS AN OVERLAY - USER

 
BUDGET DECREASING ON SELECTING A PLAYER -USER
 

BROWSING A PLAYER -USER

 
PDF VIEW
 


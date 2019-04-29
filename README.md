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
![image](https://user-images.githubusercontent.com/17830317/56876665-04de9980-6a17-11e9-9e81-cdc83a1e8873.png)
 
 
Admin Main View Page - ADMIN
![image](https://user-images.githubusercontent.com/17830317/56876759-9ea64680-6a17-11e9-8cd2-8c2817d3596d.png)


Matches Generated Page – ADMIN
![image](https://user-images.githubusercontent.com/17830317/56876766-ac5bcc00-6a17-11e9-8565-93cd0a2f04f6.png)


REGISTER POINTS - ADMIN
![image](https://user-images.githubusercontent.com/17830317/56876776-b7aef780-6a17-11e9-90c4-ddb77ab6614d.png)

 


WELCOME PAGE – USER
![image](https://user-images.githubusercontent.com/17830317/56876706-440cea80-6a17-11e9-9c18-8d070a2d388e.png)


DEFENDERS LISTED AS AN OVERLAY - USER
![image](https://user-images.githubusercontent.com/17830317/56876731-6e5ea800-6a17-11e9-8235-ef003a8ce3e4.png)


 
BUDGET DECREASING ON SELECTING A PLAYER -USER
![image](https://user-images.githubusercontent.com/17830317/56876738-7a4a6a00-6a17-11e9-950c-2c10cd3c9e49.png)

 

BROWSING A PLAYER -USER
![image](https://user-images.githubusercontent.com/17830317/56876743-83d3d200-6a17-11e9-9e45-c223812045b9.png)


 
PDF VIEW
![image](https://user-images.githubusercontent.com/17830317/56876750-8cc4a380-6a17-11e9-9347-642577d0d913.png)

 


## Use Cases

* Player interacts with mud terrain and gets slowed down
 - The CollisionChecker notices that the mud terrain and the player have directly collided by checking their zones
 - Upon entry, the CollisionChecker sends the new collision as an ENTER collision to the CollisionHandler
 - The CollisionHandler obtains the collision strategy attribute of the mud terrain, obtains the lambda expression defining the interaction, and passes it to the player to be run - it halves the player’s velocity attribute
 - Upon exit, previous steps are executed again only this time the CollisionChecker notifies the CollisionHandler to execute an EXIT collision which ends up resetting the player’s velocity attribute 
* Player hits impenetrable object and slides off
 - The CollisionChecker notices the object and the player have directly collided by checking their zones. The CollisionChecker sends the new collision as an ENTER collision to the CollisionHandler
 - The CollisionHandler obtains the collision strategy attribute of the object, obtains the lambda expression defining the interaction, and passes it to the player to be run - it changes the movement strategy of the player such that it only moves with either its horizontal or vertical velocity component
 - Upon exit, previous steps are executed again only this time the CollisionChecker notifies the CollisionHandler to execute an EXIT collision which ends up resetting the player’s movement strategy
* Enemy moves side to side on the screen
 - The modelStep() function in the PlayModel API is executed, running all PollStrategies on all objects including movement
 - The movement strategy of the enemy is retrieved as a lambda expression from the respective attribute and run on itself
 - The enemies side to side movement is coded in using the frequency parameter of the strategy class - at certain times it will move left, otherwise its velocity will flip and it will move right
* User controls the player to move up
 - User defines the up key for the game in the editor - the KeyBinding and KeyDefiner classes translate these inputs to enums in the backend used to write movement strategies
 - The modelStep() function in the PlayModel API is executed, running all PollStrategies on all objects including movement
 - The Player movement strategy is key controlled and takes in a list of active keys as a parameter. Depending on what keys are active, the player’s velocity attribute will be adjusted - here specifically, if whatever the user has defined as the down key is active (and none of the other movement pad keys are), the velocity will be set upward
* User controls the player to move diagonally
 - User defines the movement pad for the game in the editor - the KeyBinding and KeyDefiner classes translate these inputs to enums in the back-end used to write movement strategies
 - The modelStep() function in the PlayModel API is executed, running all PollStrategies on all objects including movement
 - The Player movement strategy is key controlled and takes in a list of active keys as a parameter. Depending on what keys are active, the player’s velocity attribute will be adjusted - here specifically, if a specific combination of two keys are pressed(and none of the other movement pad keys are), the velocity will be set in the appropriate diagonal direction
* User tries to make the player attack an enemy
 - User defines the attack keys for the game in the editor - the KeyBinding and KeyDefiner classes translate these inputs to enums in the back-end used to write collision/effect strategies
 - Depending on the type of attack the user wants to be executed, the CollisionChecker first must check to see if any of the player’s zones intersect with the enemy’s direct zone, passing it on to the CollisionHandler if so
 - The CollisionHandler takes the player’s effect/direct collision strategy, gets the lambda expression from it, and passes it to the enemy to be run - this could, for example, lower the enemies health upon collision
 - The direct/effect strategy itself will take in a list of active keys as a parameter - only if the specific attack key is pressed will the real content of the lambda expression actually execute on the enemy
* Enemy makes player ‘dizzy’ changing its movement pattern
 - See previous use cases for how CollisionChecker and CollisionHandler work together to sense a collision and pass a strategy from one colliding party to another
 - The lambda expression passed from the enemy to player to be run will simply pass in a new value for the player’s movement strategy attribute, using composition to dynamically change behavior
 - This strategy will have an inherent timer that upon completion will reset the movement attribute to its original state
* Spiked wall obstacle inflicts damage on player upon contact
 - See previous use cases for how CollisionChecker and CollisionHandler work together to sense a collision and pass a strategy from one colliding party to another
 - The lambda expression passed from the enemy to the player will be defined to reduce the value of the player’s health attribute upon entry, periodically reduce it while staying in the collision, and do nothing upon exited a collision
 - If the user wanted to write this strategy, they would simply write a strategy that implements the Collidable interface and they would then be prompted to write the aforementioned methods
* Player walks into the edge of the screen
 - The edges of the screen will be predefined in the editor such that there are game entities with impenetrable collision strategies surrounding the border of the world
 - From here, the code would allow for this functionality in the same manner as is discussed in Use Case 2
* User wishes to check their current game progress
 - During gameplay, the user clicks a button on the screen which opens up a pop-up menu with options to switch between different views
 - By clicking the left and right buttons on the pop-up menu, the user can eventually arrive at the progress screen. This will screen will state all the goals the user has already completed as well as the next set that they must achieve
 - This progress menu is updated by using observables between the classes in the Goal package and the respective controllers
* User customizes the attributes of an entity in the world
 - First the user selects an existing entity.  The MouseEvent for the click on the entity registers, and the WorldController sets the entity as selected.
 - When the entity is selected, the AttributesController loads the entity’s attributes and displays them onscreen in the attributes editor.  
 - To display controls to edit the attribute, the fields within the attribute are iterated over, and a FieldViewFactory creates a new editable pane for each field.  Editing the field automatically sets the value of the field in the model, and doing so activates anything that observes the attribute or its fields, updating the view to correspond with the changes in the model.
 - The user then modifies the values of the attributes using the provided controls as described above.  Modifications are reflected instantaneously through the use of observers.
* The user saves newly defined attributes to a new preset.
 - The user clicks the “Save Preset” button.  This saves the preset to XML by calling the EditorModel’s savePreset method in the API, allowing the user to use this preset at any later time to initialize new entities.
 - The model then accepts the entity to save’s Id.  It writes the information about the entity to XML, and creates a new preset entity, adding it to the existing observable list of presets.
 - The side menu in the view is observing this list of presets, and automatically refreshes, adding any tabs, accordion panels, or slots within accordions necessary to display the new entity.
* The user adds an entity based on a newly defined preset to the world.
 - The user clicks on the newly defined preset in the preset entities list. This sets the currently selected preset in the WorldController.  That preset will be added to the world when the world is clicked.
 - The user clicks on the world.  The EditorWorld registers a MouseEvent, and since the MouseEvent was not first registered on a pre-existing entity, a new entity is created by calling the EditorModel API method newInstance with the selected preset as a parameter.
 - This method call creates a new entity in the backend, adding it to the ObservableList of entities in the world.  The WorldController is observing this list, and when the list is updated, the WorldController refreshes, displaying the newly added entity onscreen.
 - The newly added entity’s position is then set to the coordinate where the mouse was clicked.  This is accomplished by modifying the position attribute of the entity through the EditorEntity API’s getAttribute method.  Now a new entity has been created at the location clicked.
 - The newly created entity is set as the currently selected entity in WorldController, causing its attributes to appear as in a previous use case.
* Create Account
 - When the user hits the play button in the play mode interface a pop up menu with two options (create account or login) appears. Each of both options requires a series of information to be written in order to access through the buttons. The player fills in the information to create an account (game ID, password, and re-enter password) and the program checks that the passwords match and if they do, it sends the request to a server application using java sockets, a reader, and a writer. The server checks that the game ID is not repeated by checking a SQL table that the server has and it tells through the same communication channel if the account is created or not. The communication is done through simple readers and by sending a String with commands to communicate and these commands use specific syntax that is created and parsed in a similar structure (but much smaller) to that done in slogo. Finally, if the account is successfully created the user is allowed to go on just like if he had successfully logged in. To view how the program handles a login please check the next use case.
* Login to account
 - When the user hits the play button in the play mode interface a pop up menu with two options (create account or login) appears. Each of both options requires a series of information to be written in order to access through the buttons. The player fills in the information to login to an account (game ID and password) and the program sends this two pieces of communication to a server just like it was specified in the previous use case and the server checks if the game ID actually exists in the SQL table database it holds and if the password matches the ID that it holds and if so it tells the result to the client. If the client receives a successful login response the user is allowed through to a new window where he is requested to choose a new game.
* Logout from account   
 - Once logged in using the previous use case, the user has two buttons as options of what to do next: play a game or logout. If the user clicks the logout button, he is returned to the login / create account menu, but in the backend the program sends a request to the server asking the server to change the state of the player in the SQL table it contains from connected to not connected by changing one of the values of the table from 1 to 0 indicating that the player is disconnected and doesn’t want to receive any more information.
* Multiplayer – chat with other
 - Suppose two players sitting at two different computers are connected to the server by loging in their account as shown two use cases above. The first player wants to send a message to all the people connected and therefore types the message and sends it. The program uses the same communication method explained above to communicate to the server and the server sends the message using the same communication method to all the connected accounts. Please note that the message is only shown in your computer once the server sends it back even if you are the one who sent the message. This message is received by everyone connected to the server and the only thing they do is display it on the screen.
* Multiplayer – moving my player
 - Using the same steps explained above in the previous use case, instead of sending a message the player sends a new location and an id related to the player. Please note that this id is given to the player the time he loads the game with his account. Note that the first time he loads the game, this in game id is created in the server. The id is stored in an SQL table related to all games and this table stores the name of the game being played, the game id, and the in game id used to represent the player’s character. Going back to moving the player, the client notifies the server his game id for his player and the new locations of given player. Then the server sends this message to all the people that have started the game before. If they are connected, the server immediately sends the request, but if they are not connected, it stores the request in a third table that stores all game updates. Once the disconnected player connects to the server to start playing any game, the request is immediately sent to the player in order for the player to modify the file on his end. The next use case handles any other event that the user could do that is not moving a player.
* Multiplayer – player presses key
 - The player presses a key that affects the state of the game. This key, in the long run, is calling a method, so it translates that method calling to a string to communicate with the server by using that string and the id of the game player in game explained in the previous use case and sends it to the server, and the only thing that the server does is sends it to all the users who have started the game in order for them to be updated with this specific information and uses the same methodology as the one explained in the previous use case.
* Creates an enemy spanner
 - The user creates an object in the authoring environment just as it was explained in the previous use cases, but the difference this time is that once created the object, the user is allowed to select one of various PollingActions that are basically actions that are stored within the object as attributes. The user must select the action that is a spanner. Next the user is asked for two pieces of information, first a predefined game entity he must choose and a frequency in seconds. In the back this action during gameplay will wait for the time to equal the frequency and when it does it creates a new object identical to the game entity chosen in the editor inside the action but with a new id. Additionally, it will reset the counter to zero and start again. Please note, that when a spanner is created, the user must specify (only if he wants to) the visibility of the object because if the object is made invisible it doesn’t interact with the environment.
* Creates tower that shoots fireballs
 - Basically, here we are using the use case specified above. The only important difference is what type of game entity we select within our spanner. The important difference is that this action must be stored as a trigger action that triggers on player and it must be given a radius. What this does is activate a trigger radius for the tower and whenever the player walks in within this trigger, the tower (spanner) will generate a fireball every frequency (specified in the spanner) and the important thing to note is that this fireball has an attack that it will deal to the player when it collides with it, a collision action that is on collision and deals damage, and a moving strategy that moves towards the player that the spanner spotted inside her main action. This use case demonstrates the use of nested actions and creation of entities based on action activities.
* Allow the user to set actions as goals (ex. "player spins once")
 - The user will go into the GoalEditor and initialize a new goal following the format: If `<Action>` has occurred (on `<Target Object>`).
 - This will allow the user to set goals which rely on actions rather than solely attributes.
 - This will implement the same Goal/Condition/Subcondition structure as before but will operate through an observable "message board" of sorts. This board will have all actions carried out, and because it is observable by the GoalNodes, goals can specify the completion of actions.
* Create the "scrolling" effect of a platformer
 - Drag a new  GameEntity onto the screen - frontend alerts backend to create a new GameEntity with the given ID
 - Thru iterator pattern, the fields of the attributes of the new GameEntity are displayed
 - Thru listeners, change the x component of the GameEntity's velocity 
 - In the game loop, the Entity will be translated by its velocity each frame
* Create a door/portal in an RPG game
 - Drag a new GameEntity and attach an action to its effect zone
 - Tell the effect zone to set the collidee's position vector to the teleport location
 - Add a tag filter to have the portal only affect the "player"
* Co-op gameplay
 - Have two GameEntities with movement strategies and keybindings associated
 - Optionally, name each "player" or give them the same team attribute so they behave as allies
* Player-versus-player mode
 - Have two GameEntities with movement strategies and keybindings
 - Do not specify teams or specify opposite teams so that each "player" can hurt the other
 - Add as many as you want!
* Unlimited survival mode
 - Specify the strategy of each GameEntity to be "spawn clone on death" 
 - Every time an enemy's health reaches 0, it will spawn a new enemy 
 
* w enemies (if desired by the user) to chase the player (or any specific character)
 - Back-end implementation of the A-star search strategy will take in two Game Entities: a "follower" object and a "followed" object. The follower entity will follow the followed entity by navigating the game board around obstacles in its path.
 - Thus it will find the shortest path (at any given instance) to the object stated. This can allow for the user to make levels more difficult (i.e. make the enemy follow the player quickly) and can be used in other situations where intelligent search algorithms may be needed.
 
* A Star Search Algorithm / Heuristic
 - Implement a smart AI search algorithm which allows for Game Entities to find the shortest path to the “goal” object (i.e. the item that it is chasing)
 - This strategy will take in two Game Entities  (the “follower” and the “followed”). The follower will trail the followed intelligently (i.e. navigating obstacles and computing the shortest distance). This will allow for the user to make games harder or easier when desired.

* Actions as goals
 - Goals can specify actions to be completed by certain objects.
 - Will follow the structure: if <action> has occurred (on <Target Object>) and therefore works with the current implemented structure.

* Action message board
 - Implemented in conjunction with the previous use case. This is the specific implementation that will make the GoalNodes able to view actions as they are completed. Because actions are strategies, as each action event is completed, the message board will hold this information. The board itself is observable by the GoalNodes and therefore actions can be incorporated into the game structure.

* Grid for Search Implementation
 - A Grid.java class will be implemented to allow for the search algorithms to operate. This could potentially be applied in other existing game frameworks as well. Thus although this class is created for the implementation of the search algorithms, it can also be used in other situations.
 
* Pause 
	- Pause game play from the menu and freeze progression of games such as enemy movement and game timer. To do this, summon the pop-up menu and select a button Pause in the progress section. This will impact the Animations and the timeline.

* Manipulate controls from pop-up menu
	- From the controls section of the pop-up menu, the player should not only be able to see the currently set controls but change the controls during gameplay. The menu would be summoned and the user would select the relevant control and indicate from the keyboard what the new keys for that control are.

* Display Time Spent so far on the game
	- On the heads-up display, the user would be able to see the time that has progressed since the user started the current goal. When subgoals are completed, the user would be able to see how long it took to complete that goal.

* View progress with subgoals and goals
	- The percentage for the progression of goals would be displayed on the heads-up display. Therefore, the user would know how far along her or she is with the quest.

* Support Drag and Drop in Editor
	- For the editor, the user would be able to drag and drop the map onto the editor and other items and be able to see a grid outline. With the grid, it would be expandable and the user would be able to trace a path for obstacles to be defined.

* Save favorite Items to keys
	- The user would be able to indicate favorite items and be able to swap to that item once the user defines a keyboard shortcut.

* Support Soundtrack changes from the pop-up menu
	- The user can change the soundtrack from the pop-up menu and be able to hear the new soundtrack.

* Display smaller map of location in relation to the larger map.
	- From the heads-up display, the user can see a smaller map on the bottom right corner. This would allow the user to see his relative position with respect to the larger complete map.
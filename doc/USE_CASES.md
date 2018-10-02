## Use Cases


* Delete Object in Editor
 * User selects an object in the editor. The user then selects to delete the object. View will remove the instance of Node from the UI. View will then call Model to delete the Entity object in Model
* View Attributes of an Object in the Editor
 * User selects an Object in Editor. The View determines which JavaFX Node was selected and determines its ID. The View then call that has Model return all the information concerning the ID of the selected Object. View then displays the pertinent information.
* Mouse Click During Runtime of a Game
 * The View will be notified of a click. View will then call a method to notify Model that a click has been detected. Model will then check Entities to see if the click affects the Entity.
* Load a GameState to Edit
 * User enters the editor. The user then selects load a game. An existing game should be selected. View will then call a method to have Model load the GameState. View then makes another call to load all the Entities from the loaded GameState into the UI
* The Upper Key is hit
 * User presses the upper key indicating that he wants the player to move forward. The View is notified of the button being pressed. View calls Model through a method to indicate that the upper button has been placed during gameplay. Model notifies GameState and GameState notifies Player. When the Player receives the notification it moves forward based on the velocity and its direction.
* Add a Second Player
 * The Player clicks on a button that allows him to add another player in order to play in multiplayer mode from one computer. A pop-up menu appears to indicate the changeable controls  that allow the movement of the player. The View notifies the model and this one notifies the GameState. The View adds a player with its respective image at a given position while the GameState adds another player with the given attributes to the list of GameObjects it has by creating a new one.
* Load a Game to Play
 * At the main entrance of any game, the user is asked to select an xml file representing the game (s)he wants to play. The game file is interpreted by the view and passed to the Model. The Model gives the file to an XMLReader and receives a GameState in return. The Model saves the GameState to one of its attributes and notifies the View to update the screen.
* Load a Different Game
 * While playing a game, the user clicks a button to load a new game and is asked to select an xml file representing the game (s)he wants to play. The game file is interpreted by the view and passed to the Model. The Model gives the file to an XMLReader and receives a GameState in return. The Model overwrites the GameState it had to the one it received by the reader and notifies the View to update the screen. Note that this will not save the progress in the game, unless the user saves before loading a new one.
* Game has been won
 * The hasWon() method in the final goal will return true. The modelStep() method will then notice this change while looping through all game objects. This will trigger a condition in the backend that pauses the game loop Timeline. An Observer in the front-end will notice this and display a new scene showing that the user has won the game
* Add Basic Player
 * The user will drag a new player onto the map template in the editor mode. After dropping the new player into place, the front-end creates a view version of the player and calls a method in the external back-end API to create the corresponding model entity as well. The front-end creates an Id instance for the specific player and uses observables so when any of the player characteristics (health, size etc.) are changed in the editor, the front-end is notified, finds the specific player instance using the Id class, and then notifies the backend of the changes uses the addAttribute() method in the external back-end API
* Change the Movement Pattern for an Enemy
 * We are using the strategy design pattern in order to exchange different movement algorithms for active game objects. We are creating classes implementing the Action interface that define different movement algorithms. To give an example, if we wanted the enemy to change its movement pattern when it gets close to a player, the CollisionHandler class will sense this and call the appropriate collide method. This collide method would switch out the action implementation for the enemy by changing its Action instance variable.
* Change Screen Color
 * The user will select a new color from the appropriate combobox in the editor. From here, the front-end is notified of the change and runs an internal public API method to resolve the issue. The back-end is not involved here.
* Display Score
 * Each game has a different goal or goals selected by the user in the editor prior to launching the game. Thus, the “score” of the game is different depending on the goal. However, all goals can be visually represented to the user. For example, if the user is playing survival mode, the score is a timer. If the user is playing “kill x enemies” mode, then the score is the number of enemies killed. The frontend will call upon a method in the backend external API for goal objects which gives the pertinent information to the frontend, who will then display it on the screen. 
* Player Loads Game
 * LoadGame is a method in the model’s external API. It involves taking in an XML file or a serialized representation of the game state (game data) and building all the necessary objects in the backend. It returns a collection of necessary information to the frontend allowing the screen to be populated with visual representations of the objects. 
* Player Saves Game
 * The player is prevented from acting during a save. That is, the step() in JavaFX is disabled. Then the game state (game data) is serialized to a file that can be loaded later. This is accomplished using the SaveGame method in the backend external API. Then the user is permitted to continue playing the game. 
* Player Hits the Edge of the Screen
 * This will be detected as a collision between the player and the boundary of the game. The result is the player’s position being translated backwards by a small step at the incident angle of collision with the wall. The distance the player is sent back is calculated using the player’s velocity and the time step. 
* Player Collides with Enemy
 * This will be detected as a collision between two game objects. The user will have selected the enemy’s “on collision” behavior in the editor prior to launching the game. Thus the collision behavior between these two objects is set to something like damage and block player, and don’t affect the obstacle. 
* Player Pauses the Game
 * The user presses “pause” in the frontend. The JavaFX timeline is then disabled until the user presses play once again.
* Player Kills an Enemy
 * The enemy’s health reaches 0. Then its “onDeath” method is activated. This behavior was set by the user in the editor. Most likely it will alert to frontend to remove its representation from the root. The dead enemy will be effectively inert for the remainder of the game.

*Create new Game
 *From the editor, the user would go to the file bar and select “New Game”. Initially, when you open up the editor, you do not have an initialized game. You will need to click on File -> New Game to start creating your game. The default XML game file is loaded into the editor.

*Put something in the heads-up display
 *In the `HeadsUpDisplay.java` class there is a public method, `addCounter(Node counterView)` which allows the user to add in any type of attribute to keep track of. This accepts a node which keeps thing general for extension. For example, you could pass in information about the health of the user.

* Define a new initial goal
 *From the editor one can switch the scene to the `GoalTreeView.java` which each node has a `GoalNodeView`. One would enter in a text box the Goal Name, Description and Condition. 

*Player collects an object
 *The backend will know if the Player is in front of a Collectible object (which we know by the Id and the type). The user will input a key (which we know through the `ControllsControlla`). This key will mean pickup item. THe key information is sent to the backend which will process the information about picking the item and adding it to an ObservableList to know the current items a player has.

*Deleting a goal
 *In the `GoalTreeScene` each `goalNodeView` contains a button to delete that Node. This would also delete the goal in the backend. One would ask if they only want to delete that node or if they also want to delete the children of that node.

*Display player moving forward: 
 *Once the key to move forward has been activated, the backend in the step-method it will update the absolute position of the player in the model. In the Controller we observe that the value has changed. To keep the player in the center of the scene, the relative position of the Background and any other object will be calculated and changed accordingly. 

*Switch between goal and world editors
 *From the menu bar, the user will select on File and there will be an option to change from one scene to the other scene.

*Edit object attribute in editor*
By clicking on an object in the editor, that will get the id of the object and the backend will pass the relevant information about the object’s attributes to the frontend. From there, the attributes pane of the left sidebar of the menu will update and the user can edit the attributes from there.

* Add a basic enemy *  
Using the editor platform, the user will have the opportunity to select a ‘generic enemy’ from a range of preset enemy objects. Upon dragging the aforementioned selection onto the screen, the enemy object will be added to the game’s object bin. The user will then be granted rights to edit the object’s parameters via the editor interface from a custom list of attribute options. Any adjustments made will pass through the appropriate agencies.

* Add a preset obstacle
 * Using the editor platform, the user will have the opportunity to select from a range of preset obstacle objects. Like the use case mentioned above, dragging the object onto the screen will add it to the appropriately marked bins (in this case the ObstacleBin).

* Change the name of the object
In the editor interface, the user will have the ability to click and select a game object on the screen. This will pull up a list of customized GUI elements that will allow the user to edit attributes like the name of the object. When the user edits the name attribute, a request is sent to the controller whereupon an instance of the game object’s EditorAttribute is called upon. Through this external API, the front end is granted access to the `setValue()` method that will change the value of a specified attribute (in this case, the name) to another value. 

* Add new goal as a child goal
 * The user will drag said goal into the UI tree formation underneath its chosen ‘parent’ goal. The external backend goal API will observe this and call the addChild() method to add the new goal into the backend goal tree data structure.
* Player hits obstacle
 * The collision manager will create a pair between the player and the obstacle. This pair will be sent to the CollisionChecker which will determine if the pair is actually colliding (the objects are sent using a limited read-only interface). From here, the CollisionChecker sends the colliding object to the CollisionHandler which uses the collision strategy it has implemented for those two specific object types. In this case, the player would be moved backward slightly every time it tried to move through the obstacle, simulating walking in place.
* Enemy dies
 * When the enemies health attribute observable returns 0, the backend will remove the enemy from the EnemyBin data structure. The front-end in turn will observe that the object with that Id has been removed from the back-end and will update its VisibleObjects list to remove the enemy.
* Drag player position around in editor
 * Via the editor interface, the user will have the ability to use mouse drag interactions to alter the spawn location of a specified player object. When the drag event is complete, it triggers the `setValue()`method in the EditorAttribute and feeds in a new Position object with the player’s new coordinates. This interaction will be closely monitored by the controller. 
* Close window
 * When the user clicks on the ‘x’ button in the top right corner of the screen, the program will execute a shutdown sequence. Additionally, the front-end will provide an exit button in the menu which will execute a force quit on the window. This gives the user two different options offering versatility. 
* Pick up item
 * When the user is technically colliding with an item, nothing will automatically happen since the CollisionHandler has no strategy for this. It will defer to the KeyActionHandler which will sense that the user has clicked the appropriate pick up key (for example, the ‘p’ key). When this happens, the appropriate action strategy will take place and the object will be moved into the player’s model’s itemsOwned List data structure. The front-end observes this list and displays the results on the screen.
* Use an item
 * If the user wanted to use an item the player currently has, they would just have to use the mouse to click on the item on the side of the screen. The item will be activated upon doing so. The back-end will remove the item from its internal itemsOwned List by checking the Id instance of the object returned from the onClicked() method from the back-end external API

* Move diagonally
 * The user could do this by pressing multiple direction keys simultaneously. The onKeyPressed() method in the back-end external API will receive all the KeyEvents currently occurring and the backend MovementEngine will take in the different combinations and use the Player’s movement strategy to react.

* Player restarts game after pausing
 * First, see the ‘Player pauses game use case’. From here, the pause button will change to a play button. The user would simply re-click the button in order to call the built in play() method on the JavaFX Timeline, restarting the game loop.

* Start Program
 * Upon initialization, the user will be presented with one of two options: editor mode or play mode. If the user selects the former, he/she will be taken to the editor interface where additional options for game creation will be available, ranging from editing an existing game state to creating one from scratch. Meanwhile, the play option will direct the user to a screen where he/she can select an existing game state to load from. 
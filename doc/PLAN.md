# Plan

## Core:
### Game State:  
**Primary Responsibility:** Justin Wang  
**Secondary Responsibility:** Ashka Stephen    
The Game State keeps track of each object in play on the "board" of the game, such as each player, each enemy, each obstacle, and each goal. Thus, through the backend external API the Game State is able to retrieve the states of all the players thus enabling decisions to occur. The details for implementation are discussed in detail in the design document.


### Game Object:  
**Primary Responsibility:** Ashka Stephen  
**Secondary Responsibility:** Dhruv Patel, Andres Lebbos  
The Game Objects are all the *players/enemies/obstacles/collectables/etc*. in the game at any given point. On a high level, we will have all the objects separated by types in bins. Thus, this leaves us with the ability to iterate through any given game object type such that rules, goals, and other checks may be implemented on one specific type of object with ease. A general iterator will be made to go through all the objects in play at  any given instance in time. Each object will have attributes which are observable by the front-end side. Another decision the group made (in detail in design document) is the creation of goal objects separate from general game objects. This is because a goal is needed for a game however it does not contain the same attributes a game object does. This is a basic overview of the high level implementation, details of which are discussed more in our design overview file.


### Attributes Hierarchy:
**Primary Responsibility:** Justin Wang
**Secondary Responsibility:** Dhruv Patel
Attributes define all customizability of Game Objects. As attachments to Game Object classes, these attributes must implement a common `Attribute` interface in order to be stored. These data containers will also be displayed and editable on the view; to do this, we will create an interface hierarchy based on the display format of the attribute. These interfaces will be editable and sent directly to the front-end. As a user edits the attribute to a game object in the front-end, it immediately edits the attribute in the back-end. Basic attributes are: x-position, y-position, and 


### Collision Engine:
**Primary Responsibility:** Advait Reddy  
**Secondary Responsibility:** Alex Boss  
>>>>>>> 5cd6084c91566ac8f0c7dbd405ae96ed68e20c5e
The Collision Engine will contain an algorithm that checks for all possible
pairs of objects on the screen. It will send each of these individual pairs to the Collision Checker to see if they have actually collided. The collision engine will use an interface to pass limited read-only game objects into the
Collision Checker so that only appropriate attributes (angle, position, range)
can be accessed.

<<<<<<< HEAD

* Collision Checker:
Primary Responsibility: Alex Boss
Secondary Responsibility: Advait Reddy
=======
### Collision Checker:
**Primary Responsibility:** Alex Boss  
**Secondary Responsibility:** Advait Reddy  
>>>>>>> 5cd6084c91566ac8f0c7dbd405ae96ed68e20c5e
The Collision Checker will receive pairs of game objects from the Collision
Engine and will use information from each game object's attributes in order to
determine if a collision has occurred. If a collision has occurred, the pair
of game objects will be sent to the Collision Handler.

<<<<<<< HEAD
* Collision Handler:
Primary Responsibility: Advait Reddy, Alex Boss
Secondary Responsibility: Nathaniel Brooke
=======
### Collision Handler:
**Primary Responsibility:** Alex Boss, Advait Reddy  
**Secondary Responsibility:** Nathaniel Brooke  
>>>>>>> 5cd6084c91566ac8f0c7dbd405ae96ed68e20c5e
The Collision Handler will use the Visitor and Strategy design patterns in order
to determine what methods should be called based off different input Game Object
pairs. These collision methods should be able to change on the fly using our
specified design patterns. The collision handler will also designate different
actions depending on the type of collision (direct or target range).

<<<<<<< HEAD
* Key Handler:
Primary Responsibility:
Secondary Responsibility:

* Movement Engine:
Primary Responsibility:
Secondary Responsibility:

* Controllers
Primary Responsibility:
Secondary Responsibility: Advait Reddy

* Action Interface Hierarchy: Andres Lebbos
Primary Responsibility: This interface must contain through a strategy implementation the hierarchy of all the possible implementations that might represent a change to the overall GameState or to a specific part of GameState through a Strategy pattern. This means that it should implement, for example, all the Possible predefined movements the enemies can have.
=======
### Movement Engine:
**Primary Responsibility:**  Dhruv Patel   
**Secondary Responsibility:**  Justin Wang  
This engine will update the trajectories of each GameObject every time the `step()` method is called in the model. This will be done by iterating through all game objects in play, extracting the "x-velocity" and "y-velocity", and changing the "x-position" and "y-position" of the game-object. If an object is in collision, it will adjust the velocity to guarantee that the object does not continue on the collision-course. It will directly update the GameState.

### Controllers:
**Primary Responsibility:** Nathaniel Brooke   
**Secondary Responsibility:** Salo Abraham, Harry Liu, Advait Reddy  
These will process information from the backend, obtained by observing 
observables within the backend, to update the view.  These will also
process user input from the view and use it to update the backend via
methods in the model's external API.

### Action Interface Hierarchy: 
**Primary Responsibility:** Andres Lebbos
**Secondary Responsibility:** Dhruv Patel 
This interface must contain through a strategy implementation the hierarchy of all the possible implementations that might represent a change to the overall GameState or to a specific part of GameState through a Strategy pattern. This means that it should implement, for example, all the Possible predefined movements the enemies can have.
>>>>>>> 5cd6084c91566ac8f0c7dbd405ae96ed68e20c5e
Secondary Responsibility: Whenever an Attribute is added that contains an Action, the possible Actions it could implement should be added to the Action hierarchy. Details to this hierarchy are discussed in the DESIGN.md file in the sections Design Details and User Interfaces that we wrote.

## Extensions:

### Animations:
**Primary Responsibility:** Salo Abraham   
**Secondary Responsibility:** Nathaniel Brooke  
Animation will take place on the front-end. Since the positions of each object are observables, this allows each component on the front-end to be updated automatically once a change in the backend occurs. Although basic implementation details are not covered here (covered in design document instead), overall it's important to understand the basic connection between the front and back end.

### Multiple Players:
**Primary Responsibility:** TBD  
**Secondary Responsibility:** TBD  
We planned our design specifically to be able to implement this feature. Because players simply fall under the `GameObjects` hierarchy (and Key-control is considered and Action-Attribute) this means that, with little changes, a game editor should be able to add two players with different (or the same) controls. This means that we will be able to have two players on the screen at once and also swarm control (multiple players controlled by the same key-pad).

### Projectiles:
**Primary Responsibility:** TBD  
**Secondary Responsibility:** TBD  
In future updates, the game should provide the appropriate framework to allow for the use of projectiles. For instance, there should be the ability to add arrows to a 'bow' weapon object or bullets to a 'gun' object. This would also entail making weapons with the capability of spawning or 'firing' projectiles. Given the current game object framework, however, we would simply need to have projectiles and projectile-spawning weapons as extensions of the Entity class.
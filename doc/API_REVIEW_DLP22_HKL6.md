# API Review
### DLP22 and HKL6 (Dylan Peters and Harry Liu)

## Part 1

1. What about your API/design is intended to be flexible?
	- The communication between front end and back end is intended to be the most flexible part of the design. To make it flexible, we made front end and back end able to communicate by passing lambda expressions that act on different parts of the Game State. This is flexible because it allows the user to create different attack/movement types without the back end having to explicitly define them.
2. How is your API/design encapsulating your implementation decisions?
	- The API is designed to encapsulate implementation by preventing the front end from having to know the structure of the Game Engine when the game is running. The game engine modifies the Game State, and the front end simply observes the changes to the Game State.
3. How is your part linked to other parts of the project?
	- My part is creating the screens that allows the user to create new unit types. This is linked to the rest of the project because the other parts need new units in order for the game to progress. 
4. What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?
	- The possible exceptions that might occur include: the user trying to move to an illegal spot, trying to use an attack  that cannot be used, or attempting to load a corrupted file. The errors will be handled in the backend, and the front end will be alerted. When the front end is alerted about an exception, we will display a dialog to the user with a description of the error and how the user can attempt to fix it. This will be done in a dedicated error handling class.
5. Why do you think your API/design is good (also define what your measure of good is)?
	- We think our API is well-designed because the methods that we outlined are thorough - they allow us to perform all of the use cases we outlined. Furthermore, it encapsulates the front and back ends and is flexible enough to allow the user to modify game behavior. Finally, it minimizes duplicated code.


## Part 2
1. What feature/design problem are you most excited to work on?
	- I am most excited to work on the popup menus. The menus involve swapping panes and identifying the proper menu when the user summons it by pressing a key. The challenge is integrating information from the backend into the menu and making sure it is easy to select the proper menu item.
2. What feature/design problem are you most worried about working on?
	- I am most worried about the map and the editor. Dragging an object to a map and having that saved to a game seems like a daunting task where many things could go wrong.
3. What major feature do you plan to implement this weekend?
	- This weekend I plan on ironing out the different menu items and connecting them together. Hopefully summoning through a key will be also finished.
4. Discuss the use cases/issues created for your pieces: are they descriptive, appropriate, and reasonably sized?
	- The use cases are appropriate and descriptive, but mainly focus on the back end. The front-end use cases lack references to specific code.
5. Do you have use cases for errors that might occur?
	-Yes, the errors would be handled by an alert class.

## Team Members
Ashka Stephen and Matthew Barbano

##PART ONE

###What about your API/design is intended to be flexible?
Matthew: You can assign any key for a particular action by simply changing a line of code which specifies the key. This is a specific example of extensibility.
Ashka: You can pass in any combination of specific items to create a goal.

###How is your API/design encapsulating your implementation decisions?
Matthew: We use the observable pattern for items such as collisions, time and input from the user. This makes implementing details easier since observables allow for automatic notification.
Ashka: The backend does not have an external API. Therefore everything goes through the frontend external. Goals also make use of the observable pattern so the frontend is notified when a goal is passed.

###How is your part linked to other parts of the project?
Matthew: Game Player instantiates the Game Loop and calls start().
Ashka: Goals are connected to Collisions, Attributes, and Actions.

###What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?
Matthew: Pop up window for the error with a customized exception error.
Ashka: Make customized "VoggaExceptions" to display.

###Why do you think your API/design is good (also define what your measure of good is)?
Matthew: Actions and Events (meeting of conditions) linked to the entities themselves.
Ashka: All objects being entities in one bin rather than all separate.

##PART TWO
###What feature/design problem are you most excited to work on?
Matthew: Extending the current game (implementing the map for going to different levels.
Ashka: Seeing the final implementation of the project.

###What feature/design problem are you most worried about working on?
Matthew: Game Engine to communicate with the Game Authoring environment.
Ashka: Drawing the line between flexibility and funcitonality.

###What major feature do you plan to implement this weekend?
Matthew: Adding the second level.
Ashka: Finish Goals start intelligent search algorithm.

###Discuss the use cases/issues created for your pieces: are they descriptive, appropriate, and reasonably sized?
Matthew: Use Cases are descriptive. Lot of them are features for extension rather than the basics of implementation.
Ashka: Use Cases are a good size and we should 

###Do you have use cases for errors that might occur?
Matthew: None.
Ashka: One that covers all errors
API Review
==========
>ghh6 Gordon Huynh
>jty4 Jesse Yue

Part 1
-----------
* The interactions between the view and the model is meant for the view to only call model methods. This way, it is on the model's job to provide the necessary methods necessary to play a game. From the model, we have different engines to handle different functionalities of the program depending on the combinations of attributes
* Attributes can represent a variety of parameters pertaining to a game. Combinations of the attributes can help define specific ideas such as a object, enemy, or a player.
* My part is linked to other parts of the program since both models of each end have an instance of GameState.
* Errors will be thrown to the front end in order for them to handle and display messages.
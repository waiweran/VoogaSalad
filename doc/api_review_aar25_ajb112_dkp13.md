Aar25, ajb112, dkp13  
# Part 1
What about your API/design is intended to be flexible? 
	Our code is designed so every game entity is defined by its attributes - this allows us to customize our characters with almost no limitations. Additionally, the get method in our AttributeHolder uses generics is such a way that we can hold all Attributes in one data structure regardless of return type making it very flexible for new additions.
How is your API/design encapsulating your implementation decisions? 
	Our design encapsulates the casting of attributes using the AttributeHolder class.
How is your part linked to other parts of the project? 
	My part is the game engine - it handles movement, key handling, and collisions. It links to the backend by using their data stores and links to the frontend using Observables.
What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?
	
One error case might occur if a user tries to edit an attribute’s field and enters a value of an incorrect data type. In this case, the model will throw up an “IncorrectFieldException” to the view, and the front-end will display the error in whatever way they see fit.

Why do you think your API/design is good (also define what your measure of good is)? 

A good API is one that is very easy to read and one that can withstand major underlying changes without having to change much itself.
# Part 2
What feature/design problem are you most excited to work on? 

I am most excited to work on  integrating movement patterns with editable attributes and fields.

What feature/design problem are you most worried about working on? 
The ability for the user to add new Attributes dynamically. 
What major feature do you plan to implement this weekend?
	A basic demo integrating backend & frontend.
Discuss the use cases/issues created for your pieces: are they descriptive, appropriate, and reasonably sized?
	The cases are descriptive, appropriate, and reasonably sized. There are not enough issues currently created. 
Do you have use cases for errors that might occur?
	Yes, if the user seeks an Attribute that does not exist, the frontend should display that to the user. 
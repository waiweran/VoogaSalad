# API Review

## Part 1

* What about your API/design is intended to be flexible?

Our API design is intended to be flexible by having all entities in the game 
defined by attributes.  This flexibility allows the user to define new entities 
and change the properties of existing entities by adding, removing, or editing
attributes.  

* How is your API/design encapsulating your implementation decisions?

Our API heavily encapsulates implementation details.  Every backend class that 
the front end accesses implements a pre-defined interface that the the front end
uses for all interactions with the back end class.  Thus all back end 
implementation details are hidden from the front end.  Likewise, the back end 
never accesses methods or properties of the front end.  Thus, the front end
implementation details are hidden from the back end.  

* How is your part linked to other parts of the project?

My part of the project is heavily linked to other parts of the project.  The 
components of the editor that I have been working on must access information
from the backend and allow the user to modify that information.  This linkage
occurs through the main API, which makes all connections between the front end
and the back end.

* What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?

My component handles exceptions through a special ExceptionHandler class which
displays exceptions caused by invalid input in a popup message.  These exceptions
are normally thrown to my component from the back end if the user enters an
invalid value or does something that the editor does not support.  

* Why do you think your API/design is good (also define what your measure of good is)?

I think a good measure of API/design goodness is encapsulation and extendibility.
Our API is good because it fully encapsulates the backend and the frontend from
each other.  Furthermore our design is good because we have designed entity types
to be easily extended by the user, making multitudes of games possible.


## Part 2

* What feature/design problem are you most excited to work on?

I am most excited to work on the design problem of moving and placing objects
at an arbitratry coordinate on the screen.  This is important for both the play
and the edtior view, as it is the core of the game view.  This will also be 
interesting because object placement must occur on a zoomed in portion of the
map, sometimes relative to the player, which is always centered during game play.  

* What feature/design problem are you most worried about working on?

I am most concerned about working on attribute editing. I am not sure how flexible
attributes will be, and if they are too flexible, I am worried that they may be
too abstract.  

* What major feature do you plan to implement this weekend?

I plan to implement viewing the main world, placing objects onscreen at their
specified locations, as I described in the feature I was most excited about
working on.  

* Discuss the use cases/issues created for your pieces: are they descriptive, appropriate, and reasonably sized?

The use cases for the editor are descriptive and appropriate, however they may
be too large.  Most span the front and back ends, as they involve user input
to the front end changing a value in the back end.  Some also involve internal
changes in the back end altering the front end state.  Examples include adding
an entity to the screen, where backend and frontend interaction is required.

* Do you have use cases for errors that might occur?

Some errors have been included in use cases, however errors were not our primary
focus during use case creation.  Some cases like invalid value input have been 
considered in a basic sense, but errors have not throroughly been discussed.  


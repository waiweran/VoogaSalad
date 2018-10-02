# API Review
### Part 1
> Code Author: Michael Li (mxl3)


1. They have general game state and game loaders that are built with emphasis on composition over inheritance. 
2. They have a save game method and individual saves for sub-entities. A similar structure exists for loading.
3. Game data is in charge of storing assets into entities that can be loaded on command. Moreover, we need game data to transfer saved games between computers.
4. The wrong type of object might be saved during serialization. Loading objects may not occur in the appropriate orientation/configuration. These will be handled through test cases.
5. Their API design is good because it is encapsulated fairly well. Other groups do not need to worry about how the game data is handled. 


> Code Author: Justin Wang (jtw57)

1. Our API design (which encompasses the GameState API) was design with external interactions prioritized. Each class that needs to interact with others on the front end will interact through the appropriate external API agencies. While game state does not directly interact with the front end, it implements an internal API (`GameStateInternal`) which limits the exposure of the class to others that use it (in this case, both the play and edit models). This enforces encapsulation. The extensive API network also allows for easy enforcement of limitations on class accesses throughout the entire project.
2. The API only allows access to direct getter/setter methods. This becomes especially useful when attributes are implemented via a composition pattern within the game entity framework. Instead of having the entities adhere to a strict hierarchy of inheritance, we decided that composition was ideal. This is similarly exhibited when the strategy pattern is implemented into the actions framework.
3. The game state, as well as goals (the second thing I'm working on) interface via the play and editor models (depending on the stage chosen). They are both in a common pool wherein only single implementations of both are needed. 
4. Similar to Michael's game data structure, the game state will entail an extensive network of type checking (especially during parsing stages). These will be handled by an exceptions framework (TBD).
5. The current API is very minimal yet extensible - it can easily be attached to the classes that need it.   

### Part 2
> Code Author: Michael Li (mxl3)

1. Loading game levels correctly (including the appropriate objects)
2. Serialization
3. Saving game objects and entities
4. The use cases outlined are succinct and cover most potential design issues that might occur. However, there are certainly a few design flaws that may occur during the whole game state procedure. 
5. As discussed above, the use cases cover a few potential errors but could be more extensive.

> Code Author: Justin Wang (jtw57)

1. The subconditions network (making it extensible for a variety of goal making options)
2. Implementing a level-up system
3. Completing the goal tree framework
4. Our use cases are admittedly quite simple. They cover most basic use cases possible, but are not extensive enough to account for very nuanced errors.
5. Error checked was rarely (if ever) accounted for in the test cases. We plan on implementing JUnit tester in order to compensate for this deficiency. 
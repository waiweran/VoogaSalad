package general.newgoal.messages;

/**
 * Created by Justin on 4/28/2017.
 */

//TODO: Check if entity for the util exists
//Option 1: Make checking integrated into the conditions themselves
//Requires: Access to the gamestate

/**
 * Structure:
 * 1. Number Of Operations
 * 2. Target object in question
 * 3. Action/Attribute to be checked
 *
 * 4A. Checker condition
 * 5A. Target value
 *
 * 4B. Secondary object (if available)
 *
 */

/**
 * Types of strategies:
 *  1 entity
 *  2 entity
 */

/**
 * Collections of SubConditions:
 *
 * > If subconditionA and/or subconditionB and/or subconditionC....
 * > If util has occurred x times
 * >
 *
 */

/**
 * NEED 2 STATES
 * Default state - what state the condition starts off as
 * change state - what happens when comparison is satisfied
 */

import general.actions.strategies.Strategy;
import general.attributes.Attribute;
import general.attributes.ReadOnlyAttribute;
import general.entities.EntityType;
import general.newgoal.messages.types.ActionMessage;
import general.newgoal.messages.types.AttributeMessage;
import general.newgoal.messages.types.IntangibleMessage;

/**
 * Store the gamestate as a 'pool' in each goal, and have the subconditions monitor said pool
 */

public class MessageFactory {

    public Message newMessage(EntityType gameEntity, Attribute targetAttribute){
        return new AttributeMessage(gameEntity, targetAttribute);
    }

    public Message newMessage(EntityType actorEntity, EntityType targetEntity, Strategy strategy) {
        return new ActionMessage(actorEntity, targetEntity, strategy);
    }

    public Message newMessage(Object o){
        return new IntangibleMessage<>(o);
    }

}

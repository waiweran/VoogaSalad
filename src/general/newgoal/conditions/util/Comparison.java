package general.newgoal.conditions.util;

import general.newgoal.conditions.NewCondition;
import general.newgoal.messages.Message;
import general.newgoal.states.State;
import general.newgoal.states.substates.Incomplete;

public enum Comparison {

    EQUALS {
        @Override
        public void checkValue(Message testAttribute, Message targetAttribute, State state, NewCondition condition) {
            if (testAttribute.compareTo(targetAttribute) == 0) {
                state.doAction(condition, testAttribute);
            }
            else{
                new Incomplete(){{
                    doAction(condition, testAttribute);
                }};
            }
        }
    }, GREATER_THAN {
        @Override
        public void checkValue(Message testAttribute, Message targetAttribute, State state, NewCondition condition) {
            if (testAttribute.compareTo(targetAttribute) > 0) {
                state.doAction(condition, testAttribute);
            }
            else{
                new Incomplete(){{
                    doAction(condition, testAttribute);
                }};
            }
        }
    }, LESS_THAN {
        @Override
        public void checkValue(Message testAttribute, Message targetAttribute, State state, NewCondition condition) {
            if (testAttribute.compareTo(targetAttribute) < 0) {
                state.doAction(condition, testAttribute);
            }
            else{
                new Incomplete(){{
                    doAction(condition, testAttribute);
                }};
            }
        }
    }, GREATER_THAN_EQUALS {
        @Override
        public void checkValue(Message testAttribute, Message targetAttribute, State state, NewCondition condition) {
            if (testAttribute.compareTo(targetAttribute) >= 0) {
                state.doAction(condition, testAttribute);
            }
            else{
                new Incomplete(){{
                    doAction(condition, testAttribute);
                }};
            }
        }
    }, LESS_THAN_EQUALS {
        @Override
        public void checkValue(Message testAttribute, Message targetAttribute, State state, NewCondition condition) {
            if (testAttribute.compareTo(targetAttribute) <= 0) {
                state.doAction(condition, testAttribute);
            }
            else{
                new Incomplete(){{
                    doAction(condition, testAttribute);
                }};
            }
        }
    }, NOT_EQUALS {
        @Override
        public void checkValue(Message testAttribute, Message targetAttribute, State state, NewCondition condition) {
            if (testAttribute.compareTo(targetAttribute) != 0) {
                state.doAction(condition, testAttribute);
            }
            else{
                new Incomplete(){{
                    doAction(condition, testAttribute);
                }};
            }
        }
    };

    /**
     * Checks the testField's value against the targetField's value
     * @param testAttribute the field to test
     * @param targetAttribute the field to execute against
     * @param state
     * @param condition
     * @return true if the condition is satisfied; false otherwise
     */
    public abstract void checkValue(Message testAttribute, Message targetAttribute, State state, NewCondition condition);

}

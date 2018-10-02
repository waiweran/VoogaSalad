package general.newgoal.conditions;

import general.newgoal.api.StateAPI;
import general.newgoal.states.State;
import general.newgoal.states.substates.Incomplete;

import java.util.*;

/**
 * State mapped to the condition. Once all states are marked as passed then the condition is passed.
 * 
 * Created by Justin Wang on 4/29/2017.
 */
public class StateMap<T extends StateAPI> implements Iterable<State> {

    private Map<State, List<T>> stateListMap;

    public StateMap(){
        stateListMap = new HashMap<State, List<T>>();
    }

    public void addItem(State state, T subCondition){
    	
        if(stateListMap.containsKey(state) && stateListMap.get(state).contains(subCondition)){
        	stateListMap.get(state).add(subCondition);
        }
        else{
        	if(!state.inProgress()){
        		stateListMap.put(state, new ArrayList<T>(){{
        			add(subCondition);
        		}});
        	}
        }

    }

    public void moveItem(State state, T subCondition){

        this.removeItem(subCondition);
        this.addItem(state, subCondition);

    }

    private void removeItem(T subCondition){

        if(stateListMap.containsKey(subCondition.getPastState())){

            List<T> tempList = stateListMap.get(subCondition.getPastState());

            if (tempList.contains(subCondition)){
                tempList.remove(subCondition);
            }

            if (tempList.isEmpty()){
                stateListMap.remove(tempList);
            }

        }

    }

    public boolean containsState(State state){
        return (stateListMap.containsKey(state));
    }

    public List<T> getAllItems(){

        List<T> tempList = new ArrayList<T>();

        for(List<T> list : stateListMap.values()){
            tempList.addAll(list);
        }

        return tempList;

    }
    
    public int numOfState(State state){
    	
    	if(this.containsState(state)){
    		return stateListMap.get(state).size();
    	}
    	
    	return 0;
    	
    }

    @Override
    public Iterator<State> iterator() {
        return this.stateListMap.keySet().iterator();
    }
}

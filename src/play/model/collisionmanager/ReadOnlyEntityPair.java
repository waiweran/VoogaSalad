package play.model.collisionmanager;

import general.entities.GameEntity;
import general.entities.ReadOnlyEntity;

public class ReadOnlyEntityPair {
	
	private ReadOnlyEntity myFirst;
	private ReadOnlyEntity mySecond;
	
	public ReadOnlyEntityPair(GameEntity one, GameEntity two){
		myFirst = one;
		mySecond = two;
	}
	
	public ReadOnlyEntityPair(ReadOnlyEntity one, ReadOnlyEntity two){
		myFirst = one;
		mySecond = two;
	}
	
	public ReadOnlyEntityPair(ReadOnlyEntityPair original){
		myFirst = original.getSecond();
		mySecond = original.getFirst();
	}
	
	public ReadOnlyEntity getFirst(){
		return myFirst;
	}
	
	public ReadOnlyEntity getSecond(){
		return mySecond;
	}
	
	public boolean equals(Object other){
		if(other == this){
			return true;
		}
		if(other == null){
			return false;
		}
		if(getClass() != other.getClass()){
			return false;
		}
		
		ReadOnlyEntityPair pair = (ReadOnlyEntityPair) other;
		boolean isCopy = pair.getFirst().equals(this.getFirst()) && pair.getSecond().equals(this.getSecond());
		
		return isCopy;
	}

}

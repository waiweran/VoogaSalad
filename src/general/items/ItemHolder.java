package general.items;

import java.util.ArrayList;
import java.util.List;

import general.entities.ReadOnlyEntity;


/**
 * 
 * Encapsulates a List of Item entities
 * 
 * @author DhruvKPatel
 * @author Alex
 *
 */
public class ItemHolder {
	
	private static final int DEFAULT_INVENTORY_SIZE = 0;
	private final int DEFAULT_ACTIVE_INDEX = 0;
	
	private List<ReadOnlyEntity> items;
	private int inventorySize;
	private int activeIndex;
	
	public ItemHolder(int size){
		items = new ArrayList<>();
		inventorySize = size;
		activeIndex = DEFAULT_ACTIVE_INDEX;
	}
	
	public ItemHolder(){
		this(DEFAULT_INVENTORY_SIZE);
	}
	
	public boolean hasItems(){
		return (items.size() != 0);
	}


	
	
	
	/**
	 * Adds the collected item to the inventory, if there is enough room
	 * @param item The collected item to be added to the inventory
	 */
	public void add(ReadOnlyEntity item){
		System.out.println("bitchly");
		if(items.size() < inventorySize){
			System.out.println("FUUUUUUUCK");
			items.add(item);
		}
	}
	
	/**
	 * Switches the active item to the next item in the inventory by iterating over every
	 * GameEntity in "items" and setting its ActiveStatus to false if it is not active
	 * or true if it is active
	 */
	public void switchItem(){
		if(inventorySize != 0){
			activeIndex = (activeIndex + 1) % inventorySize;
			for(ReadOnlyEntity item : items){
				if(items.indexOf(item) != activeIndex){
					item.run( e -> {
						e.setActive(false);
					});
				}
				else {
					item.run( e -> {
						e.setActive(true);
					});
				}
			}
		}
	}
	
	/**
	 * Sets the item at the given spot in the list to active.
	 * Front-End Invasion. GIT REKT
	 * - You're welcome for adding this -- @author Nathaniel Brooke & Harry Liu
	 * @param itemIndex the index in the list.
	 */
	public void setActive(int itemIndex) {
		if(inventorySize != 0){
			activeIndex = itemIndex;
			for(ReadOnlyEntity item : items){
				if(items.indexOf(item) != activeIndex){
					item.run( e -> {
						e.setActive(false);
					});
				}
				else {
					item.run( e -> {
						e.setActive(true);
					});
				}
			}
		}
	}
	
	public List<ReadOnlyEntity> getItems(){
		return items;
	}
	
	public ReadOnlyEntity getActiveItem(){
		return items.get(activeIndex);
	}
	
	public int getInventorySize(){
		return inventorySize;
	}
	
}

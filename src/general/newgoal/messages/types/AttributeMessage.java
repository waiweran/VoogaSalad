package general.newgoal.messages.types;

import general.attributes.Attribute;
import general.attributes.ReadOnlyAttribute;
import general.entities.EntityType;
import general.newgoal.messages.Message;

import java.util.Observable;

/**
 * @author Justin
 * 
 */
public class AttributeMessage extends Message<AttributeMessage> {

	private EntityType actorType;
	private Attribute changedAttribute;

	public AttributeMessage(EntityType actorType, Attribute changedAttribute){
		this.actorType = actorType;
		this.changedAttribute = changedAttribute;
		this.changedAttribute.addObserver(this);
	}

	public EntityType getActorType() {
		return this.actorType;
	}

	public ReadOnlyAttribute getChangedAttribute(){
		return this.changedAttribute;
	}

	@Override
    public int compareTo(AttributeMessage o) {
        return this.changedAttribute.compareTo(o.getChangedAttribute());
        //return 0;
    }

	@Override
	public void update(Observable o, Object arg) {

	}

	@Override
	public boolean equals(Object obj) {

		if(obj instanceof AttributeMessage){
			AttributeMessage attributeMessage = (AttributeMessage) obj;
			if(attributeMessage.getActorType().equals(this.getActorType()) && attributeMessage.getChangedAttribute().getClass().equals(this.getChangedAttribute().getClass())){
				return true;
			}
		}

		return false;

	}
}
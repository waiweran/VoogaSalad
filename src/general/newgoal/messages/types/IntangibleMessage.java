package general.newgoal.messages.types;

import general.newgoal.messages.Message;

import java.util.Observable;

/**
 * @author Justin
 * @version 4/28/2017.
 * 
 */
public class IntangibleMessage<T> extends Message<T> {

    private T intangible;

    public IntangibleMessage(T intangible){
        this.intangible = intangible;
    }

    public T getIntangible(){
        return this.intangible;
    }

    @Override
    public int compareTo(T o) {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {

        if(obj instanceof IntangibleMessage){
            IntangibleMessage intangibleMessage = (IntangibleMessage) obj;
            if(intangibleMessage.getIntangible().getClass().equals(this.getIntangible().getClass())){
                return true;
            }
        }

        return false;

    }

    @Override
    public void update(Observable o, Object arg) {

    }
}

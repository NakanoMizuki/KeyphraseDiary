package jp.ac.titech.psg.nakano.keyphrasememo.model;

import java.util.EventObject;
import java.util.Observable;

/**
 * Created by nakanomizuki on 15/07/20.
 */
public class ObservableMemo extends Observable {

    private String title;
    private String content;

    public static ObservableMemo createInstance(){
        return new ObservableMemo();
    }
    private ObservableMemo(){};

    public void setTitle(String title){
        this.title = title;
        notifyEvnet();
    }

    private void notifyEvnet(){
        ChangeTextEvent event = new ChangeTextEvent(this);
        event.setTitleChanged(true);
        setChanged();
        notifyObservers(event);
    }

    public static class ChangeTextEvent extends EventObject {
        public ChangeTextEvent(Object source){
            super(source);
        }
        private boolean isTitleChanged;

        public boolean isTitleChanged(){
            return isTitleChanged;
        }
        public void setTitleChanged(boolean flag){
            isTitleChanged = flag;
        }
    }
}

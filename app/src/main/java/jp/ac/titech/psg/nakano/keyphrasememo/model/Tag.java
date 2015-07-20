package jp.ac.titech.psg.nakano.keyphrasememo.model;

import java.io.Serializable;

/**
 * Created by nakanomizuki on 15/07/20.
 */
public class Tag implements Serializable{
    private static final long serialVersionUID = 1L;

    private final long id;
    private final String name;

    public Tag(long id, String name){
        this.id = id;
        this.name = name;
    }
    public Tag(String name){
        this(0,name);
    }

    public long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
}

package jp.ac.titech.psg.nakano.keyphrasememo.model;

/**
 * Created by nakanomizuki on 15/07/20.
 */
public class Tag {

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

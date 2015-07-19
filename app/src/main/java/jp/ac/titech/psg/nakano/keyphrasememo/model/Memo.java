package jp.ac.titech.psg.nakano.keyphrasememo.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by nakanomizuki on 15/07/17.
 */
public class Memo implements Serializable{

    private static final long serialVersionUID = 1L;

    private final String title;
    private final String content;
    private final Date cdate;
    private final Date udate;

    public Memo(String title, String content, Date cdate, Date udate){
        this.title = title;
        this.content = content;
        this.cdate = cdate;
        this.udate = udate;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public Date getCreatedDate() {
        return cdate;
    }

    public Date getUpdatedDate() {
        return udate;
    }

    @Override
    public String toString(){
        return "Title=" + title + "\n"
                + "Content=" + content;
    }
}

package jp.ac.titech.psg.nakano.keyphrasememo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by nakanomizuki on 15/07/17.
 */
public class Memo implements Serializable{

    private static final long serialVersionUID = 1L;

    private final Long id;
    private final String title;
    private final String content;
    private final Date cdate;
    private final Date udate;
    private List<Tag> tags;

    public Memo(Long id,String title, String content, Date cdate, Date udate){
        this.id = id;
        this.title = title;
        this.content = content;
        this.cdate = cdate;
        this.udate = udate;
    }
    public Memo(String title, String content, Date cdate, Date udate){
        this(0L, title, content, cdate, udate);
    }

    public long getId(){
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getCreatedDate() {
        return cdate;
    }

    public Date getUpdatedDate() {
        return udate;
    }

    public List<Tag> getTags(){
        return tags;
    }

    public void setTags(List<Tag> tags){
        this.tags = tags;
    }

    @Override
    public String toString(){
        return "Title=" + title + "\n"
                + "Content=" + content;
    }
}

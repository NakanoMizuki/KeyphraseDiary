package jp.ac.titech.psg.nakano.keyphrasediary.model;

import java.util.Date;

/**
 * Created by nakanomizuki on 15/07/17.
 */
public class Diary {

    private final String title;
    private final String content;
    private final Date cdate;
    private final Date udate;

    public Diary(String title, String content, Date cdate, Date udate){
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

package jp.ac.titech.psg.nakano.keyphrasememo.activities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

abstract public class AbstractWriteActivity extends AppCompatActivity {
    protected String memoTitle, memoContent;
    protected List<String> tags;
    protected ViewPager pager;

    public void setMemoTitle(String str){
        memoTitle = str;
    }

    public void setMemoContent(String str){
        memoContent = str;
    }

    public String getMemoTitle(){
        return memoTitle;
    }

    public String getMemoContent(){
        return memoContent;
    }

    public void setTags(List<String> tags){
        this.tags = tags;
    }

    public List<String> getTags(){
        return tags;
    }

    public ViewPager getViewPager(){
        return pager;
    }
}

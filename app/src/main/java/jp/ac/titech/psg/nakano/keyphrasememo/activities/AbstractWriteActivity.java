package jp.ac.titech.psg.nakano.keyphrasememo.activities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import jp.ac.titech.psg.nakano.keyphrasememo.R;

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


    public void clickCreate(View v){
        createTagView("hoge");
    }


    public void createTagView(String name){
        final LinearLayout container = (LinearLayout) this.findViewById(R.id.fragment_tag_container);
        final LinearLayout subContainer = new LinearLayout(this);
        EditText tagEdit = new EditText(this);
        tagEdit.setText(name);
        subContainer.addView(tagEdit, createParam());
        Button button = new Button(this);
        button.setText("remove");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                container.removeView(subContainer);
            }
        });
        subContainer.addView(button, createParam());
        container.addView(subContainer, createParam());
    }

    private ViewGroup.LayoutParams createParam(){
        return new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public List<String> getTagNames(){
        List<String> tags = new ArrayList<String>();
        LinearLayout container = (LinearLayout) findViewById(R.id.fragment_tag_container);
        for(int i=0; i < container.getChildCount(); ++i){
            LinearLayout subContiner = (LinearLayout) container.getChildAt(i);
            EditText tagEdit = (EditText) subContiner.getChildAt(0);
            String tagName = tagEdit.getText().toString();
            if(!tags.contains(tagName) && !tagName.equals("")){
                tags.add(tagName);
            }
        }
        return tags;
    }
}

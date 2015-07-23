package jp.ac.titech.psg.nakano.keyphrasememo.activities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import jp.ac.titech.psg.nakano.keyphrasememo.R;

abstract public class AbstractWriteActivity extends AppCompatActivity {
    private static final int CONTAINER_ID = R.id.fragment_tag_container;

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

    public List<String> getDefaultTags(){
        return tags;
    }

    public ViewPager getViewPager(){
        return pager;
    }


    public void clickCreate(View v){
        createTagView("hoge");
    }


    public void createTagView(String name){
        final ViewGroup container = (ViewGroup) this.findViewById(CONTAINER_ID);

        // if name is already existing, do nop
        for(int i=0; i <container.getChildCount(); i++){
            ViewGroup child = (ViewGroup) container.getChildAt(i);
            String existingTag = ((EditText)child.getChildAt(0)).getText().toString();
            if(name.equals(existingTag)) return;
        }

        final ViewGroup subContainer = new LinearLayout(this);
        EditText tagEdit = new EditText(this);
        tagEdit.setText(name);
        subContainer.addView(tagEdit, createParam());
        Button button = new Button(this);
        button.setText(getResources().getString(R.string.tag_delete_button));
        button.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.button_text_small));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                container.removeView(subContainer);
                container.invalidate();
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
        ViewGroup container = (ViewGroup) findViewById(CONTAINER_ID);
        for(int i=0; i < container.getChildCount(); ++i){
            ViewGroup subContiner = (ViewGroup) container.getChildAt(i);
            EditText tagEdit = (EditText) subContiner.getChildAt(0);
            String tagName = tagEdit.getText().toString();
            if(!tags.contains(tagName) && !tagName.equals("")){
                tags.add(tagName);
            }
        }
        return tags;
    }
}

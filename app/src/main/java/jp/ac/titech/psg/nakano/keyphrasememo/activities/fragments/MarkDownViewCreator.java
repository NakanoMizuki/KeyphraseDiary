package jp.ac.titech.psg.nakano.keyphrasememo.activities.fragments;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import jp.ac.titech.psg.nakano.keyphrasememo.R;

/**
 * Created by nakanomizuki on 15/07/23.
 */
public class MarkDownViewCreator {
    private static final String TAG = "MarkDownViewCreator";
    private static final String SPACE = "\\s";
    private Activity context;
    private LayoutInflater inflater;

    public MarkDownViewCreator(Activity context){
        this.context = context;
        inflater = context.getLayoutInflater();
    }

    public void createMarkDownView(String str){
        ViewGroup parent = (ViewGroup) context.findViewById(R.id.preview_fragment_content);
        parent.removeAllViews();
        if(str == null || str.equals("")) return;
        str = str.replaceAll("\r\n", "\n");
        Log.d(TAG, "replacedStr=" + str);
        String[] lines = str.split("\n");
        for(int i=0; i < lines.length; i++){
            String line = lines[i];
            Log.d(TAG, "line=" + line);
            View child;
            if(line.matches("^#+" + SPACE + ".+")){
                child = createHighlightView(line);
            }else{
                child = createTextView(line);
            }

            parent.addView(child);
        }

    }

    private TextView createTextView(String str){
        TextView textView = (TextView) inflater.inflate(R.layout.md_text, null);
        textView.setText(str);
        return textView;
    }

    private TextView createHighlightView(String str){
        int layout;
        if(str.matches("^#" + SPACE + ".+")){
            layout = R.layout.md_highlight1;
            Log.d(TAG, "set h1");
        }else if(str.matches("^##" + SPACE + ".+")){
            layout = R.layout.md_highlight2;
            Log.d(TAG, "set h2");
        }else{
            layout = R.layout.md_highlight3;
            Log.d(TAG, "set h3");
        }
        TextView textView = (TextView) inflater.inflate(layout, null);
        textView.setText(str.replaceAll("^#+" + SPACE, ""));
        return textView;
    }
}

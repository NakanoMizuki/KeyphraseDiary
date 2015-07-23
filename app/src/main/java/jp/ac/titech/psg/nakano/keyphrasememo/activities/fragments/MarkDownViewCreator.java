package jp.ac.titech.psg.nakano.keyphrasememo.activities.fragments;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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

    public View createMarkDownView(String str){
        ViewGroup view = new LinearLayout(context);
        if(str == null || str.equals("")) return view;
        str = str.replaceAll("\r\n", "\n");
        Log.d(TAG, "replacedStr=" + str);
        String[] lines = str.split("\n");
        for(int i=0; i < lines.length; i++){
            String line = lines[i];
            Log.d(TAG, "line=" + line);
            if(line.matches("^#+" + SPACE + ".+")){
                view.addView(createHighlightView(line));
            }else{
                view.addView(createTextView(line));
            }

        }

        return view;
    }

    private TextView createTextView(String str){
        TextView textView = (TextView) inflater.inflate(R.layout.md_text, null);
        textView.setText(str);
        return textView;
    }

    private TextView createHighlightView(String str){
        TextView textView = (TextView) inflater.inflate(R.layout.md_highlight1, null);
        textView.setText("Highlight");
        return textView;
    }
}

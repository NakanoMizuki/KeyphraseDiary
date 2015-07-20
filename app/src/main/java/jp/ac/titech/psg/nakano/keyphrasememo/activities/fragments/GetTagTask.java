package jp.ac.titech.psg.nakano.keyphrasememo.activities.fragments;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Set;

import jp.ac.titech.psg.nakano.keyphrasememo.R;
import jp.ac.titech.psg.nakano.keyphrasememo.YahooConnector;

/**
 * Created by nakanomizuki on 15/07/20.
 */
public class GetTagTask extends AsyncTask<String, Void, Set<String>> {
    private final Activity activity;
    private final EditText tags;

    public GetTagTask(Activity activity, EditText tags){
        this.activity = activity;
        this.tags = tags;
    }

    @Override
    public Set<String> doInBackground(String... params){
        return YahooConnector.getKeyphrase(params[0]);
    }

    @Override
    public void onPostExecute(Set<String> result){
        if(result == null){
            Toast.makeText(activity, R.string.fail_getting_keyphrase, Toast.LENGTH_SHORT).show();
            return;
        }
        String str = "";
        for(String s: result){
            str += s + ",";
        }
        tags.setText(str);
    }

}

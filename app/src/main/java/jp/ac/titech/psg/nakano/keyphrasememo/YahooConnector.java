package jp.ac.titech.psg.nakano.keyphrasememo;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by nakanomizuki on 15/07/16.
 */
public class YahooConnector {
    private static final String APP_ID = "dj0zaiZpPWFieHVaajZWU3FuQSZzPWNvbnN1bWVyc2VjcmV0Jng9OGQ-";
    private static final String OUTPUT = "xml";
    private static final String REQUEST = "http://jlp.yahooapis.jp/KeyphraseService/V1/extract?";
    private static final String ENCODE = "UTF-8";
    private static final int NUM = 5;
    private static final String TAG = "YahooConnector";

    public static Set<String> getKeyphrase(String sentence) {
        assert sentence == null;
        assert sentence.equals("");

        try {
            String urlstring = REQUEST
                    + "appid=" + APP_ID
                    + "&sentence=" + URLEncoder.encode(sentence, ENCODE)
                    + "&output=" + OUTPUT;
            Log.d(TAG, "urlstring= " + urlstring);
            URL url = new URL(urlstring);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            return parseXML(connection.getInputStream());
        } catch (IOException e) {
            return null;
        } catch (XmlPullParserException e) {
            return null;
        }
    }

    private static Set<String> parseXML(InputStream stream) throws XmlPullParserException, IOException {
        Set<String> phrases = new HashSet<String >();

        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(stream, ENCODE);
        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG) {
                if (parser.getName().equals("Keyphrase")) {
                    phrases.add(parser.nextText().toString());
                    if (phrases.size() >= NUM) break;
                }
            }
            eventType = parser.next();
        }
        return phrases;
    }
}
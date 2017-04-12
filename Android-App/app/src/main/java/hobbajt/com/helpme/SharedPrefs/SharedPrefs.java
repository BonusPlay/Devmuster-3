package hobbajt.com.helpme.SharedPrefs;

import android.content.SharedPreferences;

import com.google.gson.Gson;

public class SharedPrefs
{
    static final String IS_FIRST_START = "isFirstStart";
    static final String REPORT = "report";
    static final String USERNAME = "username";
    static final String CUSTOM_HELP_TYPES = "customHelpTypes";


    static SharedPreferences prefs;
    protected static final Gson gson = new Gson();

    public static void init(SharedPreferences prefs)
    {
        SharedPrefs.prefs = prefs;
    }

    public static void clearPrefs()
    {
        prefs.edit().clear().apply();
    }
}

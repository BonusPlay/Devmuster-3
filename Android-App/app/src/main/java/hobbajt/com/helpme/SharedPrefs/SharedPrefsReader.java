package hobbajt.com.helpme.SharedPrefs;

import com.google.gson.reflect.TypeToken;

import org.apache.commons.lang3.ObjectUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;

import hobbajt.com.helpme.CustomHelpType;
import hobbajt.com.helpme.HelpType;
import hobbajt.com.helpme.Report;
import hobbajt.com.helpme.Username;

public class SharedPrefsReader extends SharedPrefs
{
    public static boolean loadIsFirstStart()
    {
        return prefs.getBoolean(IS_FIRST_START, true);
    }

    public static Username loadUsername()
    {
        String json = prefs.getString(USERNAME, null);
        Type type = new TypeToken<Username>() {}.getType();
        return gson.fromJson(json, type);
    }

    public static Report loadReport()
    {
        String json = prefs.getString(REPORT, null);
        Type type = new TypeToken<Report>() {}.getType();
        return ObjectUtils.defaultIfNull(gson.fromJson(json, type), new Report());
    }

    public static ArrayList<CustomHelpType> loadCustomHelpTypes()
    {
        String json = prefs.getString(CUSTOM_HELP_TYPES, null);
        Type type = new TypeToken<ArrayList<CustomHelpType>>() {}.getType();
        return ObjectUtils.defaultIfNull(gson.fromJson(json, type), new ArrayList<CustomHelpType>());
    }
}

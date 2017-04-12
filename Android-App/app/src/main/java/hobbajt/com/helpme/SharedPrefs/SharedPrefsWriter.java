package hobbajt.com.helpme.SharedPrefs;

import java.util.ArrayList;

import hobbajt.com.helpme.CustomHelpType;
import hobbajt.com.helpme.Report;
import hobbajt.com.helpme.Username;

public class SharedPrefsWriter extends SharedPrefs
{


    public static void saveIsFirstStart(boolean isFirstStart)
    {
        prefs.edit().putBoolean(IS_FIRST_START, isFirstStart).apply();
    }

    public static void saveUserName(Username username)
    {
        String usernameJson = gson.toJson(username);
        prefs.edit().putString(USERNAME, usernameJson).apply();
    }

    public static void saveReport(Report report)
    {
        String reportJson = gson.toJson(report);
        prefs.edit().putString(REPORT, reportJson).apply();
    }

    public static void saveCustomHelpType(CustomHelpType customHelpType)
    {
        ArrayList<CustomHelpType> customHelpTypes = SharedPrefsReader.loadCustomHelpTypes();
        customHelpTypes.add(customHelpType);
        String customTypeJson = gson.toJson(customHelpTypes);
        prefs.edit().putString(CUSTOM_HELP_TYPES, customTypeJson).apply();
    }
}

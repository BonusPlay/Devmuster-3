package hobbajt.com.helpme.Events;

import android.content.Context;
import android.os.Bundle;

import java.util.ArrayList;

import hobbajt.com.helpme.CustomHelpType;
import hobbajt.com.helpme.Base.FragmentsManager;
import hobbajt.com.helpme.HelpType;
import hobbajt.com.helpme.Report;
import hobbajt.com.helpme.SharedPrefs.SharedPrefsReader;
import hobbajt.com.helpme.SharedPrefs.SharedPrefsWriter;
import hobbajt.com.helpme.VictimsConditions.VictimsConditionsFragment;

public class EventsPresenter implements EventsMVP.Presenter
{
    private final FragmentsManager fragmentsManager;
    private HelpType helpType;

    private final Context context;
    private final EventsMVP.View view;

    public EventsPresenter(Context context, EventsMVP.View view, FragmentsManager fragmentsManager)
    {
        this.context = context;
        this.view = view;
        this.fragmentsManager = fragmentsManager;
    }

    @Override
    public void onEventClicked(Events event)
    {
        Report report = SharedPrefsReader.loadReport();
        report.setPhoneNumber(event.getHelpType().getPhoneNumber());
        report.setEventType(event.getName());
        SharedPrefsWriter.saveReport(report);

        VictimsConditionsFragment fragment = new VictimsConditionsFragment();
        fragmentsManager.changeFragment(fragment, null);
    }

    @Override
    public void restoreData(Bundle bundle)
    {
        helpType = HelpType.getByName(bundle.getString("item"));

        if(helpType == HelpType.CUSTOM)
        {
            ArrayList<CustomHelpType> customHelpTypes = SharedPrefsReader.loadCustomHelpTypes();
            view.displayData(customHelpTypes, true);
        }
        else
            view.displayData(Events.getByCategory(helpType), false);
    }

    @Override
    public void onCoustomHelpClicked(CustomHelpType customHelpType)
    {
        Report report = SharedPrefsReader.loadReport();
        report.setPhoneNumber(customHelpType.getPhoneNumber());
        report.setEventType(customHelpType.getName());
        SharedPrefsWriter.saveReport(report);

        VictimsConditionsFragment fragment = new VictimsConditionsFragment();
        fragmentsManager.changeFragment(fragment, null);
    }
}

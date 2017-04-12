package hobbajt.com.helpme.VictimsConditions;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.location.LocationListener;
import android.telephony.SmsManager;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;

import hobbajt.com.helpme.Base.FragmentsManager;
import hobbajt.com.helpme.PeopleConditions;
import hobbajt.com.helpme.Report;
import hobbajt.com.helpme.SharedPrefs.SharedPrefsReader;
import hobbajt.com.helpme.SharedPrefs.SharedPrefsWriter;
import hobbajt.com.helpme.Summary.SummaryFragment;

import static hobbajt.com.helpme.Application.App.getContext;

public class VictimsConditionsPresenter implements VictimsConditionsMVP.Presenter, LocationListener
{
    private final Context context;
    private final VictimsConditionsMVP.View view;
    private final FragmentsManager fragmentsManager;
    private int count;

    public VictimsConditionsPresenter(Context context, VictimsConditionsMVP.View view, FragmentsManager fragmentsManager)
    {
        this.context = context;
        this.view = view;
        this.fragmentsManager = fragmentsManager;
    }

    @Override
    public void restoreData(Bundle arguments)
    {
        count = arguments.getInt("item", 0);
        view.showData(count);
    }

    @Override
    public void sendReport()
    {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, false);

        if(ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            return;

        Location location = locationManager.getLastKnownLocation(provider);

        if(location != null)
            onLocationChanged(location);

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, this);
    }

    @Override
    public void addPeople(PeopleConditions peopleConditions)
    {
        Report report = SharedPrefsReader.loadReport();
        report.setPeople(peopleConditions);
        SharedPrefsWriter.saveReport(report);
    }

    @Override
    public void onLocationChanged(Location location)
    {
        hobbajt.com.helpme.Location.Location userLocation = new hobbajt.com.helpme.Location.Location(location.getLongitude(), location.getLatitude());
        sendReportAsMessage(userLocation);
    }

    private void sendReportAsMessage(hobbajt.com.helpme.Location.Location userLocation)
    {
        Report report = SharedPrefsReader.loadReport();
        report.setUsername(SharedPrefsReader.loadUsername());
        report.setLocation(userLocation);
        String reportJson = parseReportToJson(report);
        sendSMS(report.getPhoneNumber(), reportJson);
    }

    private String parseReportToJson(Report report)
    {
        return new Gson().toJson(report);
    }

    private void sendSMS(String phoneNumber, String message)
    {
        try
        {
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, new Intent("SENT_SMS_ACTION"), 0);
            SmsManager smsManager = SmsManager.getDefault();
            ArrayList<String> messageParts = smsManager.divideMessage(message);
            ArrayList<PendingIntent> intents = new ArrayList<>(messageParts.size());

            for(int i = 0; i < messageParts.size(); i++)
                intents.add(pendingIntent);

            smsManager.divideMessage(message);
            smsManager.sendMultipartTextMessage(phoneNumber, null, messageParts, intents, null);
            Log.d("test", "SMS send successful!");
            SummaryFragment summaryFragment = new SummaryFragment();
            fragmentsManager.changeFragment(summaryFragment, phoneNumber);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            Log.d("test", "SMS send failed!");
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {}

    @Override
    public void onProviderEnabled(String s) {}

    @Override
    public void onProviderDisabled(String s) {}
}

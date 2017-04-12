package hobbajt.com.helpme.Location;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.*;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import static hobbajt.com.helpme.Application.App.getContext;

public class LocationPresenter implements LocationMVP.Presenter//,// LocationListener
{
    private final Context context;
    private final LocationMVP.View view;

    public LocationPresenter(Context context, LocationMVP.View view)
    {
        this.context = context;
        this.view = view;
    }
/*
    @Override
    public void getLocation()
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
    public void onLocationChanged(Location location)
    {
        String longitude = "Longitude: " + location.getLongitude();
        String latitude = "Latitude: " + location.getLatitude();
        Log.d("test", "Location: x = " + longitude + " y = " + latitude);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {}

    @Override
    public void onProviderEnabled(String s) {}

    @Override
    public void onProviderDisabled(String s) {}*/
}

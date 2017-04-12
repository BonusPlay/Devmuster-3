package hobbajt.com.helpme.Summary;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.telephony.SmsManager;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SummaryModel implements SummaryMVP.Model
{
    private final SummaryMVP.Presenter presenter;
    private final Context context;

    public SummaryModel(SummaryMVP.Presenter presenter, Context context)
    {
        this.presenter = presenter;
        this.context = context;
    }

    @Override
    public void sendCallRequest()
    {
        String json = "{\"calling\" : \"true\"}";

        try
        {
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, new Intent("SENT_SMS_ACTION"), 0);
            SmsManager smsManager = SmsManager.getDefault();

            smsManager.sendTextMessage("570859077", null, json, pendingIntent, null);
            Log.d("test", "SMS send successful!");
            presenter.onSendSuccessful();//changeFragment(summaryFragment, null);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            Log.d("test", "SMS send failed!");
        }
    }

}

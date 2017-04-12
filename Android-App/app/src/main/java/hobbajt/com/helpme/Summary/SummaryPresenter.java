package hobbajt.com.helpme.Summary;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;

import hobbajt.com.helpme.Base.FragmentsManager;

public class SummaryPresenter implements SummaryMVP.Presenter
{

    private final FragmentsManager fragmentsManager;
    private final Context context;
    private final SummaryMVP.View view;
    private final SummaryMVP.Model model;
    private String phoneNumber;

    public SummaryPresenter(Context context, SummaryMVP.View view, FragmentsManager fragmentsManager)
    {
        this.context = context;
        this.view = view;
        this.fragmentsManager = fragmentsManager;

        model = new SummaryModel(this, context);
    }

    @Override
    public void sendCallRequest()
    {
        model.sendCallRequest();
    }

    @Override
    public void onSendSuccessful()
    {
        Handler handler = new Handler();
        handler.postDelayed(this::callPhone, 5000);
    }

    @Override
    public void restoreData(Bundle arguments)
    {
        phoneNumber = arguments.getString("item");
    }

    private void callPhone()
    {
        Intent intent = new Intent(Intent.ACTION_CALL);

        intent.setData(Uri.parse("tel:" + phoneNumber));
        if(ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
            return;
        context.startActivity(intent);
        view.stopAnimation();
    }
}

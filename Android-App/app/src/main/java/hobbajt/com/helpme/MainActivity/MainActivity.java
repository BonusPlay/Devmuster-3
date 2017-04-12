package hobbajt.com.helpme.MainActivity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import hobbajt.com.helpme.Application.App;
import hobbajt.com.helpme.Base.BaseFragment;
import hobbajt.com.helpme.Dialogs.Dialog;
import hobbajt.com.helpme.Dialogs.ExamExitDialog;
import hobbajt.com.helpme.Base.FragmentsManager;
import hobbajt.com.helpme.Dialogs.UsernameDialog;
import hobbajt.com.helpme.Menu.MenuFragment;
import hobbajt.com.helpme.R;
import hobbajt.com.helpme.SharedPrefs.SharedPrefsReader;

public class MainActivity extends AppCompatActivity
{
    private int exitClicksCount;
    private FragmentsManager fragmentsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        showUsernameDialog(this);

        fragmentsManager = new FragmentsManager(getSupportFragmentManager());
        fragmentsManager.showFirstFragment();

        //ReportUploader uploader = new ReportUploader();
    }

    private void showUsernameDialog(Context context)
    {
        boolean isFirstStart = SharedPrefsReader.loadIsFirstStart();
        if(isFirstStart)
        {
            UsernameDialog usernameDialog = new UsernameDialog(context);
            usernameDialog.show();
        }

    }

    @Override
    public void onBackPressed()
    {
        BaseFragment fragment = fragmentsManager.getCurrentFragment();
        if(fragment instanceof MenuFragment)
        {
            Dialog dialog = new ExamExitDialog(this, getFragmentsManager());
            dialog.show();
        }
        else
            super.onBackPressed();
    }

    public void pressExit()
    {
        exitClicksCount++;
        if(exitClicksCount == 1)
            Snackbar.make(findViewById(android.R.id.content), App.getContext().getString(R.string.exit_message), Snackbar.LENGTH_SHORT).show();
        else
            System.exit(0);
    }

    public void resetClicksCount()
    {
        exitClicksCount = 0;
    }

    public FragmentsManager getFragmentsManager()
    {
        return fragmentsManager;
    }
}

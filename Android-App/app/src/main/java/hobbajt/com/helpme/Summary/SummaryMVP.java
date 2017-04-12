package hobbajt.com.helpme.Summary;

import android.os.Bundle;

public interface SummaryMVP
{
    interface Model
    {

        void sendCallRequest();
    }

    interface View
    {

        void stopAnimation();
    }

    interface Presenter
    {
        void sendCallRequest();

        void onSendSuccessful();

        void restoreData(Bundle arguments);
    }
}

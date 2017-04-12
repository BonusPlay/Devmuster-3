package hobbajt.com.helpme.Events;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import hobbajt.com.helpme.CustomHelpType;

public interface EventsMVP
{
    interface Model
    {

    }

    interface View
    {
        void displayData(List<? extends Object> data, boolean isCustom);
    }

    interface Presenter
    {

        void onEventClicked(Events event);

        void restoreData(Bundle bundle);

        void onCoustomHelpClicked(CustomHelpType customHelpType);
    }
}

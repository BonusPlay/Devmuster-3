package hobbajt.com.helpme.VictimsConditions;

import android.os.Bundle;

import hobbajt.com.helpme.PeopleConditions;

public interface VictimsConditionsMVP
{
    interface Model
    {

    }

    interface View
    {

        void showData(int count);
    }

    interface Presenter
    {

        void restoreData(Bundle arguments);

        void sendReport();

        void addPeople(PeopleConditions peopleConditions);
    }
}

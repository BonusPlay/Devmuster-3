package hobbajt.com.helpme.Base;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.io.Serializable;

import hobbajt.com.helpme.MainActivity.MainActivity;

public abstract class BaseFragment extends Fragment
{
    protected MainActivity activity;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        activity = (MainActivity) getActivity();
        activity.resetClicksCount();
    }

    public void saveState(Serializable items)
    {
        Bundle bundle = new Bundle();
        bundle.putSerializable("item", items);
        setArguments(bundle);
    }
}

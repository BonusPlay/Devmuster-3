package hobbajt.com.helpme.Location;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import hobbajt.com.helpme.Base.BaseFragment;
import hobbajt.com.helpme.R;

public class LocationFragment extends BaseFragment implements LocationMVP.View
{
    private LocationMVP.Presenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        ButterKnife.bind(this, view);

        presenter = new LocationPresenter(getContext(), this);
        //presenter.getLocation();

        return view;
    }
}

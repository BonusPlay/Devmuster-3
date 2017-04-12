package hobbajt.com.helpme.VictimsConditions;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import hobbajt.com.helpme.Base.BaseFragment;
import hobbajt.com.helpme.CustomViews.CustomNumberPicker;
import hobbajt.com.helpme.PeopleConditions;
import hobbajt.com.helpme.R;

public class VictimsConditionsFragment extends BaseFragment implements VictimsConditionsMVP.View
{
    @BindView(R.id.tvCount) TextView tvCount;
    @BindViews({R.id.npStableCount, R.id.npBadCount, R.id.npCriticalCount}) CustomNumberPicker[] npCounts;
    @BindView(R.id.toolbar) Toolbar toolbar;

    VictimsConditionsMVP.Presenter presenter;
    private int totalCount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_victims_conditions, container, false);
        ButterKnife.bind(this, view);

        setHasOptionsMenu(true);
        toolbar.setTitleTextColor(android.graphics.Color.WHITE);
        toolbar.inflateMenu(R.menu.toolbar_menu_victims_conditions);
        toolbar.setOnMenuItemClickListener(item ->
        {
            switch(item.getItemId())
            {
                case R.id.miCall:
                    PeopleConditions peopleConditions = new PeopleConditions(npCounts[0].getValue(), npCounts[1].getValue(), npCounts[2].getValue());
                    presenter.addPeople(peopleConditions);
                    presenter.sendReport();
                    return true;
            }
            return false;
        });

        presenter = new VictimsConditionsPresenter(getContext(), this, activity.getFragmentsManager());
        presenter.restoreData(getArguments());

        return view;
    }

    @Override
    public void showData(int totalCount)
    {
        tvCount.setText(totalCount + "");

        for(CustomNumberPicker npCount : npCounts)
        {
            npCount.setMinValue(0);
            npCount.setMaxValue(15);
            npCount.setWrapSelectorWheel(false);
            npCount.setOnValueChangedListener((numberPicker, oldValue, newValue) -> updateTotalCount());
        }
    }

    private void updateTotalCount()
    {
        totalCount = 0;
        for(CustomNumberPicker npCount : npCounts)
            totalCount += npCount.getValue();

        tvCount.setText(totalCount + "");
    }
}

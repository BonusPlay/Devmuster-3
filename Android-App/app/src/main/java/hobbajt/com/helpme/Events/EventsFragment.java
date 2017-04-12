package hobbajt.com.helpme.Events;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hobbajt.com.helpme.Base.BaseFragment;
import hobbajt.com.helpme.Create.CreateFragment;
import hobbajt.com.helpme.PeopleConditions;
import hobbajt.com.helpme.R;

public class EventsFragment extends BaseFragment implements EventsMVP.View
{
    @BindView(R.id.rvItems) RecyclerView rvItems;
    @BindView(R.id.toolbar) Toolbar toolbar;

    private EventsMVP.Presenter presenter;
    private EventsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        ButterKnife.bind(this, view);

        setHasOptionsMenu(true);
        toolbar.setTitleTextColor(android.graphics.Color.WHITE);
        toolbar.setTitle("Wybierz rodzaj zdarzenia");

        presenter = new EventsPresenter(getContext(), this, activity.getFragmentsManager());
        presenter.restoreData(getArguments());

        return view;
    }

    @Override
    public void displayData(List<? extends Object> data, boolean isCustom)
    {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        adapter = new EventsAdapter(presenter, getContext());
        rvItems.setLayoutManager(gridLayoutManager);

        if(isCustom)
        {
            setHasOptionsMenu(true);
            toolbar.setTitleTextColor(android.graphics.Color.WHITE);
            toolbar.inflateMenu(R.menu.toolbar_menu_events_own);
            toolbar.setOnMenuItemClickListener(item ->
            {
                switch(item.getItemId())
                {
                    case R.id.miAdd:
                        CreateFragment fragment = new CreateFragment();
                        activity.getFragmentsManager().changeFragment(fragment, null);
                        return true;
                }
                return false;
            });
        }
        adapter.setData(data);
        rvItems.setAdapter(adapter);
    }
}


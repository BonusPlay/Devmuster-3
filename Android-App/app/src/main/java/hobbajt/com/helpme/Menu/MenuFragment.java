package hobbajt.com.helpme.Menu;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import hobbajt.com.helpme.Base.BaseFragment;
import hobbajt.com.helpme.R;

public class MenuFragment extends BaseFragment implements MenuMVP.View
{
    @BindView(R.id.rvItems) RecyclerView rvItems;
    @BindView(R.id.toolbar) Toolbar toolbar;

    private MenuMVP.Presenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        ButterKnife.bind(this, view);

        setHasOptionsMenu(true);
        toolbar.setTitleTextColor(android.graphics.Color.WHITE);
        toolbar.setTitle("Wybierz potrzebną pomoc");

        presenter = new MenuPresenter(getContext(), this, activity.getFragmentsManager());
        displayData();

        return view;
    }

    public void displayData()
    {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        MenuAdapter adapter = new MenuAdapter(presenter, getContext());
        rvItems.setLayoutManager(gridLayoutManager);
        rvItems.setAdapter(adapter);
    }
}

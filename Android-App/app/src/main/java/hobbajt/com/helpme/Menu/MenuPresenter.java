package hobbajt.com.helpme.Menu;

import android.content.Context;

import hobbajt.com.helpme.Base.FragmentsManager;
import hobbajt.com.helpme.Events.EventsFragment;
import hobbajt.com.helpme.HelpType;

public class MenuPresenter implements MenuMVP.Presenter
{

    private final MenuMVP.View view;
    private final FragmentsManager fragmentsManager;

    public MenuPresenter(Context context, MenuMVP.View view, FragmentsManager fragmentsManager)
    {
        this.view = view;
        this.fragmentsManager = fragmentsManager;
    }

    @Override
    public void onTileClicked(HelpType helpType)
    {
        EventsFragment fragment = new EventsFragment();
        fragmentsManager.changeFragment(fragment, helpType.getName());
    }
}
package hobbajt.com.helpme.Menu;

import hobbajt.com.helpme.HelpType;

public interface MenuMVP
{
    interface Model
    {

    }

    interface View
    {
    }

    interface Presenter
    {
        void onTileClicked(HelpType helpType);
    }
}

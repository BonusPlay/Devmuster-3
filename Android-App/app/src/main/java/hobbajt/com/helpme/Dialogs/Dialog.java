package hobbajt.com.helpme.Dialogs;

import com.afollestad.materialdialogs.MaterialDialog;

public abstract class Dialog
{
    protected MaterialDialog dialog;

    public void show()
    {
        dialog.show();
    }
}

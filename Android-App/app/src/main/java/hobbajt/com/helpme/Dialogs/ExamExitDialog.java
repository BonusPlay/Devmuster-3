package hobbajt.com.helpme.Dialogs;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;

import hobbajt.com.helpme.Base.FragmentsManager;

public class ExamExitDialog extends Dialog
{
    public ExamExitDialog(Context context, FragmentsManager fragmentsManager)
    {
        dialog = new MaterialDialog.Builder(context)
                .title("Wyjście")
                .content("Czy na pewno chcesz wyjść? Niezapisane zmiany zostaną utracone.")
                .positiveText("Wyjdź")
                .negativeText("Anuluj")
                .onAny((dialog1, which) ->
                {
                    switch(which)
                    {
                        case POSITIVE:
                            fragmentsManager.backToPreviousFragment();
                            break;
                        case NEGATIVE:
                            dialog1.dismiss();
                    }
                })
                .build();
    }
}

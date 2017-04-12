package hobbajt.com.helpme.Dialogs;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import hobbajt.com.helpme.R;
import hobbajt.com.helpme.SharedPrefs.SharedPrefsWriter;
import hobbajt.com.helpme.Username;

public class UsernameDialog extends Dialog
{
    @BindView(R.id.etName) EditText etName;
    @BindView(R.id.etSurname) EditText etSurname;

    public UsernameDialog(final Context context)
    {
        dialog = new MaterialDialog.Builder(context)
                .title(R.string.username)
                .positiveText(R.string.accept)
                .negativeText(R.string.exit)
                .customView(R.layout.dialog_username, false)
                .autoDismiss(false)
                .iconRes(android.R.drawable.ic_dialog_alert)
                .onPositive((dialog, which) ->
                {
                    View view = dialog.getCustomView();
                    ButterKnife.bind(this, view);

                    Username username = new Username(etName.getText().toString(), etSurname.getText().toString());

                    if(username.isExists())
                    {
                        SharedPrefsWriter.saveIsFirstStart(false);
                        SharedPrefsWriter.saveUserName(username);
                        dialog.dismiss();
                    }

                })
                .onNegative((dialog, which) -> System.exit(0))
                .cancelable(false)
                .build();
    }
}

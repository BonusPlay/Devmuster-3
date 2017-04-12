package hobbajt.com.helpme.Dialogs;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;
import hobbajt.com.helpme.Base.Uploader;
import hobbajt.com.helpme.R;

public class ConnectionErrorDialog extends Dialog
{
    public ConnectionErrorDialog(final Uploader downloader, final Context context)
    {
        dialog = new MaterialDialog.Builder(context)
                .title(R.string.connection)
                .content(R.string.internet)
                .positiveText(R.string.try_again)
                .negativeText(R.string.exit)
                .autoDismiss(false)
                .iconRes(android.R.drawable.ic_dialog_alert)
                .onPositive((dialog, which) ->
                {
                    downloader.retry();
                    dialog.dismiss();
                })
                .onNegative((dialog, which) -> System.exit(0))
                .cancelable(false)
                .build();
    }
}

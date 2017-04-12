package hobbajt.com.helpme.Create;

import android.content.Context;

import hobbajt.com.helpme.Base.FragmentsManager;

public class CreatePresenter implements CreateMVP.Presenter
{
    private final FragmentsManager fragmentsManager;
    private final Context context;
    private final CreateMVP.View view;

    public CreatePresenter(Context context, CreateMVP.View view, FragmentsManager fragmentsManager)
    {
        this.context = context;
        this.view = view;
        this.fragmentsManager = fragmentsManager;
    }
}

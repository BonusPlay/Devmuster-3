package hobbajt.com.helpme.Base;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import hobbajt.com.helpme.Dialogs.ConnectionErrorDialog;
import hobbajt.com.helpme.Dialogs.Dialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class Uploader<T> implements Callback<T>
{
    private static final Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    private static final String BASE_URL = "http://hackaton1.beep.pl/";
    private static final Retrofit RETROFIT = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))

            .build();
    protected static final RequestInterface request = RETROFIT.create(RequestInterface.class);

    private Context context;
    protected Call<T> call;
    private Dialog dialog;

    public Uploader(Context context)
    {
        this.context = context;
    }

    @Override
    public void onFailure(Call<T> call, Throwable t)
    {
        t.printStackTrace();
        dialog = new ConnectionErrorDialog(this, context);
        dialog.show();
    }

    public void retry()
    {
        call.clone().enqueue(this);
    }
}

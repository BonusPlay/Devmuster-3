package hobbajt.com.helpme.Application;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import hobbajt.com.helpme.SharedPrefs.SharedPrefs;

public class App extends Application
{
    private static App instance;
    private static Resources resources;

    private static ImageLoader imageLoader = ImageLoader.getInstance();

    @Override
    public void onCreate()
    {
        super.onCreate();
        instance = this;
        resources = getResources();
        App.imageLoader.init(ImageLoaderConfiguration.createDefault(this));
        SharedPrefs.init(getSharedPreferences("applicationPrefs", 0));
    }

    public static Context getContext()
    {
        return instance.getApplicationContext();
    }

    public static Resources getRes()
    {
        return resources;
    }

    public static ImageLoader getImageLoader()
    {
        return imageLoader;
    }
}

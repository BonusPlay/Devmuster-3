package hobbajt.com.helpme;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import hobbajt.com.helpme.Location.Location;

public class ReportUploader extends AsyncTask<String, String, String>// extends Uploader<String>
{
    public ReportUploader()
    {
        Location location = new Location(5.32623, 9.23662);
        Report report = new Report();//("070012345", location, 6, 3, "Jan", "Kowalski", 3);
        JsonElement object = new Gson().toJsonTree(report);
        Log.d("test", object.toString());

        execute(object.toString());
    }
    /* ReportUploader(Context context*//*, NavigationMenuMVP.Presenter presenter*//*)
    {
        super(context);
        //this.presenter = presenter;
    }*/
/*
    public void upload()
    {
        Location location = new Location(5.32623, 9.23662);
        Report report = new Report("070012345", location, 6, 3, "Jan", "Kowalski", 3);
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("data", new Gson().toJsonTree(report));

        Log.d("test", jsonObject.toString());
        call = request.sendReport(jsonObject);
        call.enqueue(this);
    }


    @Override
    public void onResponse(Call<String> call, Response<String> response)
    {

        Log.d("test", response.body());
    }

    @Override
    public void onFailure(Call<String> call, Throwable t)
    {
        super.onFailure(call, t);
        t.printStackTrace();
        Log.d("test", "Sprzedam opla!");
    }*/

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params)
    {
        String url = "http://51.254.130.93:3070/event";
        InputStream inputStream = null;
        String result = "";

        try
        {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);

            String json = "";

            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("data", params[0]);

            json = jsonObject.toString();

            StringEntity se = new StringEntity(json);
            httpPost.setEntity(se);

            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            HttpResponse httpResponse = httpclient.execute(httpPost);

            inputStream = httpResponse.getEntity().getContent();

            if(inputStream != null)
            {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while((line = bufferedReader.readLine()) != null)
                    result += line;

                inputStream.close();
                Log.i("dada", result);
                return result;
            } else
                result = "Did not work!";

        } catch(Exception e)
        {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
    }

    @Override
    protected void onPostExecute(String s)
    {
        super.onPostExecute(s);
        Log.d("test", s);

    }
}

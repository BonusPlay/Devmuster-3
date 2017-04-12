package pl.bonusplay.smsgate;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.telephony.SmsMessage;
import android.util.Log;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by BonusPlay on 2017-04-07.
 */

public class IncomingSms extends BroadcastReceiver
{
	@Override
	public void onReceive(Context context, Intent intent)
	{
		Object[] pdus = (Object[]) intent.getExtras().get("pdus");

		if (pdus != null && pdus.length > 0)
		{
			SmsMessage[] messages = new SmsMessage[pdus.length];

			for (int i = 0; i < pdus.length; i++)
				messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);

			SmsMessage message = messages[0];
			String text;

			if (messages.length == 1 || message.isReplace())
				text = message.getDisplayMessageBody();
			else
			{
				StringBuilder textBuilder = new StringBuilder();
				for (SmsMessage msg : messages)
					textBuilder.append(msg.getMessageBody());

				text = textBuilder.toString();
			}

			String number = message.getDisplayOriginatingAddress();

			Log.i("Number", number);
			Log.i("Text", text);

			new CallAPI().execute(text);
		}
	}

	public class CallAPI extends AsyncTask<String, String, String>
	{
		public CallAPI()
		{}

		@Override
		protected void onPreExecute()
		{
			super.onPreExecute();
		}

		@Override
		protected String doInBackground(String... params)
		{
			String url = "http://hackaton1.beep.pl/receiveData.php";
			InputStream inputStream = null;
			String result = "";

			try
			{
				HttpClient httpclient = new DefaultHttpClient();

				HttpPost httpPost = new HttpPost(url);

				String json = "";

				JSONObject jsonObject = new JSONObject();
				jsonObject.accumulate("message", params[0]);

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
				}
				else
					result = "Did not work!";

			}
			catch (Exception e)
			{
				Log.d("InputStream", e.getLocalizedMessage());
			}

			return result;
		}
	}
}
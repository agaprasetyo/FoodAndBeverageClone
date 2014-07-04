package id.knt.restaurant.foodandbeverage.service.proxy;

import id.knt.restaurant.foodandbeverage.Application;
import id.knt.restaurant.foodandbeverage.service.json.JSONConstant;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

/**
 * Get data from url then return String result 
 * @author cindy.situmorang
 */

public class ProxyAsyncTask extends AsyncTask<String, Void, String> {

	private AsyncCallback callback;

	public ProxyAsyncTask(AsyncCallback callback) {
		this.callback = callback;
	}

	// Check internet connection
	private void checkInternetConnection() {
		ConnectivityManager connMgr = (ConnectivityManager) Application
				.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connMgr != null) {
			NetworkInfo[] info = connMgr.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						this.callback.onPreExecute("Connecting ..");
					}
				}
			}
		} else {
			this.callback.onPreExecute("No internet connection");
		}
	}

	@Override
	protected void onPreExecute() {
		checkInternetConnection();
	}

	@Override
	protected String doInBackground(String... params) {
		try {
			String address = (String) params[0];
			StringBuilder builder = new StringBuilder();

			URL url = new URL(address);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(10000);
			conn.setConnectTimeout(15000);
			conn.setRequestMethod("GET");
			conn.setDoInput(true);
			conn.connect();

			InputStream inputStream = conn.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputStream, "UTF-8"));

			String data = null;

			while ((data = reader.readLine()) != null) {
				builder.append(data);
			}

			return builder.toString();

		} catch (Exception e) {
			return new String(JSONConstant.ERROR_MESSAGE + " :" + e.getMessage());
		}
	}

	@Override
	protected void onPostExecute(String result) {
		this.callback.onPostExecute(result);
	}

}

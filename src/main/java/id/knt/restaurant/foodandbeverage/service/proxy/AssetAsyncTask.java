package id.knt.restaurant.foodandbeverage.service.proxy;

import id.knt.restaurant.foodandbeverage.Application;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import android.os.AsyncTask;

/**
 * get data from file asset then return String result
 * @author cindy.situmorang
 */

public class AssetAsyncTask extends AsyncTask<String, Void, String> {

	private AsyncCallback callback;

	public AssetAsyncTask(AsyncCallback callback) {
		this.callback = callback;
	}

	@Override
	protected void onPreExecute() {
		this.callback.onPreExecute("Connecting ..");
	}

	@Override
	protected String doInBackground(String... params) {
		try {
			String address = (String) params[0];
			StringBuilder builder = new StringBuilder();

			InputStream inputStream = Application.getInstance().getAssets().open(address);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputStream, "UTF-8"));

			String data = null;

			while ((data = reader.readLine()) != null) {
				builder.append(data);
			}

			return builder.toString();

		} catch (Exception e) {
			return new String("Exception: " + e.getMessage());
		}
	}

	@Override
	protected void onPostExecute(String result) {
		this.callback.onPostExecute(result);
	}

}

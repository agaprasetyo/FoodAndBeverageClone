package id.knt.restaurant.foodandbeverage.service.proxy;

/**
 * callback from asynctask
 * @author cindy.situmorang
 *
 */

public interface AsyncCallback {
	void onPreExecute(String data);
	void doInBackground(String data);
	void onPostExecute(String data);
}

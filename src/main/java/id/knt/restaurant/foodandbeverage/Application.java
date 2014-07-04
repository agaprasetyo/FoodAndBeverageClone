package id.knt.restaurant.foodandbeverage;

/**
 * Global context
 * @author cindy.situmorang
 *
 */
public class Application extends android.app.Application {
	private static Application instance;

	public static Application getInstance() {
		if (instance == null)
			throw new IllegalStateException();
		return instance;
	}

	public Application() {
		instance = this;

	}
}

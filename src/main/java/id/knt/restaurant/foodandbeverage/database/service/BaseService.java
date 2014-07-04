package id.knt.restaurant.foodandbeverage.database.service;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;

public abstract class BaseService {

	protected final String tag;

	protected final Context context;
	protected final RepositoryHelper repositoryHelper;

	public BaseService( Context context, Class<? extends BaseService> instanceClass ) {
		this.context = context;
		this.repositoryHelper = OpenHelperManager.getHelper( context, RepositoryHelper.class );
		this.tag = instanceClass.getName();
	}
}

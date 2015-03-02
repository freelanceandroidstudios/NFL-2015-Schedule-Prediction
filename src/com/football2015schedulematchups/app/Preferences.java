package com.football2015schedulematchups.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preferences {

	private Context context;
	private SharedPreferences preferences;

	public Preferences(Context context) {
		setContext(context);
		setPreferences((PreferenceManager
				.getDefaultSharedPreferences(getContext()
						.getApplicationContext())));
	}

	public boolean isFirstRun() {
		return getPreferences().getBoolean(Keys.FIRST, true);
	}

	public void setFirstRun(boolean b) {
		getPreferences().edit().putBoolean(Keys.FIRST, b).apply();
	}

	public boolean hasAppShared() {
		return getPreferences().getBoolean(Keys.SHARED, false);
	}

	public void setHasAppShared(boolean b) {
		getPreferences().edit().putBoolean(Keys.SHARED, b).apply();
		;
	}

	/**
	 * @return the context
	 */
	public Context getContext() {
		return context;
	}

	/**
	 * @param context
	 *            the context to set
	 */
	public void setContext(Context context) {
		this.context = context;
	}

	/**
	 * @return the preferences
	 */
	public SharedPreferences getPreferences() {
		return preferences;
	}

	/**
	 * @param preferences
	 *            the preferences to set
	 */
	public void setPreferences(SharedPreferences preferences) {
		this.preferences = preferences;
	}

}

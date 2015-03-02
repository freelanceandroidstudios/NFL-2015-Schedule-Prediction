package com.football2015schedulematchups.app;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MatchupsActivity extends ActionBarActivity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {

	private TeamNames teamNames;
	private int stored_position;

	MenuItem item;
	private Activity activity;

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_matchups);

		setActivity(this);

		setTeamNames(new TeamNames());

		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
				.findFragmentById(R.id.navigation_drawer);

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));

	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		LoadingFragment loadingFrag = new LoadingFragment();
		Bundle bundle = new Bundle();
		bundle.putInt(Keys.TEAM_POS, position);
		loadingFrag.setArguments(bundle);
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.popBackStackImmediate(null,
				FragmentManager.POP_BACK_STACK_INCLUSIVE);
		fragmentManager.beginTransaction().replace(R.id.container, loadingFrag)
				.commit();
		setStored_position(position);
	}

	public void onSectionAttached(int number) {
		mTitle = getTeamNames().getTeamnames(number);
	}

	public void restoreActionBar() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		onSectionAttached(stored_position);
		actionBar.setTitle(mTitle);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.matchups, menu);
			// Locate MenuItem with ShareActionProvider

			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	/**
	 * @return the teamNames
	 */
	public TeamNames getTeamNames() {
		return teamNames;
	}

	/**
	 * @param teamNames
	 *            the teamNames to set
	 */
	public void setTeamNames(TeamNames teamNames) {
		this.teamNames = teamNames;
	}

	/**
	 * @return the stored_position
	 */
	public int getStored_position() {
		return stored_position;
	}

	/**
	 * @param stored_position
	 *            the stored_position to set
	 */
	public void setStored_position(int stored_position) {
		this.stored_position = stored_position;
	}

	/**
	 * @return the activity
	 */
	public Activity getActivity() {
		return activity;
	}

	/**
	 * @param activity
	 *            the activity to set
	 */
	public void setActivity(Activity activity) {
		this.activity = activity;
	}

}

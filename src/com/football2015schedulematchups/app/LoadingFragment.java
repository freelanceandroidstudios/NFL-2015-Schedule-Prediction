/**
 * 
 */
package com.football2015schedulematchups.app;

import android.os.Bundle;
import android.os.AsyncTask.Status;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @author Scott Auman
 *
 */
public class LoadingFragment extends Fragment {

	private RelativeLayout layout;
	private TextView loadingText;
	private int team_position;
	private ParseTeamInformationTask parseTeamInfoTask;

	/**
	 * 
	 */
	public LoadingFragment() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		if (getParseTeamInfoTask() != null
				&& getParseTeamInfoTask().getStatus() != Status.RUNNING) {
			// cancel current asynctask
			try {
				getParseTeamInfoTask().cancel(true);
			} catch (Exception ex) {
				Log.e(getClass().getSimpleName(),
						"exception at cancel Asynctask");
			}
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View thisView = inflater.inflate(R.layout.loading_fragment, null, true);

		setLayout((RelativeLayout) thisView
				.findViewById(R.id.loadingFragmentLayout));
		setLoadingText((TextView) thisView
				.findViewById(R.id.loading_text_textview));

		getLayout().setBackgroundColor(
				getActivity().getResources().getColor(
						new TeamColors().getTeamcolors()
								.get(getTeam_position())));

		return thisView;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTeam_position(getArguments().getInt(Keys.TEAM_POS));
		// get postition of team passed from main activity

		// start bG task to parse team data and return back to fragment when
		// taks completed
		setParseTeamInfoTask(new ParseTeamInformationTask(taskStatus,
				getActivity()));
		getParseTeamInfoTask().execute(getTeam_position());
	}

	TaskStatus taskStatus = new TaskStatus() {

		@Override
		public void onComplete(Teams team) {

			Log.i(getClass().getSimpleName(), team.toString());

			MatchupFragment matchupFragment = new MatchupFragment();
			Bundle bundle = new Bundle();
			bundle.putParcelableArrayList(Keys.TEAM_INFO, team);
			bundle.putInt(Keys.TEAM_POS, getTeam_position());
			matchupFragment.setArguments(bundle);
			FragmentManager manager = getActivity().getSupportFragmentManager();
			FragmentTransaction tran = manager.beginTransaction();
			tran.setCustomAnimations(android.R.anim.fade_in,
					android.R.anim.fade_out);
			tran.replace(R.id.container, matchupFragment).commit();
		}

		@Override
		public void onCancelled() {
			// TODO Auto-generated method stub

		}
	};

	/**
	 * @return the layout
	 */
	public RelativeLayout getLayout() {
		return layout;
	}

	/**
	 * @param layout
	 *            the layout to set
	 */
	public void setLayout(RelativeLayout layout) {
		this.layout = layout;
	}

	/**
	 * @return the loadingText
	 */
	public TextView getLoadingText() {
		return loadingText;
	}

	/**
	 * @param loadingText
	 *            the loadingText to set
	 */
	public void setLoadingText(TextView loadingText) {
		this.loadingText = loadingText;
	}

	/**
	 * @return the team_position
	 */
	public int getTeam_position() {
		return team_position;
	}

	/**
	 * @param team_position
	 *            the team_position to set
	 */
	public void setTeam_position(int team_position) {
		this.team_position = team_position;
	}

	/**
	 * @return the parseTeamInfoTask
	 */
	public ParseTeamInformationTask getParseTeamInfoTask() {
		return parseTeamInfoTask;
	}

	/**
	 * @param parseTeamInfoTask
	 *            the parseTeamInfoTask to set
	 */
	public void setParseTeamInfoTask(ParseTeamInformationTask parseTeamInfoTask) {
		this.parseTeamInfoTask = parseTeamInfoTask;
	}

}

package com.football2015schedulematchups.app;

import java.util.ArrayList;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableRow;
import android.widget.TextView;

public class MatchupFragment extends Fragment {

	private int team_position;
	private Teams teams;
	private LinearLayout homeLayout, visitorLayout, matchupLayout;
	private Buttons buttons;
	private Button resetButton;
	private TextView recordTextView, recordBottomTextView;
	private ImageView shareImageView;
	private ScrollView scrollViewLayout;
	MenuItem shareItem;
	View thisView;
	ShareButton shareButton;

	ArrayList<TableRow> tableRows = new ArrayList<TableRow>();

	public MatchupFragment() {
		// TODO Auto-generated constructor stub
	}

	RecordChange recordChange = new RecordChange() {

		@Override
		public void resetButtons() {
			// TODO Auto-generated method stub

		}

		@Override
		public void addWin() {
			String[] splitter = getRecordTextView().getText().toString()
					.split("-");
			int wins = Integer.parseInt(splitter[0]);
			int losses = Integer.parseInt(splitter[1]);

			wins++;
			getRecordTextView().setText(wins + "-" + losses);
			getRecordBottomTextView().setText(wins + "-" + losses);

			if (checkFor16()) {
				scrollViewToTop();
			} else {

			}

		}

		@Override
		public void addLoss() {
			String[] splitter = getRecordTextView().getText().toString()
					.split("-");
			int wins = Integer.parseInt(splitter[0]);
			int losses = Integer.parseInt(splitter[1]);

			losses++;
			getRecordTextView().setText(wins + "-" + losses);
			getRecordBottomTextView().setText(wins + "-" + losses);

			if (checkFor16()) {
				scrollViewToTop();
			} else {

			}

		}

		@Override
		public void removeWin() {
			String[] splitter = getRecordTextView().getText().toString()
					.split("-");
			int wins = Integer.parseInt(splitter[0]);
			int losses = Integer.parseInt(splitter[1]);

			--wins;
			getRecordTextView().setText(wins + "-" + losses);
			getRecordBottomTextView().setText(wins + "-" + losses);

			if (checkFor16()) {
				scrollViewToTop();
			} else {

			}

		}

		@Override
		public void removeLoss() {
			String[] splitter = getRecordTextView().getText().toString()
					.split("-");
			int wins = Integer.parseInt(splitter[0]);
			int losses = Integer.parseInt(splitter[1]);

			--losses;
			getRecordTextView().setText(wins + "-" + losses);
			getRecordBottomTextView().setText(wins + "-" + losses);

			if (checkFor16()) {
				scrollViewToTop();
			} else {
			}

		}
	};

	private void scrollViewToTop() {
		getScrollViewLayout().fullScroll(ScrollView.FOCUS_UP);

		if (!shareButton.isShowing()) {
			getMatchupLayout().addView(shareButton, 1);
			shareButton.setShowing(true);

			Button shareButton = (Button) this.shareButton
					.findViewById(R.id.shareResultButton);
			shareButton.setOnClickListener(new ShareButtonClicker());
		}
	}

	class ShareButtonClicker implements OnClickListener {

		@Override
		public void onClick(View v) {
			popShareDialog();
		}

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ArrayList<Team> placeholder = new ArrayList<Team>();
		placeholder = getArguments().getParcelableArrayList(Keys.TEAM_INFO);
		setTeams(new Teams());
		setTeams((Teams) placeholder);
		setTeam_position(getArguments().getInt(Keys.TEAM_POS));

		shareButton = new ShareButton(getActivity());

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		thisView = inflater.inflate(R.layout.fragment_matchups, null, true);

		TextView teamName = (TextView) thisView
				.findViewById(R.id.team_name_matchup_textview);

		setMatchupLayout((LinearLayout) thisView
				.findViewById(R.id.matchup_fragment_layout));

		setScrollViewLayout((ScrollView) thisView
				.findViewById(R.id.team_scroll_view));

		TextView conferenceDivision = (TextView) thisView
				.findViewById(R.id.conference_division_textview);

		conferenceDivision.setText(getTeams().get(team_position)
				.getConference()
				+ " "
				+ getTeams().get(team_position).getDivision());

		setRecordTextView((TextView) thisView
				.findViewById(R.id.wins_losses_textview));
		setRecordBottomTextView((TextView) thisView
				.findViewById(R.id.wins_losses_textview_bottom));

		new Fonts().setFontRobotoBlackItalics(conferenceDivision);
		new Fonts().setFontGrandeeCP(teamName);

		TextView homeOpponents = (TextView) thisView
				.findViewById(R.id.home_opponents_textview);
		TextView visitorOpponents = (TextView) thisView
				.findViewById(R.id.visitor_opponenets_textview);

		underlineTextViews(homeOpponents, visitorOpponents);

		teamName.setText(new TeamNames().getTeamnames(getTeam_position()));

		setHomeLayout((LinearLayout) thisView
				.findViewById(R.id.homeTeamsLayout));
		setVisitorLayout((LinearLayout) thisView
				.findViewById(R.id.visitorTeamsLayout));
		// attach team names to each layout

		setHomeTeams();
		setVisitorTeams();

		setResetButton((Button) thisView.findViewById(R.id.resetButton));
		getResetButton().setOnClickListener(new ResetSelections());

		return thisView;

	}

	/**
	 * @param homeOpponents
	 * @param visitorOpponents
	 */
	private void underlineTextViews(TextView homeOpponents,
			TextView visitorOpponents) {
		homeOpponents.setPaintFlags(homeOpponents.getPaintFlags()
				| Paint.UNDERLINE_TEXT_FLAG);

		visitorOpponents.setPaintFlags(visitorOpponents.getPaintFlags()
				| Paint.UNDERLINE_TEXT_FLAG);
	}

	/**
	 * 
	 */
	private void setVisitorTeams() {
		for (int i = 0; i < 8; i++) {
			TeamTableRow teamRow = new TeamTableRow(getActivity(), getTeams()
					.get(getTeam_position()).getVisitorTeams().getTeams()
					.get(i), buttons, recordChange);
			getVisitorLayout().addView(teamRow,
					getVisitorLayout().getChildCount() - 1);
			tableRows.add(teamRow);
		}
	}

	/**
	 * 
	 */
	private void setHomeTeams() {
		for (int i = 0; i < 8; i++) {
			TeamTableRow teamRow = new TeamTableRow(getActivity(), getTeams()
					.get(getTeam_position()).getHomeTeams().getTeams().get(i),
					buttons, recordChange);
			getHomeLayout().addView(teamRow,
					getHomeLayout().getChildCount() - 1);
			tableRows.add(teamRow);
		}
	}

	class ResetSelections implements OnClickListener {

		@Override
		public void onClick(View v) {

			// remove all tablerows and re -add then
			// creating new isntances that reset all variables and flags

			getHomeLayout().removeViews(1, 8);
			setHomeTeams();

			getVisitorLayout().removeViews(1, 8);
			setVisitorTeams();
			// reset record textview to 0-0
			resetRecord();
			shareButton.setShowing(false);
			getMatchupLayout().removeView(shareButton);

		}

	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
	}

	private void popShareDialog() {

		ShareFragment shareFragment = new ShareFragment();
		FragmentManager fManager = getActivity().getSupportFragmentManager();
		Bundle bundle = new Bundle();
		bundle.putParcelable(Keys.RESULTS, buildResultsObject());
		bundle.putInt(Keys.TEAM_POS, getTeam_position());
		shareFragment.setArguments(bundle);
		FragmentTransaction fTrans = fManager.beginTransaction();
		fTrans.setCustomAnimations(android.R.anim.slide_in_left,
				android.R.anim.slide_out_right, android.R.anim.slide_in_left,
				android.R.anim.slide_out_right);
		fTrans.add(R.id.container, shareFragment).addToBackStack(null).commit();

	}

	private TeamResults buildResultsObject() {

		TeamResults tResults = new TeamResults();
		tResults.setTeamName(new TeamNames().getTeamnames(getTeam_position()));
		tResults.setFinalRecord(getRecordTextView().getText().toString());
		tResults.setLastYears(getTeams().get(getTeam_position()).getLastYears());

		for (int i = 1; i < getHomeLayout().getChildCount() - 1; i++) {
			HomeResults hResult = new HomeResults();
			View view = getHomeLayout().getChildAt(i);
			TextView name = (TextView) view
					.findViewById(R.id.team_name_textview);
			Button button = (Button) view.findViewById(R.id.gameButton);

			hResult.setName(name.getText().toString());
			if (button.getText().toString().equals("W")) {
				hResult.setWin(true);
			} else {
				hResult.setWin(false);
			}

			tResults.getHomeResults().add(hResult);
		}

		for (int i = 1; i < getVisitorLayout().getChildCount() - 1; i++) {
			VisitorResults vResult = new VisitorResults();
			View view = getVisitorLayout().getChildAt(i);
			TextView name = (TextView) view
					.findViewById(R.id.team_name_textview);
			Button button = (Button) view.findViewById(R.id.gameButton);

			vResult.setName(name.getText().toString());
			if (button.getText().toString().equals("W")) {
				vResult.setWin(true);
			} else {
				vResult.setWin(false);
			}

			tResults.getVisitorResults().add(vResult);
		}

		return tResults;

	}

	private boolean checkFor16() {
		String[] splitter = getRecordTextView().getText().toString().split("-");
		int wins = Integer.parseInt(splitter[0]);
		int losses = Integer.parseInt(splitter[1]);

		if (wins + losses == 16) {
			return true;
		}
		return false;
	}

	private String determineSeasonType() {

		String[] splitter = getRecordTextView().getText().toString().split("-");
		int wins = Integer.parseInt(splitter[0]);

		if (wins < 4) {
			return "High Draft Pick For Next Year....";
		} else if (wins > 9 && wins < 13) {
			return "Playoff Bound!";
		} else if (wins == 8) {
			return "Another 500 Year...";
		} else if (wins > 8 && wins < 10) {
			return "Wild Card!";
		} else if (wins > 12 && wins < 16) {
			return "Number One Seed!";
		} else if (wins == 16) {
			return "Perfect Season!";
		} else {
			return "Optimistic!";
		}

	}

	private void resetRecord() {
		getRecordTextView().setText("0-0");
		getRecordBottomTextView().setText("0-0");
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
	 * @return the teams
	 */
	public Teams getTeams() {
		return teams;
	}

	/**
	 * @param teams
	 *            the teams to set
	 */
	public void setTeams(Teams teams) {
		this.teams = teams;
	}

	/**
	 * @return the homeLayout
	 */
	public LinearLayout getHomeLayout() {
		return homeLayout;
	}

	/**
	 * @param homeLayout
	 *            the homeLayout to set
	 */
	public void setHomeLayout(LinearLayout homeLayout) {
		this.homeLayout = homeLayout;
	}

	/**
	 * @return the visitorLayout
	 */
	public LinearLayout getVisitorLayout() {
		return visitorLayout;
	}

	/**
	 * @param visitorLayout
	 *            the visitorLayout to set
	 */
	public void setVisitorLayout(LinearLayout visitorLayout) {
		this.visitorLayout = visitorLayout;
	}

	/**
	 * @return the buttons
	 */
	public Buttons getButtons() {
		return buttons;
	}

	/**
	 * @param buttons
	 *            the buttons to set
	 */
	public void setButtons(Buttons buttons) {
		this.buttons = buttons;
	}

	/**
	 * @return the resetButton
	 */
	public Button getResetButton() {
		return resetButton;
	}

	/**
	 * @param resetButton
	 *            the resetButton to set
	 */
	public void setResetButton(Button resetButton) {
		this.resetButton = resetButton;
	}

	/**
	 * @return the recordTextView
	 */
	public TextView getRecordTextView() {
		return recordTextView;
	}

	/**
	 * @param recordTextView
	 *            the recordTextView to set
	 */
	public void setRecordTextView(TextView recordTextView) {
		this.recordTextView = recordTextView;
	}

	/**
	 * @return the shareImageView
	 */
	public ImageView getShareImageView() {
		return shareImageView;
	}

	/**
	 * @param shareImageView
	 *            the shareImageView to set
	 */
	public void setShareImageView(ImageView shareImageView) {
		this.shareImageView = shareImageView;
	}

	/**
	 * @return the recordBottomTextView
	 */
	public TextView getRecordBottomTextView() {
		return recordBottomTextView;
	}

	/**
	 * @param recordBottomTextView
	 *            the recordBottomTextView to set
	 */
	public void setRecordBottomTextView(TextView recordBottomTextView) {
		this.recordBottomTextView = recordBottomTextView;
	}

	/**
	 * @return the scrollViewLayout
	 */
	public ScrollView getScrollViewLayout() {
		return scrollViewLayout;
	}

	/**
	 * @param scrollViewLayout
	 *            the scrollViewLayout to set
	 */
	public void setScrollViewLayout(ScrollView scrollViewLayout) {
		this.scrollViewLayout = scrollViewLayout;
	}

	/**
	 * @return the matchupLayout
	 */
	public LinearLayout getMatchupLayout() {
		return matchupLayout;
	}

	/**
	 * @param matchupLayout
	 *            the matchupLayout to set
	 */
	public void setMatchupLayout(LinearLayout matchupLayout) {
		this.matchupLayout = matchupLayout;
	}

}

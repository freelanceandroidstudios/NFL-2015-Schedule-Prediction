/**
 * 
 */
package com.football2015schedulematchups.app;

import java.util.Locale;

import com.football2015schedulematchups.app.NavigationDrawerFragment.NavigationDrawerCallbacks;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * @author Scott Auman
 *
 */
public class ShareFragment extends Fragment {

	private LinearLayout layout, homeLayout, visitorLayout;
	private TextView finalRecord, teamName, changeInWins, lastYearsRecord;
	View view;
	private ScrollView scrollViewlayout;
	private ImageView differenceImageView;
	SaveScreenshot saveScreenshot;
	TeamResults teamResults;
	ChangeType changeType;
	int team_pos;
	private Button shareButton;

	enum ChangeType {
		WINS, LOSSES, SAME
	}

	public enum GameType {
		HOME, AWAY
	}

	/**
	 * 
	 */
	public ShareFragment() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.share_view, null, true);

		assignViewElements();

		buildHomeGames();

		buildVisitorGames();

		getFinalRecord().setText(teamResults.getFinalRecord());
		getTeamName().setText(teamResults.getTeamName());
		getChangeInWins().setText(determineChangeInRecord());
		getDifferenceImageView().setImageDrawable(
				getActivity().getResources().getDrawable(determineImageType()));
		getLastYearsRecord().setText(
				"2014 Record: " + teamResults.getLastYears());

		setButtonClicker();

		new Fonts().setFontHugMeTight(getTeamName());
		new Fonts().setFontRobotoBlackItalics(getLastYearsRecord());
		new Fonts().setPointsFont(getFinalRecord());
		new Fonts().setPointsFont(getChangeInWins());
		return view;
	}

	private Integer determineImageType() {
		switch (changeType) {
		case WINS:
			return R.drawable.green_arrow;
		case LOSSES:
			return R.drawable.red_arrow;
		case SAME:
			return R.drawable.equal_icon;
		default:
			return null;
		}
	}

	private String determineChangeInRecord() {

		String[] s = teamResults.getFinalRecord().split("-");
		int winsF = Integer.parseInt(s[0]);
		int lossesF = Integer.parseInt(s[1]);
		int tieF;
		try {
			// see if there is a tie game!!!
			tieF = Integer.parseInt(s[2]);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		String[] last = teamResults.getLastYears().split("-");
		int winsL = Integer.parseInt(last[0]);
		int lossesL = Integer.parseInt(last[1]);
		int tieL = 0;
		try {
			// see if there is a tie game!!!
			tieL = Integer.parseInt(s[2]);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// find out which is changed!
		if (winsF > winsL) {
			// find diff
			changeType = ChangeType.WINS;
			if (tieL > 0) {
				return (((winsF - winsL) - (tieL * .5) + ""));
			}
			return (winsF - winsL + "");
		} else if (winsL == winsF) {
			// same record
			changeType = ChangeType.SAME;
			return "No Change";
		} else {
			// more losses
			changeType = ChangeType.LOSSES;
			// find diff
			if (tieL > 0) {
				return (((double) (lossesF - lossesL) - (double) (tieL * .50) + ""));
			}
			return (lossesF - lossesL + "");
		}
	}

	/**
	 * 
	 */
	private void setButtonClicker() {
		getShareButton().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// remove view so its not in the screenshot
				getShareButton().setVisibility(View.INVISIBLE);
				saveScreenshot.saveBitmap(takeScreenshot());
				shareToApp();
				getShareButton().setVisibility(View.VISIBLE);
				getActivity().getSupportFragmentManager().popBackStack();
			}
		});
	}

	/**
	 * 
	 */
	private void buildVisitorGames() {
		for (int i = 0; i < 8; i++) {
			getVisitorLayout()
					.addView(
							new ShareTableRow(getActivity(), teamResults
									.getVisitorResults().get(i).getName(),
									teamResults.getVisitorResults().get(i)
											.isWin(), GameType.AWAY),
							getVisitorLayout().getChildCount());
		}
	}

	/**
	 * 
	 */
	private void buildHomeGames() {
		for (int i = 0; i < 8; i++) {
			getHomeLayout().addView(
					new ShareTableRow(getActivity(), teamResults
							.getHomeResults().get(i).getName(), teamResults
							.getHomeResults().get(i).isWin(), GameType.HOME),
					getHomeLayout().getChildCount());
		}
	}

	/**
	 * 
	 */
	private void assignViewElements() {

		setHomeLayout((LinearLayout) view.findViewById(R.id.homeLayout));
		setVisitorLayout((LinearLayout) view.findViewById(R.id.visitorLayout));
		setFinalRecord((TextView) view.findViewById(R.id.finalRecordTextView));
		setTeamName((TextView) view.findViewById(R.id.team_name_share_textview));
		setLayout((LinearLayout) view.findViewById(R.id.shareLayout));
		setChangeInWins((TextView) view.findViewById(R.id.winsChangeTextView));
		setDifferenceImageView((ImageView) view
				.findViewById(R.id.changeIamgeView));
		setShareButton((Button) view.findViewById(R.id.sharebutton));
		setLastYearsRecord((TextView) view
				.findViewById(R.id.lastYearsRecordTextView));
		setScrollViewlayout((ScrollView) view
				.findViewById(R.id.shareViewScrollView));

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		saveScreenshot = new SaveScreenshot(getActivity());

		teamResults = new TeamResults();
		teamResults = getArguments().getParcelable(Keys.RESULTS);

		team_pos = getArguments().getInt(Keys.TEAM_POS);
	}

	public Bitmap takeScreenshot() {
		view.setDrawingCacheEnabled(true);
		return view.getDrawingCache();
	}

	public void shareToApp() {
		String text = "https://play.google.com/store/apps/details?"
				+ "id=com.football2015schedulematchups.app";
		Intent shareIntent = new Intent();
		shareIntent.setAction(Intent.ACTION_SEND);
		shareIntent.putExtra(Intent.EXTRA_TEXT, text);
		shareIntent.putExtra(Intent.EXTRA_STREAM,
				Uri.parse(saveScreenshot.getUriImage()));
		shareIntent.setType("image/*");
		shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
		startActivity(Intent.createChooser(shareIntent,
				"Share " + teamResults.getTeamName() + " Record With..."));
	}

	/**
	 * @return the layout
	 */
	public LinearLayout getLayout() {
		return layout;
	}

	/**
	 * @return the homeLayout
	 */
	public LinearLayout getHomeLayout() {
		return homeLayout;
	}

	/**
	 * @return the visitorLayout
	 */
	public LinearLayout getVisitorLayout() {
		return visitorLayout;
	}

	/**
	 * @param layout
	 *            the layout to set
	 */
	public void setLayout(LinearLayout layout) {
		this.layout = layout;
	}

	/**
	 * @param homeLayout
	 *            the homeLayout to set
	 */
	public void setHomeLayout(LinearLayout homeLayout) {
		this.homeLayout = homeLayout;
	}

	/**
	 * @param visitorLayout
	 *            the visitorLayout to set
	 */
	public void setVisitorLayout(LinearLayout visitorLayout) {
		this.visitorLayout = visitorLayout;
	}

	/**
	 * @return the finalRecord
	 */
	public TextView getFinalRecord() {
		return finalRecord;
	}

	/**
	 * @param finalRecord
	 *            the finalRecord to set
	 */
	public void setFinalRecord(TextView finalRecord) {
		this.finalRecord = finalRecord;
	}

	/**
	 * @return the teamName
	 */
	public TextView getTeamName() {
		return teamName;
	}

	/**
	 * @param teamName
	 *            the teamName to set
	 */
	public void setTeamName(TextView teamName) {
		this.teamName = teamName;
	}

	/**
	 * @return the shareButton
	 */
	public Button getShareButton() {
		return shareButton;
	}

	/**
	 * @param shareButton
	 *            the shareButton to set
	 */
	public void setShareButton(Button shareButton) {
		this.shareButton = shareButton;
	}

	/**
	 * @return the changeInWins
	 */
	public TextView getChangeInWins() {
		return changeInWins;
	}

	/**
	 * @param changeInWins
	 *            the changeInWins to set
	 */
	public void setChangeInWins(TextView changeInWins) {
		this.changeInWins = changeInWins;
	}

	/**
	 * @return the differenceImageView
	 */
	public ImageView getDifferenceImageView() {
		return differenceImageView;
	}

	/**
	 * @param differenceImageView
	 *            the differenceImageView to set
	 */
	public void setDifferenceImageView(ImageView differenceImageView) {
		this.differenceImageView = differenceImageView;
	}

	/**
	 * @return the lastYearsRecord
	 */
	public TextView getLastYearsRecord() {
		return lastYearsRecord;
	}

	/**
	 * @param lastYearsRecord
	 *            the lastYearsRecord to set
	 */
	public void setLastYearsRecord(TextView lastYearsRecord) {
		this.lastYearsRecord = lastYearsRecord;
	}

	/**
	 * @return the scrollViewlayout
	 */
	public ScrollView getScrollViewlayout() {
		return scrollViewlayout;
	}

	/**
	 * @param scrollViewlayout
	 *            the scrollViewlayout to set
	 */
	public void setScrollViewlayout(ScrollView scrollViewlayout) {
		this.scrollViewlayout = scrollViewlayout;
	}

}

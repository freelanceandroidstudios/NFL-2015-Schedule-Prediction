package com.football2015schedulematchups.app;

import com.football2015schedulematchups.app.ShareFragment.GameType;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

public class ShareTableRow extends TableRow {

	public ShareTableRow(Context context, String name, boolean win,
			GameType gameType) {
		super(context);

		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		inflater.inflate(R.layout.team_share_row, this, true);
		assignData(name, win, gameType);

	}

	private void assignData(String name, boolean win, GameType gameType) {

		TextView nameTV = (TextView) this
				.findViewById(R.id.team_name_share_textview);
		ImageView winLossImageView = (ImageView) this
				.findViewById(R.id.win_or_loss_share_iamgeview);

		nameTV.setText(name);
		
		determineGameType(gameType, nameTV);
		determineWinOrLoss(win, winLossImageView);

	}

	/**
	 * @param win
	 * @param winLossImageView
	 * @throws NotFoundException
	 */
	private void determineWinOrLoss(boolean win, ImageView winLossImageView)
			throws NotFoundException {
		if (win) {
			winLossImageView.setImageDrawable(getContext().getResources()
					.getDrawable(R.drawable.check_mark));

		} else {
			winLossImageView.setImageDrawable(getContext().getResources()
					.getDrawable(R.drawable.x_mark));
		}
	}

	/**
	 * @param gameType
	 * @param nameTV
	 */
	private void determineGameType(GameType gameType, TextView nameTV) {
		if (gameType == GameType.HOME) {
			nameTV.setText("VS " + nameTV.getText().toString());
		} else {
			nameTV.setText("@ " + nameTV.getText().toString());
		}
	}

}

/**
 * 
 */
package com.football2015schedulematchups.app;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author Scott Auman
 *
 */
public class ButtonSelection extends Button implements OnClickListener {

	public ButtonSelection(Context context, Button button,
			RecordChange recordChange) {
		super(context);
		this.recordChange = recordChange;
		setButton(button);
		setWin(false);
		setLoss(true);
		setNeutral(true);
		setFirst(true);
	}

	private boolean win, loss, neutral, first;
	private Button button;
	private Object previous;
	RecordChange recordChange;

	/**
	 * 
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {

		if (win) {
			getButton().setBackground(
					getContext().getResources().getDrawable(
							R.drawable.loss_button));
			getButton().setText("L");
			setWin(false);
			setLoss(true);
			recordChange.addLoss();
			if (!first) {
				recordChange.removeWin();
			}
		} else if (loss) {
			getButton().setBackground(
					getContext().getResources().getDrawable(
							R.drawable.win_button));
			getButton().setText("W");
			setWin(true);
			setLoss(false);
			recordChange.addWin();
			if (!first) {
				recordChange.removeLoss();
			}
		}

		setFirst(false);

	}

	/**
	 * @return the win
	 */
	public boolean isWin() {
		return win;
	}

	/**
	 * @param win
	 *            the win to set
	 */
	public void setWin(boolean win) {
		this.win = win;
	}

	/**
	 * @return the loss
	 */
	public boolean isLoss() {
		return loss;
	}

	/**
	 * @param loss
	 *            the loss to set
	 */
	public void setLoss(boolean loss) {
		this.loss = loss;
	}

	/**
	 * @return the button
	 */
	public Button getButton() {
		return button;
	}

	/**
	 * @param button
	 *            the button to set
	 */
	public void setButton(Button button) {
		this.button = button;
	}

	/**
	 * @return the neutral
	 */
	public boolean isNeutral() {
		return neutral;
	}

	/**
	 * @param neutral
	 *            the neutral to set
	 */
	public void setNeutral(boolean neutral) {
		this.neutral = neutral;
	}

	/**
	 * @return the previous
	 */
	public Object getPrevious() {
		return previous;
	}

	/**
	 * @param previous
	 *            the previous to set
	 */
	public void setPrevious(Object previous) {
		this.previous = previous;
	}

	/**
	 * @return the first
	 */
	public boolean isFirst() {
		return first;
	}

	/**
	 * @param first
	 *            the first to set
	 */
	public void setFirst(boolean first) {
		this.first = first;
	}

}

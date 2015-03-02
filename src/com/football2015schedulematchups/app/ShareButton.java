package com.football2015schedulematchups.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;

public class ShareButton extends LinearLayout {
	
	boolean showing;

	public ShareButton(Context context) {
		super(context);
		
		setShowing(false);

		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		inflater.inflate(R.layout.share_button, this, true);

	}

	/**
	 * @return the showing
	 */
	public boolean isShowing() {
		return showing;
	}

	/**
	 * @param showing the showing to set
	 */
	public void setShowing(boolean showing) {
		this.showing = showing;
	}

}

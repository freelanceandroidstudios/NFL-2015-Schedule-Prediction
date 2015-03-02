/**
 * 
 */
package com.football2015schedulematchups.app;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * @author Scott Auman
 *
 */
public class TeamTableRow extends TableRow {

	private String name;
	private Buttons buttons;
	RecordChange recordChange;

	/**
	 * @param context
	 */
	public TeamTableRow(Context context, String name, Buttons buttons,
			RecordChange recordChange) {
		super(context);
		setName(name);
		setButtons(buttons);
		this.recordChange = recordChange;
		init();
	}

	private void init() {

		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		inflater.inflate(R.layout.team_tablerow, this, true);

		TextView name = (TextView) this.findViewById(R.id.team_name_textview);
		name.setText(getName());

		Button gameButton = (Button) this.findViewById(R.id.gameButton);
		gameButton.setOnClickListener(new ButtonSelection(getContext(),
				gameButton,recordChange));

		Saved.gameButtonDrawable = gameButton.getBackground();
		Saved.default_button_text = gameButton.getText().toString();

	}

	/**
	 * @param context
	 * @param attrs
	 */
	public TeamTableRow(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
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

}

/**
 * 
 */
package com.football2015schedulematchups.app;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.Button;

/**
 * @author Scott Auman
 *
 */
public class Buttons extends ArrayList<Button> {
	
	Context context;
	Drawable defaultDrawable;
	
	public Buttons(Context context,Drawable drawable) {
		this.context = context;
		defaultDrawable = drawable;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void resetButtons(){
		for(Button button: this){
			button.setBackground((this.defaultDrawable));
		}
	}

}

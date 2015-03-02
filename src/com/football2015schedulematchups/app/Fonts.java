package com.football2015schedulematchups.app;

/*
 * Helper class that contains all external fonts
 * location: /assets/fonts/
 * each method takes in a textview assigns the typeface created and 
 * returns that textview
 */

import android.graphics.Typeface;
import android.widget.TextView;

public class Fonts {

	public Fonts() {

	}

	// sets custom font from assets folder in project hierarchy
	public void setFontHugMeTight(TextView textView) {
		Typeface tf = Typeface.createFromAsset(textView.getContext()
				.getAssets(), "fonts/Hug Me Tight.ttf");

		textView.setTypeface(tf);

	}

	// sets custom font from assets folder in project hierarchy
	public void setPointsFont(TextView textView) {
		Typeface tf = Typeface.createFromAsset(textView.getContext()
				.getAssets(), "fonts/cartoon_font.TTF");

		textView.setTypeface(tf);

	}

	// sets custom font from assets folder in project hierarchy
	public void setFontSnickles(TextView textView) {
		Typeface tf = Typeface.createFromAsset(textView.getContext()
				.getAssets(), "fonts/Snickles.ttf");

		textView.setTypeface(tf);

	}

	// sets custom font from assets folder in project hierarchy
	public void setFontRobotoBlackItalics(TextView textView) {
		Typeface tf = Typeface.createFromAsset(textView.getContext()
				.getAssets(), "fonts/Roboto-BlackItalic.ttf");

		textView.setTypeface(tf);

	}

	// sets custom font from assets folder in project hierarchy
	public void setFontGrandeeCP(TextView textView) {
		Typeface tf = Typeface.createFromAsset(textView.getContext()
				.getAssets(), "fonts/GrandeeCP.otf");

		textView.setTypeface(tf);

	}

}

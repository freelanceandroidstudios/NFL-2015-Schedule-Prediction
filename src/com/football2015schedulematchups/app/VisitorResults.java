package com.football2015schedulematchups.app;

import android.os.Parcel;
import android.os.Parcelable;

public class VisitorResults implements Parcelable {
	private String name;
	private boolean win;

	public VisitorResults() {
		// TODO Auto-generated constructor stub
	}

	protected VisitorResults(Parcel in) {
		name = in.readString();
		win = in.readByte() != 0x00;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeByte((byte) (win ? 0x01 : 0x00));
	}

	@SuppressWarnings("unused")
	public static final Parcelable.Creator<VisitorResults> CREATOR = new Parcelable.Creator<VisitorResults>() {
		@Override
		public VisitorResults createFromParcel(Parcel in) {
			return new VisitorResults(in);
		}

		@Override
		public VisitorResults[] newArray(int size) {
			return new VisitorResults[size];
		}
	};

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the win
	 */
	public boolean isWin() {
		return win;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param win the win to set
	 */
	public void setWin(boolean win) {
		this.win = win;
	}
}

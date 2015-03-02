package com.football2015schedulematchups.app;

import android.os.Parcel;
import android.os.Parcelable;

public class HomeResults implements Parcelable {
	private String name;
	private boolean win;
	
	public HomeResults() {
		// TODO Auto-generated constructor stub
	}

    protected HomeResults(Parcel in) {
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
    public static final Parcelable.Creator<HomeResults> CREATOR = new Parcelable.Creator<HomeResults>() {
        @Override
        public HomeResults createFromParcel(Parcel in) {
            return new HomeResults(in);
        }

        @Override
        public HomeResults[] newArray(int size) {
            return new HomeResults[size];
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

package com.football2015schedulematchups.app;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class HomeTeams implements Parcelable {

	private List<String> teams;
	private String lastRecord;

	/**
	 * @return the teams
	 */
	public List<String> getTeams() {
		return teams;
	}

	/**
	 * @param teams
	 *            the teams to set
	 */
	public void setTeams(List<String> teams) {
		this.teams = teams;
	}

	public HomeTeams() {
		teams = new ArrayList<String>();
	}

	protected HomeTeams(Parcel in) {
		if (in.readByte() == 0x01) {
			teams = new ArrayList<String>();
			in.readList(teams, String.class.getClassLoader());
		} else {
			teams = null;
		}

		lastRecord = in.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		if (teams == null) {
			dest.writeByte((byte) (0x00));
		} else {
			dest.writeByte((byte) (0x01));
			dest.writeList(teams);
		}

		dest.writeString(lastRecord);
	}

	/**
	 * @return the lastRecord
	 */
	public String getLastRecord() {
		return lastRecord;
	}

	/**
	 * @param lastRecord
	 *            the lastRecord to set
	 */
	public void setLastRecord(String lastRecord) {
		this.lastRecord = lastRecord;
	}

	@SuppressWarnings("unused")
	public static final Parcelable.Creator<HomeTeams> CREATOR = new Parcelable.Creator<HomeTeams>() {
		@Override
		public HomeTeams createFromParcel(Parcel in) {
			return new HomeTeams(in);
		}

		@Override
		public HomeTeams[] newArray(int size) {
			return new HomeTeams[size];
		}
	};
}

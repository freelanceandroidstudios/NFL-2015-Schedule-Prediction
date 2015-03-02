package com.football2015schedulematchups.app;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class TeamResults implements Parcelable {

	private String teamName;
	private List<HomeResults> homeResults;
	private List<VisitorResults> visitorResults;
	private String finalRecord;
	private String lastYears;

	public TeamResults() {
		homeResults = new ArrayList<HomeResults>();
		visitorResults = new ArrayList<VisitorResults>();
	}

	protected TeamResults(Parcel in) {
		teamName = in.readString();
		if (in.readByte() == 0x01) {
			homeResults = new ArrayList<HomeResults>();
			in.readList(homeResults, HomeResults.class.getClassLoader());
		} else {
			homeResults = null;
		}
		if (in.readByte() == 0x01) {
			visitorResults = new ArrayList<VisitorResults>();
			in.readList(visitorResults, VisitorResults.class.getClassLoader());
		} else {
			visitorResults = null;
		}
		finalRecord = in.readString();
		lastYears = in.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(teamName);
		if (homeResults == null) {
			dest.writeByte((byte) (0x00));
		} else {
			dest.writeByte((byte) (0x01));
			dest.writeList(homeResults);
		}
		if (visitorResults == null) {
			dest.writeByte((byte) (0x00));
		} else {
			dest.writeByte((byte) (0x01));
			dest.writeList(visitorResults);
		}
		dest.writeString(finalRecord);
		dest.writeString(lastYears);
	}

	@SuppressWarnings("unused")
	public static final Parcelable.Creator<TeamResults> CREATOR = new Parcelable.Creator<TeamResults>() {
		@Override
		public TeamResults createFromParcel(Parcel in) {
			return new TeamResults(in);
		}

		@Override
		public TeamResults[] newArray(int size) {
			return new TeamResults[size];
		}
	};

	/**
	 * @return the teamName
	 */
	public String getTeamName() {
		return teamName;
	}

	/**
	 * @return the homeResults
	 */
	public List<HomeResults> getHomeResults() {
		return homeResults;
	}

	/**
	 * @return the visitorResults
	 */
	public List<VisitorResults> getVisitorResults() {
		return visitorResults;
	}

	/**
	 * @return the finalRecord
	 */
	public String getFinalRecord() {
		return finalRecord;
	}

	/**
	 * @param teamName
	 *            the teamName to set
	 */
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	/**
	 * @param homeResults
	 *            the homeResults to set
	 */
	public void setHomeResults(List<HomeResults> homeResults) {
		this.homeResults = homeResults;
	}

	/**
	 * @param visitorResults
	 *            the visitorResults to set
	 */
	public void setVisitorResults(List<VisitorResults> visitorResults) {
		this.visitorResults = visitorResults;
	}

	/**
	 * @param finalRecord
	 *            the finalRecord to set
	 */
	public void setFinalRecord(String finalRecord) {
		this.finalRecord = finalRecord;
	}

	/**
	 * @return the lastYears
	 */
	public String getLastYears() {
		return lastYears;
	}

	/**
	 * @param lastYears
	 *            the lastYears to set
	 */
	public void setLastYears(String lastYears) {
		this.lastYears = lastYears;
	}
}

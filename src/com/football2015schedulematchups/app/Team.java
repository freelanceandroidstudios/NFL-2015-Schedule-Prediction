package com.football2015schedulematchups.app;

import android.os.Parcel;
import android.os.Parcelable;

public class Team implements Parcelable {

	private String name;
	private String conference;
	private String division;
	private HomeTeams homeTeams;
	private VisitorTeams visitorTeams;
	private String lastYears;

	protected Team(Parcel in) {
		name = in.readString();
		conference = in.readString();
		division = in.readString();
		homeTeams = (HomeTeams) in.readValue(HomeTeams.class.getClassLoader());
		visitorTeams = (VisitorTeams) in.readValue(VisitorTeams.class
				.getClassLoader());
		lastYears= in.readString();
	}

	public Team() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeString(conference);
		dest.writeString(division);
		dest.writeValue(homeTeams);
		dest.writeValue(visitorTeams);
		dest.writeString(lastYears);
	}

	@SuppressWarnings("unused")
	public static final Parcelable.Creator<Team> CREATOR = new Parcelable.Creator<Team>() {
		@Override
		public Team createFromParcel(Parcel in) {
			return new Team(in);
		}

		@Override
		public Team[] newArray(int size) {
			return new Team[size];
		}
	};

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the conference
	 */
	public String getConference() {
		return conference;
	}

	/**
	 * @return the division
	 */
	public String getDivision() {
		return division;
	}

	/**
	 * @return the homeTeams
	 */
	public HomeTeams getHomeTeams() {
		return homeTeams;
	}

	/**
	 * @return the visitorTeams
	 */
	public VisitorTeams getVisitorTeams() {
		return visitorTeams;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param conference
	 *            the conference to set
	 */
	public void setConference(String conference) {
		this.conference = conference;
	}

	/**
	 * @param division
	 *            the division to set
	 */
	public void setDivision(String division) {
		this.division = division;
	}

	/**
	 * @param homeTeams
	 *            the homeTeams to set
	 */
	public void setHomeTeams(HomeTeams homeTeams) {
		this.homeTeams = homeTeams;
	}

	/**
	 * @param visitorTeams
	 *            the visitorTeams to set
	 */
	public void setVisitorTeams(VisitorTeams visitorTeams) {
		this.visitorTeams = visitorTeams;
	}

	/**
	 * @return the lastYears
	 */
	public String getLastYears() {
		return lastYears;
	}

	/**
	 * @param lastYears the lastYears to set
	 */
	public void setLastYears(String lastYears) {
		this.lastYears = lastYears;
	}
}

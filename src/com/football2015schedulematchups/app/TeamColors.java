package com.football2015schedulematchups.app;

import java.util.ArrayList;
import java.util.List;

public class TeamColors {

	private List<Integer> teamcolors = new ArrayList<Integer>();

	public TeamColors() {

		setUpColors();

	}

	private void setUpColors() {

		// add all colors from string XML
		teamcolors.add(R.color.Cards);
		teamcolors.add(R.color.Falcons);
		teamcolors.add(R.color.Ravens);
		teamcolors.add(R.color.Bills);
		teamcolors.add(R.color.Panthers);
		teamcolors.add(R.color.Bears);
		teamcolors.add(R.color.Bengals);
		teamcolors.add(R.color.Browns);
		teamcolors.add(R.color.Cowboys);
		teamcolors.add(R.color.Broncos);
		teamcolors.add(R.color.Lions);
		teamcolors.add(R.color.Packers);
		teamcolors.add(R.color.Texans);
		teamcolors.add(R.color.Colts);
		teamcolors.add(R.color.Jags);
		teamcolors.add(R.color.Chiefs);
		teamcolors.add(R.color.Dolphins);
		teamcolors.add(R.color.Vikings);
		teamcolors.add(R.color.Patriots);
		teamcolors.add(R.color.Saints);
		teamcolors.add(R.color.Giants);
		teamcolors.add(R.color.Jets);
		teamcolors.add(R.color.Raiders);
		teamcolors.add(R.color.Eagles);
		teamcolors.add(R.color.Steelers);
		teamcolors.add(R.color.Rams);
		teamcolors.add(R.color.Chargers);
		teamcolors.add(R.color.fourniners);
		teamcolors.add(R.color.Seahawks);
		teamcolors.add(R.color.Buccaneers);
		teamcolors.add(R.color.Titans);
		teamcolors.add(R.color.RedSkins);
	}

	/**
	 * @return the teamcolors
	 */
	public List<Integer> getTeamcolors() {
		return teamcolors;
	}

	/**
	 * @param teamcolors
	 *            the teamcolors to set
	 */
	public void setTeamcolors(List<Integer> teamcolors) {
		this.teamcolors = teamcolors;
	}

}

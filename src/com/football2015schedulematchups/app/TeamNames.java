package com.football2015schedulematchups.app;

import java.util.ArrayList;
import java.util.List;

public class TeamNames {

	private List<String> teamnames = new ArrayList<String>();

	public List<String> getTeamnames() {
		return teamnames;
	}

	public String getTeamnames(int i) {
		return teamnames.get(i);
	}

	public TeamNames() {

		addElements();

	}

	private void addElements() {

		teamnames.add("Arizona Cardinals");
		teamnames.add("Atlanta Falcons");
		teamnames.add("Baltimore Ravens");
		teamnames.add("Buffalo Bills");
		teamnames.add("Carolina Panthers");
		teamnames.add("Chicago Bears");
		teamnames.add("Cincinnati Bengals");
		teamnames.add("Cleveland Browns ");
		teamnames.add("Dallas Cowboys");
		teamnames.add("Denver Broncos");
		teamnames.add("Detroit Lions");
		teamnames.add("Green Bay Packers");
		teamnames.add("Houston Texans");
		teamnames.add("Indianapolis Colts");
		teamnames.add("Jacksonville Jaguars ");
		teamnames.add("Kansas City Chiefs");
		teamnames.add("Miami Dolphins");
		teamnames.add("Minnesota Vikings");
		teamnames.add("New England Patriots");
		teamnames.add("New Orleans Saints");
		teamnames.add("New York Giants");
		teamnames.add("New York Jets");
		teamnames.add("Oakland Raiders");
		teamnames.add("Philadelphia Eagles");
		teamnames.add("Pittsburgh Steelers");
		teamnames.add("San Diego Chargers");
		teamnames.add("San Francisco 49ers");
		teamnames.add("Seattle Seahawks");
		teamnames.add("St. Louis Rams");
		teamnames.add("Tampa Bay Buccaneers");
		teamnames.add("Tennessee Titans");
		teamnames.add("Washington Redskins");

	}

}

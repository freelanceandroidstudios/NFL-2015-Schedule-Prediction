package com.football2015schedulematchups.app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Context;
import android.os.AsyncTask;

public class ParseTeamInformationTask extends AsyncTask<Integer, Void, Teams> {

	TaskStatus taskStatus;
	private Context context;
	private String filename;

	public ParseTeamInformationTask(TaskStatus taskStatus, Context context) {
		this.taskStatus = taskStatus;
		setContext(context);
		setFilename("nflteamfile.csv");
	}

	@Override
	protected Teams doInBackground(Integer... params) {

		Teams teams = new Teams();

		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {
			br = new BufferedReader(new BufferedReader(new InputStreamReader(
					getContext().getAssets().open(getFilename()))));

			while ((line = br.readLine()) != null) {

				Team team = new Team();

				// use comma as separator
				String[] splitters = line.split(cvsSplitBy);

				// set team variables
				team.setName(splitters[0].trim());
				team.setConference(splitters[1].trim());
				team.setDivision(splitters[2].trim());

				// get home teams
				HomeTeams homeTeams = new HomeTeams();
				for (int i = 3; i < 11; i++) {
					homeTeams.getTeams().add(splitters[i].trim());
				}

				team.setHomeTeams(homeTeams);

				// get visitor teams
				VisitorTeams visitorTeams = new VisitorTeams();
				for (int i = 11; i < 19; i++) {
					visitorTeams.getTeams().add(splitters[i].trim());
				}

				team.setVisitorTeams(visitorTeams);
				
				team.setLastYears(splitters[19]);

				teams.add(team); // add team object to list
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("Done");

		return teams;
	}

	@Override
	protected void onPostExecute(Teams result) {
		super.onPostExecute(result);
		taskStatus.onComplete(result);
	}

	/**
	 * @return the context
	 */
	public Context getContext() {
		return context;
	}

	/**
	 * @param context
	 *            the context to set
	 */
	public void setContext(Context context) {
		this.context = context;
	}

	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * @param filename
	 *            the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

}

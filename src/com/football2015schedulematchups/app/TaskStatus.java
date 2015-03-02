package com.football2015schedulematchups.app;

public interface TaskStatus {
	
	void onComplete(Teams result);
	void onCancelled();

}

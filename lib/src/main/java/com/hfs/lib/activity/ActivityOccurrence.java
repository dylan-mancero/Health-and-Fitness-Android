package com.hfs.lib.activity;

import androidx.room.Ignore;

public abstract class ActivityOccurrence implements ReportableStrategy {

	@Ignore
	private FinishedActivity finishedActivity;

	protected ActivityOccurrence(){
	}

	protected void setFinishedActivity(FinishedActivity finishedActivity){
		this.finishedActivity = finishedActivity;
	}

	/**
	 * 
	 * @param finishedActivity
	 */
	public ActivityOccurrence(FinishedActivity finishedActivity) {
		this.finishedActivity = finishedActivity;
	}

	public Activity getActivity() {
		return this.finishedActivity.getActivity();
	}

}
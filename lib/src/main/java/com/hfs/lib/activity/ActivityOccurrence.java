package com.hfs.lib.activity;

public abstract class ActivityOccurrence implements ReportableStrategy {

	private final FinishedActivity finishedActivity;

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
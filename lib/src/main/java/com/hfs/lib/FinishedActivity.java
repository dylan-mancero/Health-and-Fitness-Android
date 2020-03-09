package com.hfs.lib;

import java.util.Date;

// TODO - KJ: Check if this class should declare a getActivityReport()
public class FinishedActivity implements ReportableStrategy {

	private Date end;

	/**
	 * 
	 * @param activity
	 */
	public FinishedActivity(UnfinishedActivity activity) {
		// TODO - implement FinishedActivity.FinishedActivity
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param activity
	 * @param endTime
	 */
	public FinishedActivity(UnfinishedActivity activity, Date endTime) {
		// TODO - implement FinishedActivity.FinishedActivity
		throw new UnsupportedOperationException();
	}

	public Date getStart() {
		// TODO - implement FinishedActivity.getStart
		throw new UnsupportedOperationException();
	}

	public Date getEnd() {
		return this.end;
	}

	public Activity getActivity() {
		// TODO - implement FinishedActivity.getActivity
		throw new UnsupportedOperationException();
	}

	public ActivityReport getActivityReport() {
		// TODO - implement FinishedActivity.getActivityReport
		throw new UnsupportedOperationException();
	}

}
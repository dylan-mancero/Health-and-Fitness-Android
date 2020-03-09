package com.hfs.lib.activity;

import java.time.OffsetDateTime;

public class UnfinishedActivity {

	private final OffsetDateTime start;
	private final Activity activity;

	/**
	 * 
	 * @param activity
	 */
	public UnfinishedActivity(Activity activity) {
	    this(activity, OffsetDateTime.now());
	}

	/**
	 * 
	 * @param activity
	 * @param start
	 */
	public UnfinishedActivity(Activity activity, OffsetDateTime start) {
		this.activity = activity;
		this.start = start;
	}

	public FinishedActivity end() {
		return new FinishedActivity(this);
	}

	public OffsetDateTime getStart() {
	    return this.start;
	}

	public Activity getActivity() {
	    return this.activity;
	}

}
package com.hfs.lib.activity;

import java.time.OffsetDateTime;
import java.util.Objects;

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

	public FinishedActivity end(int sets, int reps) {
		return new FinishedActivity(this, sets, reps);
	}

	public OffsetDateTime getStart() {
	    return this.start;
	}

	public Activity getActivity() {
	    return this.activity;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UnfinishedActivity that = (UnfinishedActivity) o;
		return start.equals(that.start) &&
				activity.equals(that.activity);
	}

	@Override
	public int hashCode() {
		return Objects.hash(start, activity);
	}
}
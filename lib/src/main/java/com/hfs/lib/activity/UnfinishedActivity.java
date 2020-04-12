package com.hfs.lib.activity;

import androidx.room.Ignore;
import androidx.room.TypeConverters;

import com.hfs.lib.typeconverters.OffsetDateTimeConverter;

import java.time.OffsetDateTime;
import java.util.Objects;

public class UnfinishedActivity {

	@TypeConverters(OffsetDateTimeConverter.class)
	private final OffsetDateTime start;

	public long activityId;
	@Ignore
	private Activity activity;

	/**
	 * Only for Android Room.
	 * @param start
	 */
	@Deprecated
	public UnfinishedActivity(OffsetDateTime start) {
		this.start = start;
	}

	/**
	 * Only for Android Room.
	 * @param activity
	 */
	@Deprecated
	public void setActivity(Activity activity){
		this.activity = activity;
	}

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
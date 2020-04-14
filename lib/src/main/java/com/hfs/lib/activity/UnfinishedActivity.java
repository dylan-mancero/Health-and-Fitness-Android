package com.hfs.lib.activity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.hfs.lib.StandardProfile;
import com.hfs.lib.dao.ActivitiesDao;
import com.hfs.lib.repo.Activities;
import com.hfs.lib.typeconverters.OffsetDateTimeConverter;

import java.time.OffsetDateTime;
import java.util.Objects;

import static androidx.room.ForeignKey.NO_ACTION;
@Entity(foreignKeys = {
		@ForeignKey(
				entity = StandardProfile.class,
				parentColumns = "standardProfileId",
				childColumns = "userId",
				onDelete = NO_ACTION),
		@ForeignKey(
				entity = Activity.class,
				parentColumns = "name",
				childColumns = "activityName",
				onDelete = NO_ACTION
		)
})
public class UnfinishedActivity {

	@PrimaryKey(autoGenerate = true)
	public long id;

	@TypeConverters(OffsetDateTimeConverter.class)
	private final OffsetDateTime start;

	// Foreign keys
	private final long userId;
	private final String activityName;
	@Ignore
	private Activity activity;

	/**
	 * Only For Room.
	 */
	@Deprecated
	public UnfinishedActivity(String activityName, OffsetDateTime start, long userId){
		this.activityName = activityName;
		this.start = start;
		this.userId = userId;
	}

	/**
	 * Only for Room.
	 * @param activities
	 * @return
	 */
	@Deprecated
	public UnfinishedActivity init(Activities activities){
		this.activity = activities.getActivity(activityName);
		return this;
	}

	/**
	 * Only for Room.
	 * @return
	 */
	@Deprecated
	public long getUserId() {
		return userId;
	}

	public UnfinishedActivity(Activity activity, StandardProfile profile) {
	    this(activity, OffsetDateTime.now(), profile);
	}

	public UnfinishedActivity(Activity activity, OffsetDateTime start, StandardProfile profile) {
		this.activity = activity;
		this.activityName = activity.getName();
		this.start = start;
		this.userId = profile.getStandardProfileId();
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

	public String getActivityName() {
		return this.activityName;
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
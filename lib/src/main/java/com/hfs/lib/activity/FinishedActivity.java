package com.hfs.lib.activity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.hfs.lib.dao.ActivitiesDao;
import com.hfs.lib.repo.Activities;
import com.hfs.lib.typeconverters.DurationConverter;

import java.time.DateTimeException;
import java.time.Duration;
import java.time.OffsetDateTime;

import static androidx.room.ForeignKey.CASCADE;
@Entity(foreignKeys = {
	@ForeignKey(
			entity = UnfinishedActivity.class,
			parentColumns = "id",
			childColumns = "unfinishedActivityId",
			onDelete = CASCADE
	)
})
public class FinishedActivity implements ReportableStrategy {

	@PrimaryKey(autoGenerate = true)
	public long finishedActivityId;

	@TypeConverters({DurationConverter.class})
	private final Duration duration;
	@Ignore
	private UnfinishedActivity unfinishedActivity;
	public long unfinishedActivityId;
	@Ignore
	private ActivityOccurrence activityOccurrence;

	@Deprecated
	public FinishedActivity(Duration duration, long unfinishedActivityId){
		this.duration = duration;
		this.unfinishedActivityId = unfinishedActivityId;
	}

	@Deprecated
	public FinishedActivity init(ActivitiesDao activitiesDao){
		this.unfinishedActivity = activitiesDao.getUnfinishedActivity(this.finishedActivityId);
		this.unfinishedActivity.init(new Activities(activitiesDao));
		return this;
	}

	@Deprecated
	public long getUnfinishedActivityId() {
		return unfinishedActivityId;
	}

	@Deprecated
	public UnfinishedActivity getUnfinishedActivity() {
		return unfinishedActivity;
	}

	/**
	 * 
	 * @param activity
	 */
	public FinishedActivity(UnfinishedActivity activity) {
	    this(activity, OffsetDateTime.now());
	}

	public FinishedActivity(UnfinishedActivity activity, int sets, int reps) {
		this(activity, OffsetDateTime.now(), sets, reps);
	}
	/**
	 * 
	 * @param unfinishedActivity
	 * @param endTime
	 */
	public FinishedActivity(UnfinishedActivity unfinishedActivity, OffsetDateTime endTime) throws DateTimeException{
		this.unfinishedActivityId = unfinishedActivity.id;
		final OffsetDateTime start = unfinishedActivity.getStart();
		if(start.isAfter(endTime)){
			throw new DateTimeException("Provided end time is invalid.\nStart time: " + start.toString() + "\tEnd time: " + endTime.toString());
		}
		this.duration = Duration.between(start, endTime);
		this.unfinishedActivity = unfinishedActivity;

		if(!(unfinishedActivity.getActivity() instanceof Sport)){
			throw new IllegalArgumentException(unfinishedActivity.getActivity().toString() + " not of type Sport.\nCannot create FinishedActivity.");
		}
		this.activityOccurrence = new SportOccurrence(this);

	}

	public FinishedActivity(UnfinishedActivity unfinishedActivity, OffsetDateTime endTime, int sets, int reps) throws DateTimeException{
		this.unfinishedActivityId = unfinishedActivity.id;
		final OffsetDateTime start = unfinishedActivity.getStart();
		if(endTime.isAfter(start)){
			throw new DateTimeException("Provided end time is invalid.\nStart time: " + start.toString() + "\tEnd time: " + endTime.toString());
		}
		this.duration = Duration.between(start, endTime);
		this.unfinishedActivity = unfinishedActivity;

		if(!(unfinishedActivity.getActivity() instanceof Exercise)){
			throw new IllegalArgumentException(unfinishedActivity.getActivity().toString() + " not of type Exercise.\nCannot create FinishedActivity.");
		}
		this.activityOccurrence = new ExerciseOccurrence(this, sets, reps);
	}

	public OffsetDateTime getStart() {
		return this.unfinishedActivity.getStart();
	}

	public OffsetDateTime getEnd() {
		return this.getStart().plus(duration);
	}

	public Duration getDuration(){
		return this.duration;
	}

	public Activity getActivity() {
	    return this.unfinishedActivity.getActivity();
	}

	public ActivityReport getActivityReport() {
	    return this.activityOccurrence.getActivityReport();
	}

}
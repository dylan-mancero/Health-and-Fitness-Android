package com.hfs.lib;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.hfs.lib.activity.Activity;
import com.hfs.lib.activity.Exercise;
import com.hfs.lib.activity.ExerciseOccurrence;
import com.hfs.lib.activity.FinishedActivity;
import com.hfs.lib.activity.Sport;
import com.hfs.lib.activity.SportOccurrence;
import com.hfs.lib.activity.UnfinishedActivity;
import com.hfs.lib.dao.ActivitiesDao;
import com.hfs.lib.dao.StandardProfileDao;
import com.hfs.lib.repo.Activities;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;

@Database(entities = {Activity.class, Exercise.class, Sport.class, StandardProfile.class, UnfinishedActivity.class, FinishedActivity.class, SportOccurrence.class, ExerciseOccurrence.class}, version = 1)
public abstract class HFSDatabase extends RoomDatabase {
    private static HFSDatabase instance;

    public abstract ActivitiesDao activitiesDao();

    public abstract StandardProfileDao standardProfileDao();

    private void prePopulateRepos(){
        final Sport[] dummySports = new Sport[]{
                new Sport("Running", 10),
                new Sport("Swimming", 20),
                new Sport("Cycling", 30),
                new Sport("Walking", 40)
        };
        activitiesDao().insert(dummySports);

        final Exercise[] dummyExercises = new Exercise[]{
                new Exercise("Push-ups"),
                new Exercise("Sit-ups"),
                new Exercise("Pull-ups"),
                new Exercise("Plank")
        };
        activitiesDao().insert(dummyExercises);
    }

    public void prePopulate(){
        // Necessary as primary keys need to be generated.
        prePopulateRepos();

        // TODO: Consumables

        final Activities activities = new Activities(activitiesDao());

        final StandardProfile profile = new StandardProfile(1,
                StandardProfile.SHARING,
                180,
                70,
                Goal.GAIN_MUSCLE_MASS
        );

        final long profileId = this.standardProfileDao().insertStandardProfile(profile);
        profile.setStandardProfileId(profileId);

        final Fitness fitness = new Fitness(this.activitiesDao(), profile);
        profile.setFitness(fitness);
        final Schedule schedule = new Schedule(this.activitiesDao(), profile);
        profile.setSchedule(schedule);

        final Random rand = new Random();

        // Add dummy FinishedActivities.
        profile.addPastActivitySession(
                    activities.getActivity("Running"),
                    OffsetDateTime.of(2019,
                            rand.nextInt(12) + 1,
                            rand.nextInt(20) + 1,
                            rand.nextInt(24),
                            rand.nextInt(60),
                            0, 0, ZoneOffset.UTC),
                    OffsetDateTime.now());

        // Add dummy UnfinishedActivities.
        activities.getActivities().parallelStream().map(Activity::getName).forEach(activityName -> {
            profile.addFutureActivitySession(
                    activities.getActivity(activityName),
                    OffsetDateTime.of(2019,
                            rand.nextInt(12) + 1,
                            rand.nextInt(20) + 1,
                            rand.nextInt(24),
                            rand.nextInt(60),
                            0, 0, ZoneOffset.UTC));
        });
    }

    public static synchronized HFSDatabase getInstance(Context context) {
        // TODO: Run db in a background thread - remove allowMainThredQueries()
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    HFSDatabase.class, "hfs")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            Executors.newSingleThreadExecutor().execute(() -> getInstance(context).prePopulate());
                        }
                    }).build();
        }


        return instance;
    }

    public void dump(){
        this.activitiesDao().loadUnfinishedActivities(1).forEach(uf ->{
            Log.d("UF: ", uf.getActivityName());
        });

        this.activitiesDao().loadFinishedActivities(1).forEach(ff ->{
            Log.d("FF: ", ff.getDuration().toString());
        });

        this.activitiesDao()._load_activities().forEach(act -> {
            Log.d("ACT: ", act.getName());
        });

        Activities activities = new Activities(this.activitiesDao());
        activities.getActivities().forEach(a -> { Log.d("EX/SPT: ", a.getName());});

    }

    public StandardProfile getStandardProfile(Application app, long id){
        final StandardProfile profile = standardProfileDao().getStandardProfile(id);
        profile.setFitness(new Fitness(app, profile));
        return standardProfileDao().getStandardProfile(id);
    }

}

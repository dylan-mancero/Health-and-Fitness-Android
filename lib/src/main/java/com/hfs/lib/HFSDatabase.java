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
import com.hfs.lib.dao.ConsumablesDao;
import com.hfs.lib.dao.StandardProfileDao;
import com.hfs.lib.nutrition.Allergy;
import com.hfs.lib.nutrition.Consumable;
import com.hfs.lib.nutrition.ConsumableOccurrence;
import com.hfs.lib.nutrition.ConsumableType;
import com.hfs.lib.nutrition.Nutrition;
import com.hfs.lib.repo.Activities;
import com.hfs.lib.repo.Consumables;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Database(entities = {
        Activity.class,
        Exercise.class,
        Sport.class,
        StandardProfile.class,
        UnfinishedActivity.class,
        FinishedActivity.class,
        SportOccurrence.class,
        ExerciseOccurrence.class,
        Consumable.class,
        ConsumableOccurrence.class
}, version = 1)
public abstract class HFSDatabase extends RoomDatabase {
    private static HFSDatabase instance;

    public abstract ActivitiesDao activitiesDao();

    public abstract ConsumablesDao consumablesDao();

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

        final Consumable[] dummyConsumables = new Consumable[]{
                new Consumable("Pizza", 100, 100, 100, 100, ConsumableType.FOOD),
                new Consumable("Pasta", 101, 101,101, 101, ConsumableType.FOOD),
                new Consumable("Baked Beans", 102, 102, 102, 102, ConsumableType.FOOD),
                new Consumable("Eggs", 103, 103, 103, 103, ConsumableType.FOOD),
                new Consumable("Milk", 104, 104, 104, 104, ConsumableType.DRINK),
                new Consumable("Orange Juice", 105, 105, 105, 105, ConsumableType.DRINK)
        };
        consumablesDao().insert(dummyConsumables);
    }

    public void prePopulate(){
        // Necessary as primary keys need to be generated.
        prePopulateRepos();

        // TODO: Consumables

        final Activities activities = new Activities(activitiesDao());
        final Consumables consumables = new Consumables(consumablesDao());

        final StandardProfile profile = new StandardProfile(1,
                StandardProfile.SHARING,
                180,
                70,
                Goal.GAIN_MUSCLE_MASS
        );

        this.standardProfileDao().insertStandardProfile(profile);


        final Fitness fitness = new Fitness(this.activitiesDao(), profile);
        profile.setFitness(fitness);
        final Schedule schedule = new Schedule(this.activitiesDao(), profile);
        profile.setSchedule(schedule);
        final Nutrition nutrition = new Nutrition(this.consumablesDao(), profile);
        profile.setNutrition(nutrition);

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

        profile.consume(consumables.getConsumable("Pizza"), 101);
        Log.d("DB ---------------", "Filled");
    }

    public static synchronized HFSDatabase getInstance(Context context) {
        // TODO: Run db in a background thread - remove allowMainThredQueries()
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    HFSDatabase.class, "hfs")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
            /*
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            Executors.newSingleThreadExecutor().execute(() -> getInstance(context).prePopulate());
                        }
                   }).build();
             */
            instance.prePopulate();
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

        this.consumablesDao().loadConsumables().forEach(consumable-> {
            Log.d("CON: ", consumable.getName());
        });

        this.consumablesDao().loadConsumableOccurrences(1).forEach( consumed -> {
            Log.d("CONSUMED: ", consumed.getConsumableName() + " " + consumed.getAmount() + " " + consumed.getDate().toString());
        });

        Activities activities = new Activities(this.activitiesDao());
        activities.getActivities().forEach(a -> { Log.d("EX/SPT: ", a.getName());});

    }

    public StandardProfile getStandardProfile(Application app, long id){
        // TODO: Add Schedule and Nutrition.
        final StandardProfile profile = standardProfileDao().getStandardProfile(id);

        profile.setFitness(new Fitness(app, profile));
        profile.setNutrition(Nutrition.getInstance(app, profile));

        return profile;
    }

}

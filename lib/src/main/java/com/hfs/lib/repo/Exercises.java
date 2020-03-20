package com.hfs.lib.repo;

import com.hfs.lib.activity.Exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Exercises {

	private static Exercises instance;
	private List<Exercise> exercises;

	private Exercises() {
		// TODO - Change dummies to appropriate values from db.
		// TODO - Possibly use a Set as no duplicate Exercise should be allowed.
		final Exercise[] dummyExercises = new Exercise[]{
				new Exercise("Push-ups"),
				new Exercise("Sit-ups"),
				new Exercise("Pull-ups"),
				new Exercise("Plank")
		};

		this.exercises = new ArrayList<>(Arrays.asList(dummyExercises));
	}

	public static Exercises getInstance() {
		if(instance == null){
			instance = new Exercises();
		}

		return instance;
	}

	/**
	 * 
	 * @param name
	 */
	public Exercise getExercise(String name) {
		return this.exercises.parallelStream()
				.filter(exercise -> exercise.getName().equals(name))
				.findFirst().get();
	}

	public List<Exercise> getExercises() {
		return new ArrayList<>(this.exercises);
	}
}
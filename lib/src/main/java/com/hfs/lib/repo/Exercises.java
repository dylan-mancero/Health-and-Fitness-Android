package com.hfs.lib.repo;

import com.hfs.lib.activity.Exercise;

public class Exercises {

	private Exercises instance;

	private Exercises() {
		// TODO - implement Exercises.Exercises
		throw new UnsupportedOperationException();
	}

	private Exercises getInstance() {
		if(this.instance == null){
			instance = new Exercises();
		}

		return this.instance;
	}

	/**
	 * 
	 * @param name
	 */
	public Exercise getExercise(String name) {
		// TODO - implement Exercises.getExercise
		throw new UnsupportedOperationException();
	}

}
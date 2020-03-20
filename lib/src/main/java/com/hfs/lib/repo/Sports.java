package com.hfs.lib.repo;

import com.hfs.lib.activity.Sport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Sports {

	private static Sports instance;
	private List<Sport> sports;

	private Sports() {
		// TODO - Change dummies to appropriate values from db.
		// TODO - Possibly use a Set as no duplicate Sport should be allowed.
		final Sport[] dummySports = new Sport[]{
				new Sport("Running", 1),
				new Sport("Swimming", 2),
				new Sport("Cycling", 3),
				new Sport("Walking", 4)
		};
		this.sports = new ArrayList<>(Arrays.asList(dummySports));
	}

	public static Sports getInstance() {
		if(instance == null){
			instance = new Sports();
		}

		return instance;
	}

	/**
	 * 
	 * @param name
	 */
	public Sport getSport(String name) {
		 return this.sports.parallelStream()
				.filter(sport -> sport.getName().equals(name))
				.findFirst().get();
	}

	public List<Sport> getSports() {
		return new ArrayList<>(this.sports);
	}
}
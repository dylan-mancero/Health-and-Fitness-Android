package com.hfs.lib.repo;

import com.hfs.lib.activity.Sport;

public class Sports {

	private Sports instance;

	private Sports() {
		// TODO - implement Sports.Sports
		throw new UnsupportedOperationException();
	}

	public Sports getInstance() {
		if(this.instance == null){
			this.instance = new Sports();
		}

		return this.instance;
	}

	/**
	 * 
	 * @param name
	 */
	public Sport getSport(String name) {
		// TODO - implement Sports.getSport
		throw new UnsupportedOperationException();
	}

}
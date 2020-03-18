package com.hfs.lib.activity;

public abstract class Activity {

	private final String name;

	public Activity(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

}
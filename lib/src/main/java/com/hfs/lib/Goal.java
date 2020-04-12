package com.hfs.lib;

public enum Goal {
	WEIGHT_LOSS		(0),
	GAIN_WEIGHT		(1),
	GAIN_MUSCLE_MASS(2),
	REDUCE_BODY_FAT	(3);

	private int id;

	Goal(int id){
		this.id = id;
	}

	public int getId(){
		return this.id;
	}
}

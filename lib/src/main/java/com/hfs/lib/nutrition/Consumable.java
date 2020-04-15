package com.hfs.lib.nutrition;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.hfs.lib.typeconverters.ConsumableTypeConverter;

@Entity
public class Consumable {

	@PrimaryKey
	@NonNull private String name;
	private double proteins;
	private double carbs;
	private double calories;
	private double sugar;
	@TypeConverters(ConsumableTypeConverter.class)
	private ConsumableType type;

	// TODO: Allergies
	//private Allergy[] allergies;

	/**
	 *
	 * @param name
	 * @param proteins
	 * @param carbs
	 * @param calories
	 * @param sugar
	 * @param type
	 */

	public Consumable(@NonNull String name, double proteins, double carbs, double calories, double sugar,ConsumableType type) {
		this.name = name;
		this.proteins = proteins;
		this.carbs = carbs;
		this.calories = calories;
		this.sugar = sugar;
		this.type = type;
		//this.allergies = allergies;
	}

	public String getName() {
		return name;
	}

	public double getProteins() {
	    return this.proteins;
	}

	public double getCarbs() {
		return this.carbs;
	}

	public double getCalories() {
		return this.calories;
	}

	public double getSugar() {
		return this.sugar;
	}

	public ConsumableType getType() {
		return type;
	}

	/*
	public Allergy[] getAllergies() {
		return this.allergies;
	}
	*/

}
package com.hfs.lib.nutrition;

public class Consumable {

	private String name;
	private double proteins;
	private double carbs;
	private double calories;
	private double sugar;
	private ConsumableType type;
	private Allergy[] allergies;

	/**
	 *
	 * @param name
	 * @param proteins
	 * @param carbs
	 * @param calories
	 * @param sugar
	 * @param type
	 * @param allergies
	 */

	public Consumable(String name, double proteins, double carbs, double calories, double sugar,ConsumableType type, Allergy[] allergies) {
		this.name = name;
		this.proteins = proteins;
		this.carbs = carbs;
		this.calories = calories;
		this.sugar = sugar;
		this.type = type;
		this.allergies = allergies;
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

	public Allergy[] getAllergies() {
		return this.allergies;
	}

}
package com.hfs.lib.nutrition;

public class Consumable {

	private String name;
	private double protein;
	private double carbs;
	private double calories;
	private double sugar;

	/**
	 * 
	 * @param protein
	 * @param carbs
	 * @param calories
	 * @param sugar
	 * @param type
	 * @param allergies
	 */
	public Consumable(double protein, double carbs, double calories, double sugar, ConsumableType type, Allergy[] allergies) {
		// TODO - implement Consumable.Consumable
		throw new UnsupportedOperationException();
	}

	public Allergy[] getAllergies() {
		// TODO - implement Consumable.getAllergies
		throw new UnsupportedOperationException();
	}

	public double getProteins() {
		// TODO - implement Consumable.getProteins
		throw new UnsupportedOperationException();
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

}
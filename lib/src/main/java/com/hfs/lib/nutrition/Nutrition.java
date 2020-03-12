package com.hfs.lib.nutrition;

import java.util.Arrays;
import java.util.List;

public class Nutrition {

	private double protein;
	private double carbs;
	private double calories;
	private double sugar;

	private List<ConsumableOccurance> consumables;
	private final Allergy[] allergies;

	public Nutrition(double protein, double carbs, double calories, double sugar, Allergy[] allergies) {
		this.protein = protein;
		this.carbs = carbs;
		this.calories = calories;
		this.sugar = sugar;
		this.allergies = allergies;
	}

	/**
	 * 
	 * @param consumable
	 * @param amount
	 */
	public void addConsumable(Consumable consumable, double amount) throws IllegalArgumentException{
		if(amount <= 0){
			throw new IllegalArgumentException(consumable.getName() + " consumed cannot be: " + amount + " g\nExpected more than 0g.");
		}

		final List<Allergy> userAllergies = Arrays.asList(this.allergies);
		final boolean itemContainsAllergies = Arrays.stream(consumable.getAllergies())
				.anyMatch(userAllergies::contains);

		if(itemContainsAllergies){
			throw new IllegalArgumentException(consumable.getName() + " contains one or many allergen to which the user is allergic.");
		}

		final ConsumableOccurance consumedItem = new ConsumableOccurance(consumable, amount);
        this.consumables.add(consumedItem);
        this.update(consumedItem);
	}

	private void update(ConsumableOccurance consumedItem) {
		final double amountConsumed = consumedItem.getAmount();
		final Consumable itemConsumed = consumedItem.getConsumable();

		this.protein = itemConsumed.getProteins() * amountConsumed;
		this.carbs = itemConsumed.getCarbs() * amountConsumed;
		this.calories = itemConsumed.getCalories() * amountConsumed;
		this.sugar = itemConsumed.getSugar() * amountConsumed;
	}

	public List<ConsumableOccurance> getConsumables() {
		return consumables;
	}

	public double getProtein() {
		return this.protein;
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
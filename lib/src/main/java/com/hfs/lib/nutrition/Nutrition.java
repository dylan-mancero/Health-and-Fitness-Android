package com.hfs.lib.nutrition;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hfs.lib.HFSDatabase;
import com.hfs.lib.StandardProfile;
import com.hfs.lib.dao.ConsumablesDao;
import com.hfs.lib.repo.Consumables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Nutrition {

	private static Nutrition instance;

	private final ConsumablesDao consumablesDao;

	private double protein = 0;
	private double carbs = 0;
	private double calories = 0;
	private double sugar = 0;

	private final List<ConsumableOccurrence> consumables;
	private MutableLiveData<List<ConsumableOccurrence>> consumablesLiveData;

	private final StandardProfile standardProfile;

	// TODO: Implement allergies.
	//private final Allergy[] allergies;


	/**
	 * Only for Room.
	 * @param consumablesDao
	 * @param standardProfile
	 */
	@Deprecated
	public Nutrition(ConsumablesDao consumablesDao, StandardProfile standardProfile) {
		this.standardProfile = standardProfile;
		this.consumablesDao = consumablesDao;
		this.consumables = consumablesDao.loadConsumableOccurrences(standardProfile.getStandardProfileId());
		this.consumables.forEach(consumable -> consumable.init(new Consumables(consumablesDao)));
	}

	private Nutrition(Application application, StandardProfile standardProfile){
		this(HFSDatabase.getInstance(application).consumablesDao(), standardProfile);
		this.consumablesLiveData = new MutableLiveData<>(this.consumables);
	}

	public static synchronized Nutrition getInstance(Application application, StandardProfile standardProfile){
		if(instance == null){
			instance = new Nutrition(application, standardProfile);
		}

		return instance;
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

		/* TODO: Uncomment when Allergies implemented
		if(this.allergies != null){
			final List<Allergy> userAllergies = Arrays.asList(this.allergies);
			final boolean itemContainsAllergies = Arrays.stream(consumable.getAllergies())
					.anyMatch(userAllergies::contains);

			if(itemContainsAllergies) {
				throw new IllegalArgumentException(consumable.getName() + " contains one or many allergen to which the user is allergic.");
			}
		}
		 */

		final ConsumableOccurrence consumedItem = new ConsumableOccurrence(consumable, amount, standardProfile);
		this.consumablesDao.insert(consumedItem);
        this.consumables.add(consumedItem);
        this.update(consumedItem);
	}

	private void update(ConsumableOccurrence consumedItem) {
		final double amountConsumed = consumedItem.getAmount();
		final Consumable itemConsumed = consumedItem.getConsumable();

		this.protein = itemConsumed.getProteins() * amountConsumed;
		this.carbs = itemConsumed.getCarbs() * amountConsumed;
		this.calories = itemConsumed.getCalories() * amountConsumed;
		this.sugar = itemConsumed.getSugar() * amountConsumed;
	}

	public LiveData<List<ConsumableOccurrence>> getConsumables() {
		return this.consumablesLiveData;
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
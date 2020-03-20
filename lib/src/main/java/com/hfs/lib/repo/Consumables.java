package com.hfs.lib.repo;

import com.hfs.lib.nutrition.Allergy;
import com.hfs.lib.nutrition.Consumable;
import com.hfs.lib.nutrition.ConsumableType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Consumables {

	private static Consumables instance;
	private List<Consumable> consumables;

	private Consumables() {
		// TODO - Change dummies to appropriate values from db.
		// TODO - Possibly use a Set as no duplicate Consumable should be allowed.
		final Consumable[] dummyConsumables = new Consumable[]{
				new Consumable("Pizza", 100, 100, 100, 100, ConsumableType.FOOD, new Allergy[]{Allergy.MILK}),
				new Consumable("Pasta", 101, 101,101, 101, ConsumableType.FOOD, null),
				new Consumable("Baked Beans", 102, 102, 102, 102, ConsumableType.FOOD, null),
				new Consumable("Eggs", 103, 103, 103, 103, ConsumableType.FOOD, new Allergy[]{Allergy.EGG}),
				new Consumable("Milk", 104, 104, 104, 104, ConsumableType.DRINK, new Allergy[]{Allergy.MILK}),
				new Consumable("Orange Juice", 105, 105, 105, 105, ConsumableType.DRINK, null)
		};

		this.consumables = new ArrayList<>(Arrays.asList(dummyConsumables));
	}

	public static Consumables getInstance() {
	    if(instance == null){
	    	instance = new Consumables();
		}
		return instance;
	}

	/**
	 * 
	 * @param name
	 */
	public Consumable getConsumable(String name) {
	    return this.consumables.parallelStream()
				.filter(consumable -> consumable.getName().equals(name))
				.findFirst().get();
	}

	public List<Consumable> getConsumables() {
		return new ArrayList<>(this.consumables);
	}

	public void addConsumable(Consumable consumable) {
		this.consumables.add(consumable);
	}

}
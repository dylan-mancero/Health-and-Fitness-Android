package com.hfs.lib.repo;

import com.hfs.lib.nutrition.Consumable;

public class Consumables {

	private Consumables instance;

	private Consumables() {
		// TODO - implement Consumables.Consumables
		throw new UnsupportedOperationException();
	}

	public Consumables getInstance() {
		return this.instance;
	}

	/**
	 * 
	 * @param name
	 */
	public Consumable getConsumable(String name) {
		// TODO - implement Consumables.getConsumable
		throw new UnsupportedOperationException();
	}

}
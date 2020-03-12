package com.hfs.lib.nutrition;

import java.time.OffsetDateTime;

public class ConsumableOccurance {

	private final double amount;
	private final OffsetDateTime date;
	private final Consumable consumable;

	/**
	 * 
	 * @param consumable
	 * @param amount
	 */
	public ConsumableOccurance(Consumable consumable, double amount) throws IllegalArgumentException{
		if(amount <= 0){
			throw new IllegalArgumentException(consumable.getName() + " consumed cannot be: " + amount + " g\nExpected more than 0g.\n");
		}

	    this.consumable = consumable;
		this.amount = amount;
		this.date = OffsetDateTime.now();
	}

	public double getAmount() {
		return amount;
	}

	public OffsetDateTime getDate() {
		return date;
	}

	public Consumable getConsumable() {
		return consumable;
	}
}
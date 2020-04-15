package com.hfs.lib.repo;

import android.app.Application;

import com.hfs.lib.HFSDatabase;
import com.hfs.lib.dao.ConsumablesDao;
import com.hfs.lib.nutrition.Consumable;

import java.util.Collections;
import java.util.List;

public class Consumables {
	private static Consumables instance;

	private List<Consumable> consumables;
	private final ConsumablesDao consumablesDao;

	public Consumables(ConsumablesDao consumablesDao) {
		// TODO - Possibly use a Set as no duplicate Consumable should be allowed.
		this.consumablesDao = consumablesDao;
		this.consumables = this.consumablesDao.loadConsumables();
	}

	private Consumables(Application application) {
		final HFSDatabase db = (HFSDatabase) HFSDatabase.getInstance(application);
		this.consumablesDao = db.consumablesDao();
		this.consumables = this.consumablesDao.loadConsumables();
	}

	public static Consumables getInstance(Application application){
		if(instance == null){
			instance = new Consumables(application);
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
		return Collections.unmodifiableList(this.consumables);
	}

/*	TODO: Implement addConsumables.
	public void addConsumable(Consumable consumable) {
		this.consumables.add(consumable);
	}

 */
}
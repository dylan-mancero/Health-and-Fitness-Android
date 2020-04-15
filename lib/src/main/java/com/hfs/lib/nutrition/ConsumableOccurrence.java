package com.hfs.lib.nutrition;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.hfs.lib.StandardProfile;
import com.hfs.lib.dao.ConsumablesDao;
import com.hfs.lib.repo.Consumables;
import com.hfs.lib.typeconverters.OffsetDateTimeConverter;

import java.time.OffsetDateTime;
import static androidx.room.ForeignKey.NO_ACTION;
@Entity(foreignKeys = @ForeignKey(
		entity = Consumable.class,
		parentColumns = "name",
		childColumns = "consumableName",
		onDelete = NO_ACTION
))
public class ConsumableOccurrence {

	@PrimaryKey(autoGenerate = true)
	private long id;
	private final String consumableName;
	private final long userId;

	private final double amount;
	@TypeConverters(OffsetDateTimeConverter.class)
	private final OffsetDateTime date;
	@Ignore
	private Consumable consumable;

	/**
	 * Only for Room.
	 * @param id
	 * @param amount
	 * @param date
	 */
	@Deprecated
	public ConsumableOccurrence(long id, String consumableName, long userId, double amount, OffsetDateTime date) {
		this.id = id;
		this.consumableName = consumableName;
		this.userId = userId;
		this.amount = amount;
		this.date = date;
	}

	/**
	 * Only for Room.
	 * @return
	 */
	public long getId() {
		return id;
	}

	/**
	 * Only for Room.
	 * @return
	 */
	@Deprecated
	public String getConsumableName() {
		return consumableName;
	}

	/**
	 * Only for Room.
	 * @return
	 */
	@Deprecated
	public long getUserId() {
		return userId;
	}

	@Deprecated
	public ConsumableOccurrence init(Consumables consumables) {
		this.consumable = consumables.getConsumable(this.consumableName);
		return this;
	}

	public ConsumableOccurrence(Consumable consumable, double amount, StandardProfile profile) throws IllegalArgumentException{
		if(amount <= 0){
			throw new IllegalArgumentException(consumable.getName() + " consumed cannot be: " + amount + " g\nExpected more than 0g.\n");
		}

	    this.consumable = consumable;
		this.consumableName = consumable.getName();
		this.userId = profile.getStandardProfileId();
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
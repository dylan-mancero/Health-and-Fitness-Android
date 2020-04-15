package com.hfs.lib.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.hfs.lib.nutrition.Consumable;
import com.hfs.lib.nutrition.ConsumableOccurrence;

import java.util.List;

@Dao
public interface ConsumablesDao {

    @Insert
    void insert(Consumable... consumables);

    @Query("SELECT * FROM consumable")
    List<Consumable> loadConsumables();

    @Insert
    long[] insert(ConsumableOccurrence... consumableOccurrences);

    @Delete
    void delete(ConsumableOccurrence consumableOccurrence);

    @Query("SELECT * FROM consumableoccurrence WHERE userId = :id")
    List<ConsumableOccurrence> loadConsumableOccurrences(long id);
}

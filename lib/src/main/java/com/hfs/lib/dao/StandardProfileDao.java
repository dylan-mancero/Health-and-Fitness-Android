package com.hfs.lib.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.hfs.lib.StandardProfile;

@Dao
public interface StandardProfileDao {

    @Query("SELECT * FROM standardprofile WHERE standardProfileId = :id LIMIT 1")
    StandardProfile getStandardProfile(int id);

}

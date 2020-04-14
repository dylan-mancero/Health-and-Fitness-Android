package com.hfs.lib;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.hfs.lib.activity.Activity;
import com.hfs.lib.activity.ExerciseOccurrence;
import com.hfs.lib.activity.FinishedActivity;
import com.hfs.lib.activity.Sport;
import com.hfs.lib.activity.SportOccurrence;
import com.hfs.lib.activity.UnfinishedActivity;
import com.hfs.lib.dao.StandardProfileDao;

import java.time.OffsetDateTime;

public class Account {

	private String username;
	private String password;
	private String email;
	private OffsetDateTime dateOfBirth;
	// TODO : implement image
	//  private Image image;
	private Gender gender;

	/**
	 * 
	 * @param username
	 * @param password
	 * @param email
	 * @param dateOfBirth
	 * @param gender
	 */
	public Account(String username, String password, String email, OffsetDateTime dateOfBirth, Gender gender) {
	    this.username = username;
	    // TODO: Password hashin ?
	    this.password = password;
	    this.email = email;
	    this.dateOfBirth = dateOfBirth;
	    this.gender = gender;
	}

	public String getUsername() {
		return this.username;
	}

	/**
	 * 
	 * @param password
	 */
	public boolean isPassword(String password) {
		// TODO - Hashing?
		return password.equals(password);
	}

	/**
	 * 
	 * @param password
	 */
	public void updatePassword(String password) {
	    this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	/**
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/*
	public Image getImage() {
		return this.image;
	}
	 */

	/**
	 * 
	 * @param email
	 */
	public void resetPassword(String email) {
		// TODO - implement Account.resetPassword
		throw new UnsupportedOperationException();
	}

	@Database(entities = {Activity.class, Sport.class, StandardProfile.class, UnfinishedActivity.class, FinishedActivity.class, SportOccurrence.class, ExerciseOccurrence.class}, version = 1)
	public abstract static class HFSDatabase extends RoomDatabase {
		private static HFSDatabase instance;

		public abstract StandardProfileDao activitiesDao();

		public static synchronized HFSDatabase getInstance(Context context) {
			// TODO: Run db in a background thread - remove allowMainThredQueries()
			if(instance == null){
				instance = Room.databaseBuilder(context.getApplicationContext(),
						HFSDatabase.class, "hfs_db")
						.allowMainThreadQueries()
						.fallbackToDestructiveMigration()
						.build();
			}

			return instance;
		}
	}
}
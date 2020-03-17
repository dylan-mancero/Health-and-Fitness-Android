package com.hfs.lib;

import java.awt.Image;
import java.time.OffsetDateTime;
import java.util.Date;

public class Account {

	private String username;
	private String password;
	private String email;
	private OffsetDateTime dateOfBirth;
	private Image image;
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

	public Image getImage() {
		return this.image;
	}

	/**
	 * 
	 * @param email
	 */
	public void resetPassword(String email) {
		// TODO - implement Account.resetPassword
		throw new UnsupportedOperationException();
	}

}
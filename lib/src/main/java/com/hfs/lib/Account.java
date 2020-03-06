package com.hfs.lib;

import java.awt.Image;
import java.util.Date;

public class Account {

	private String username;
	private String password;
	private String email;
	private Date dateOfBirth;
	private Image image;

	/**
	 * 
	 * @param username
	 * @param password
	 * @param email
	 * @param dateOfBirth
	 * @param gender
	 */
	public Account(String username, String password, String email, Date dateOfBirth, Gender gender) {
		// TODO - implement Account.Account
		throw new UnsupportedOperationException();
	}

	public String getUsername() {
		return this.username;
	}

	/**
	 * 
	 * @param password
	 */
	public void isPassword(String password) {
		// TODO - implement Account.isPassword
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param password
	 */
	public void updatePassword(String password) {
		// TODO - implement Account.updatePassword
		throw new UnsupportedOperationException();
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
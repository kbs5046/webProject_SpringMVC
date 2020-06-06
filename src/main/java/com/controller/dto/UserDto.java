package com.controller.dto;

import java.sql.Timestamp;
import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;

public class UserDto {
	private int uID;
	private String userName;
	private String password;
	private String name;
	private String email;
	private String salutation;
	private Timestamp dateCreated;
	private String role;
	private MultipartFile photo;
    private byte[] bphoto;
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getuID() {
		return uID;
	}

	public void setuID(int uID) {
		this.uID = uID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public Timestamp getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}

	public MultipartFile getPhoto() {
		return photo;
	}

	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}

	public byte[] getBphoto() {
		return bphoto;
	}

	public void setBphoto(byte[] bphoto) {
		this.bphoto = bphoto;
	}

	@Override
	public String toString() {
		return "UserDto [uID=" + uID + ", userName=" + userName + ", password=" + password + ", name=" + name
				+ ", email=" + email + ", salutation=" + salutation + ", dateCreated=" + dateCreated + ", role=" + role
				+ ", photo=" + photo + ", bphoto=" + Arrays.toString(bphoto) + "]";
	}

	
}

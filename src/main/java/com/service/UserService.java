package com.service;

import java.util.List;

import com.controller.dto.UserDto;

public interface UserService {
	UserDto authUser(String username,String password);
	void signup(UserDto userDto);
	List<UserDto> displayAll();
	void deleteById(int sid);
	UserDto selectById(int sid);
	void updateUser(UserDto userDto);
	public List<UserDto> getSignups(int pageid,int total);
	public int findTotalSignup();
	List<UserDto> searchUser(String name);
	byte[] findImageById(int uid);
}

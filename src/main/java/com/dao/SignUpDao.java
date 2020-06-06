package com.dao;

import java.util.List;

import com.dao.entity.UserEntity;

public interface SignUpDao {
	UserEntity authUser(String username,String password);
	void signup(UserEntity userEntity);
	List<UserEntity> displayAll();
	void deleteById(int sid);
	UserEntity selectById(int sid);
	void updateUser(UserEntity userEntity);
	List<UserEntity> getSignups(int pageid, int total);
	int findTotalSignup();
	List<UserEntity> searchUser(String name);
	byte[] findImageById(int uid);
	
}

package com.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.controller.dto.UserDto;
import com.dao.SignUpDao;
import com.dao.entity.UserEntity;
import com.utils.NullPropertyUtils;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	@Qualifier("SignUpDaoOrmImpl")
	private SignUpDao signupDao;

	@Override
	public UserDto authUser(String username, String password) {
		UserEntity userEntity = signupDao.authUser(username, password);
		UserDto userDto = null;
		if(userEntity != null) {
			userDto=new UserDto();
 			BeanUtils.copyProperties(userEntity, userDto);
		}
		return userDto;
	}
	
	@Override
	public void signup(UserDto userDto) {
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(userDto,userEntity);
		signupDao.signup(userEntity);
	}
	
	@Override
	public List<UserDto> displayAll() {
		List<UserDto> userListDto = new ArrayList<UserDto>();
		List<UserEntity> userListDao = signupDao.displayAll();
		for(UserEntity entity:userListDao) {
 			UserDto userDto= new UserDto();
 			BeanUtils.copyProperties(entity, userDto);
 			userListDto.add(userDto);
 		}
		
		return userListDto;
		
	}
	
	@Override
	public void deleteById(int sid) {
		signupDao.deleteById(sid);
	}
	
	@Override
	public UserDto selectById(int sid) {
		UserEntity userEntity = signupDao.selectById(sid);
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userEntity,userDto);
		return userDto;
	}
	
	@Override
	public void updateUser(UserDto userDto) {
//		UserEntity userEntity = new UserEntity();
//		BeanUtils.copyProperties(userDto,userEntity);
//		signupDao.updateUser(userEntity);
		UserDto dUserDto = selectById(userDto.getuID());
		NullPropertyUtils.copyNonNullProperties(userDto, dUserDto);
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(dUserDto,userEntity);
		signupDao.updateUser(userEntity);	
	}

	@Override
	public List<UserDto> getSignups(int pageid, int total) {
		List<UserEntity> userListDao = signupDao.getSignups(pageid, total);
		List<UserDto> userListDto = new ArrayList<UserDto>();
		for(UserEntity entity:userListDao) {
 			UserDto userDto= new UserDto();
 			BeanUtils.copyProperties(entity, userDto);
 			userListDto.add(userDto);
 		}
		return userListDto;
	}

	@Override
	public int findTotalSignup() {
		int count = signupDao.findTotalSignup();
		return count;
	}

	@Override
	public List<UserDto> searchUser(String name) {
		List<UserEntity> userListDao = signupDao.searchUser(name);
		List<UserDto> userListDto = new ArrayList<UserDto>();
		for(UserEntity entity:userListDao) {
 			UserDto userDto= new UserDto();
 			BeanUtils.copyProperties(entity, userDto);
 			userListDto.add(userDto);
 		}
		return userListDto;
	}

	@Override
	public byte[] findImageById(int uid) {
		byte[]  photo = signupDao.findImageById(uid);
		return photo;
	}
}

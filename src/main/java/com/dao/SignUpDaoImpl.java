package com.dao;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;

import com.dao.entity.UserEntity;
import com.dao.query.SQLQuery;

@Repository("SignUpDaoJdbcImpl")
public class SignUpDaoImpl implements SignUpDao {
	@Autowired
	@Qualifier("jdbcDriver")
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
	public SignUpDaoImpl() {
    }
	
	@PostConstruct
 	public void init() {
 		jdbcTemplate=new JdbcTemplate(dataSource);
 	}
	
	@Override
	public UserEntity authUser(String username, String password) {
		
		List<UserEntity> signupList=jdbcTemplate.query(SQLQuery.SELECT_SIGNUP_USERNAME_PASSWORD, new Object[] {username,password},new BeanPropertyRowMapper<>(UserEntity.class));
		return signupList.size()==0 ?null : signupList.get(0);	
	}
	
	@Override
	public void signup(UserEntity userEntity) {
		Timestamp now = new Timestamp(new Date().getTime());
		LobHandler lobHandler = new DefaultLobHandler();
		SqlLobValue sqlLobValue = new SqlLobValue(userEntity.getBphoto(),lobHandler);
		Object[] data = {userEntity.getUserName(),userEntity.getPassword(),userEntity.getName(),userEntity.getEmail(),userEntity.getSalutation(),now,sqlLobValue};
		jdbcTemplate.update(SQLQuery.ADD_USER,data,new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP,Types.BLOB});
		jdbcTemplate.update(SQLQuery.DELETE_UID_COL);
		jdbcTemplate.update(SQLQuery.ADD_UID_COL);		
		
	}
	
	@Override
	public List<UserEntity> displayAll() {
		List<UserEntity> userList = jdbcTemplate.query(SQLQuery.SELECT_ALL_USERS,new BeanPropertyRowMapper<>(UserEntity.class));
		return userList;
		
	}
	
	@Override
	public void deleteById(int sid) {
		jdbcTemplate.update(SQLQuery.DELETE_USER_BY_UID,sid);
		jdbcTemplate.update(SQLQuery.UPDATE_UIDS_AFTER_DELETE);
	}
	
	@Override
	public UserEntity selectById(int sid) {
		UserEntity userEntity = (UserEntity)jdbcTemplate.queryForObject(SQLQuery.SELECT_USER_BY_UID, new Object[] {sid}, new BeanPropertyRowMapper<>(UserEntity.class));
		return userEntity;
	}
	
	@Override
	public void updateUser(UserEntity userEntity) {
		LobHandler lobHandler = new DefaultLobHandler();
		SqlLobValue sqlLobValue = new SqlLobValue(userEntity.getBphoto(),lobHandler);
		Object[] data = {userEntity.getUserName(),userEntity.getPassword(),userEntity.getName(),userEntity.getEmail(),userEntity.getSalutation(),sqlLobValue,userEntity.getuID()};
		jdbcTemplate.update(SQLQuery.EDIT_USER,data,new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.BLOB,Types.INTEGER});
	}
	
	@Override
    public List<UserEntity> getSignups(int pageid,int total) {
		Object[] data = {total,pageid-1};
        List<UserEntity> signupList=jdbcTemplate.query(SQLQuery.SELECT_WITH_LIMIT, data,new BeanPropertyRowMapper<>(UserEntity.class));
        return signupList;
    }
	
	@Override
	 public int findTotalSignup() {
         Integer count=jdbcTemplate.queryForObject(SQLQuery.FIND_TOTAL, Integer.class);
         return count;
     }

	@Override
	public List<UserEntity> searchUser(String name) {
		List<UserEntity> searchList=jdbcTemplate.query(SQLQuery.SEARCH_BY_NAME, new Object[] {name},new BeanPropertyRowMapper<>(UserEntity.class));
        return searchList;
	}

	@Override
	public byte[] findImageById(int uid) {
		byte[]  photo = jdbcTemplate.queryForObject(SQLQuery.FIND_IMAGE_BY_ID, new Object[] {uid},byte[].class);
		return photo;
	}
	
}






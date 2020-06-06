package com.dao.query;

public interface SQLQuery {
	public static String SELECT_SIGNUP_USERNAME_PASSWORD = "select uID,UserName,Password,Name,Email,Salutation,DateCreated,Role from Users where UserName=? and Password=? ";
	public static String ADD_USER = "insert into USERS(UserName,Password,Name,Email,Salutation,DateCreated,Picture) values(?,?,?,?,?,?,?)";
	//public static String SELECT_ALL_USERS = "select uID,UserName,Password,Name,Email,Salutation,DateCreated,Role from Users" ;
	public static String SELECT_ALL_USERS = "select * from Users" ;
	public static String SELECT_SIGNUPS="select uID,UserName,Password,Name,Email,Salutation,DateCreated,Role from Users";
	public static String DELETE_USER_BY_UID = "delete from Users where uID =?";
	public static String SELECT_USER_BY_UID = "select uId,UserName,Password,Name,Email,Salutation,DateCreated,Picture from Users where uID =?";
	public static String EDIT_USER = "update Users set UserName = ?,Password = ?,Name=?,Email=?,Salutation=?, Picture=? where uID = ?";
	
	//update uid after delete and signup
	public static String UPDATE_UIDS_AFTER_DELETE = "UPDATE Users SET uID = uID - 1 WHERE uID > ?";
	public static String DELETE_UID_COL = "ALTER TABLE Users DROP uID";
	public static String ADD_UID_COL = "ALTER TABLE `Users` ADD `uID` INT(11) NOT NULL AUTO_INCREMENT FIRST, ADD PRIMARY KEY (`uID`)";
	
	public static String SELECT_WITH_LIMIT = "select uID,UserName,Password,Name,Email,Salutation,DateCreated,Role from Users order by uID asc limit ? offset ?";
	public static String FIND_TOTAL = "select count(*) from Users";
	public static String SEARCH_BY_NAME = "select uID,UserName,Password,Name,Email,Salutation,DateCreated,Role from Users where Name=?";
	public static String FIND_IMAGE_BY_ID = "Select Picture from Users where uID=?";
}

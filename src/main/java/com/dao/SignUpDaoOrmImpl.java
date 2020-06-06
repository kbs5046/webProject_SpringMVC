package com.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dao.entity.UserEntity;



@Repository("SignUpDaoOrmImpl")
public class SignUpDaoOrmImpl implements SignUpDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		//return sessionFactory.openSession();
		return sessionFactory.getCurrentSession();
	}

	@Override
	public UserEntity authUser(String username, String password) {
		Session session = getSession();
		TypedQuery<UserEntity> query = session.createQuery("Select entity from UserEntity entity where entity.userName=:pusername and entity.password=:ppassword",UserEntity.class);
		query.setParameter("pusername", username);
		query.setParameter("ppassword", password);
		try {
			return query.getSingleResult();
		}
		catch(Exception e) {
//			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void signup(UserEntity userEntity) {
		Session session = getSession();
		session.save(userEntity);
	}

	@Override
	public List<UserEntity> displayAll() {
		Session session = getSession();
		List<UserEntity> userEntities = session.createQuery("Select entity from UserEntity entity",UserEntity.class).getResultList();
		return userEntities;
	}

	@Override
	public void deleteById(int sid) {
//		//getSession().delete(UserEntity.class,sid);
//		Session session = getSession();
//		//Transaction transaction = session.beginTransaction();
//		UserEntity userEntity = session.get(UserEntity.class, sid);		session.delete(userEntity);
//		Query query = session.createQuery("delete from UserEntity entity where entity.uID = :puID");
//		query.setParameter("puID", sid);
//		query.executeUpdate();
//		//transaction.commit();
		
		Session session = getSession();
		UserEntity userEntity = session.byId(UserEntity.class).load(sid);
		session.delete(userEntity);
	
	}

	@Override
	public UserEntity selectById(int sid) {
		Session session = getSession();
		UserEntity userEntity = session.get(UserEntity.class, sid);
		return userEntity;
	}

	@Override
	public void updateUser(UserEntity userEntity) {
//		Session session = getSession();
//		//Transaction transaction = session.beginTransaction();
//		Query query = session.createQuery("Update UserEntity entity set entity.userName = :puserName,entity.password=:ppassword,e"
//				+ "ntity.name=:pname,entity.email=:pemail,entity.salutation=:psalutation,entity.bphoto=:pbphoto where entity.uID = :puID");
//		query.setParameter("puserName", userEntity.getUserName());
//		query.setParameter("ppassword", userEntity.getPassword());
//		query.setParameter("pname", userEntity.getName());
//		query.setParameter("pemail", userEntity.getEmail());
//		query.setParameter("psalutation", userEntity.getSalutation());
//		query.setParameter("pbphoto", userEntity.getBphoto());
//		query.setParameter("puID", userEntity.getuID());
//		query.executeUpdate();
		//transaction.commit();
		
		Session session = getSession();
		session.merge(userEntity);
		
	}

	@Override
	public List<UserEntity> getSignups(int pageid, int total) {
		Session session = getSession();
		TypedQuery<UserEntity> query = session.createQuery("Select entity from UserEntity entity",UserEntity.class);
		query.setFirstResult(pageid-1);
		query.setMaxResults(total);
		List<UserEntity> userEntities = query.getResultList();
		return userEntities;
	}

	@Override
	public int findTotalSignup() {
//		Session session = getSession();
//		Query query = session.createQuery("select count(*) from UserEntity",UserEntity.class);
//		Long count = (Long)query.uniqueResult();		
//		return count.intValue();
		return displayAll().size();
	}

	@Override
	public List<UserEntity> searchUser(String name) {
		Session session = getSession();
		TypedQuery<UserEntity> query=session.createQuery("select entity from UserEntity entity where entity.name=:pname",UserEntity.class);
		query.setParameter("pname", name);
		List<UserEntity> userEntities = query.getResultList();		
		return userEntities;
	}

	@Override
	public byte[] findImageById(int uid) {
		Session session = getSession();
		UserEntity userEntity = session.get(UserEntity.class, uid);
		return userEntity.getBphoto();
	}

}

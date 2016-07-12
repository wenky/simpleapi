package com.wenky.app.business.repository;

import java.io.Serializable;

import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class EntityDao {

	@Inject
	SessionFactory sessionFactory;

	public void save(Object entity) {
		sessionFactory.getCurrentSession().save(entity);
	}

	public Object get(Class clazz,Serializable id) {
		return sessionFactory.getCurrentSession().get(clazz,id);
	}

}

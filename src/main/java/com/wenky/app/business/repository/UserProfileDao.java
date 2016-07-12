package com.wenky.app.business.repository;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.wenky.app.business.entity.UserProfile;

@Repository
public class UserProfileDao {
	@Inject
	SessionFactory sessionFactory;

	public UserProfile getUserProfile(String userName, String password) {
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(UserProfile.class);
		criteria.add(Restrictions.and(Restrictions.eq("userName", userName),Restrictions.eq("password", password)));
		return (UserProfile) criteria.uniqueResult();

	}

}

package com.wenky.app.business.repository;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.wenky.app.business.entity.UserProfileDetails;

@Repository
public class UserProfileDetailsDao {

	@Inject
	SessionFactory sessionFactory;
	public UserProfileDetails getUserProfileDetailsByIdUser(String idUser) {
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(UserProfileDetails.class);
		criteria.add(Restrictions.eq("userProfile.idUser", idUser));
		
		UserProfileDetails userProfileDetails=(UserProfileDetails) criteria.uniqueResult();
		return userProfileDetails;
	}

}

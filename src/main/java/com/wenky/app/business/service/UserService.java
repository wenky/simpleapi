package com.wenky.app.business.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.wenky.app.business.entity.UserProfile;
import com.wenky.app.business.entity.UserProfileDetails;
import com.wenky.app.business.repository.EntityDao;
import com.wenky.app.business.repository.UserProfileDao;
import com.wenky.app.business.repository.UserProfileDetailsDao;

@Service
@Transactional
public class UserService {
	@Inject
	EntityDao entityDao;
	@Inject
	UserProfileDetailsDao userProfileDetailsDao;
	@Inject
	UserProfileDao userProfileDao;

	public void register(Model model) {
		UserProfile userProfile=(UserProfile) model.asMap().get("userProfile");
		entityDao.save(userProfile);
	}

	public void info(Model model) {
		model.addAttribute("userProfileDetails",userProfileDetailsDao.getUserProfileDetailsByIdUser((String) model.asMap().get("idUser")));
	}

	public UserProfile getUserProfile(String userName, String password) {
		return userProfileDao.getUserProfile(userName,password);
	}

	public void addinfo(Model model) {
		UserProfileDetails userProfileDetails=(UserProfileDetails) model.asMap().get("userProfileDetails");
		UserProfile userProfile=(UserProfile) entityDao.get(UserProfile.class, Long.parseLong((String) model.asMap().get("idUser")));
		entityDao.save(userProfileDetails);
		userProfileDetails.setUserProfile(userProfile);
		
	}

}

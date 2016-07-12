package com.wenky.app.facade;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.inject.Inject;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.wenky.app.business.entity.UserProfile;
import com.wenky.app.business.service.CachingService;
import com.wenky.app.business.service.UserService;
import com.wenky.app.dto.AccessTokenDto;
import com.wenky.app.util.PreConditions;

@Component
public class AuthenticationFacade {
	@Inject
	UserService userService;
	@Inject
	CachingService cachingService;

	public void login(Model model) {
		UserProfile userProfile=(UserProfile) model.asMap().get("userProfile");
		UserProfile tmp=userService.getUserProfile(userProfile.getUserName(),userProfile.getPassword());
				if(PreConditions.isEmpty(tmp)){
					Map result=new HashMap();
					result.put("status", "failed");
					result.put("reason", "Invalied Username/Password");
					model.addAttribute("result",result);
				return;
				}
		Map result=new HashMap();
		result.put("status", "success");
		AccessTokenDto accessTokenDto=new AccessTokenDto();
		String code=new Random().nextLong()+"";
		code=code.startsWith("-")?code.substring(1):code;
		accessTokenDto.setCode(code);
		accessTokenDto.setExpiry(new Date());
		result.put("accesstoken", accessTokenDto);
		model.addAttribute("result",result);
		cachingService.put(tmp.getIdUserProfile()+"", accessTokenDto);
	}
}
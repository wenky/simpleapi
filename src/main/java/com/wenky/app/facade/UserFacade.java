package com.wenky.app.facade;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.wenky.app.business.service.CachingService;
import com.wenky.app.business.service.UserService;
import com.wenky.app.dto.AccessTokenDto;
import com.wenky.app.util.PreConditions;

@Component
public class UserFacade {
	@Inject
	UserService userService;
	@Inject
	CachingService cachingService;

	public void register(Model model) {
		userService.register(model);

	}

	public void info(Model model) {
		userService.info(model);
	}

	public void addinfo(Model model) {
	try {
		if(!PreConditions.isEmpty(cachingService.get((String) model.asMap().get("idUser")))){
			System.out.println("bb");
			AccessTokenDto accessTokenDto=cachingService.get((String) model.asMap().get("idUser"));
			if(accessTokenDto.getCode().equals((String)model.asMap().get("accessToken"))){
				userService.addinfo(model);
				Map result=new HashMap();
				result.put("status", "success");
				result.put("reason", "UserProfileDetails updated");
				model.addAttribute("result",result);
				return;
			}
		}else{
			Map result=new HashMap();
			result.put("status", "failed");
			result.put("reason", "Access Token expired");
			model.addAttribute("result",result);
			return;
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
		
	Map result=new HashMap();
	result.put("status", "failed");
	result.put("reason", "Unable to update UserProfileDetails");
	model.addAttribute("result",result);
	}

}

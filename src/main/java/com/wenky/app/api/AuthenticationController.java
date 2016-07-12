package com.wenky.app.api;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wenky.app.business.entity.UserProfile;
import com.wenky.app.dto.AccessTokenDto;
import com.wenky.app.facade.AuthenticationFacade;
import com.wordnik.swagger.annotations.Api;

@Controller
@RequestMapping("/api/auth")
@Api(description="Authentication Controller",value="/api/auth")
public class AuthenticationController {

	@Inject
	AuthenticationFacade authenticationFacade;

	@RequestMapping("/login")
	@ResponseBody
	public Map login(Model model, @ModelAttribute("userProfile") UserProfile userProfile) {
		authenticationFacade.login(model);
		return (Map) model.asMap().get("result");
	}

	@RequestMapping("/logout")
	public AccessTokenDto logout(Model model, @ModelAttribute("userProfile") UserProfile userProfile) {
		//Map<String, AccessTokenDto> accessTokenMap = (Map<String, AccessTokenDto>) context.getAttribute("accessTokenMap");
//		accessTokenMap.remove(userProfile.getUserName());
		return (AccessTokenDto) model.asMap().get("accessToken");
	}
}
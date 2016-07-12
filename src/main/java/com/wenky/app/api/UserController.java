package com.wenky.app.api;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wenky.app.business.entity.UserProfile;
import com.wenky.app.business.entity.UserProfileDetails;
import com.wenky.app.facade.UserFacade;

@Controller
@RequestMapping("/api/user")
public class UserController {
	
	@Inject
	UserFacade userFacade;
	@RequestMapping("/register")
	@ResponseBody
	public void register(Model model,@ModelAttribute("userProfile") UserProfile userProfile){
		userFacade.register(model);
	}
	
	@RequestMapping("/{accessToken}/info/add/{idUser}")
	@ResponseBody
	public Object addInfo(Model model,@ModelAttribute("userProfileDetails") UserProfileDetails userProfileDetails,@PathVariable String accessToken,@PathVariable String idUser){
		model.addAttribute("idUser",idUser);
		model.addAttribute("accessToken", accessToken);
		userFacade.addinfo(model);
		return model.asMap().get("result");
	}
	
	@RequestMapping("/{accessToken}/info/{idUser}")
	@ResponseBody
	public Object info(Model model,@PathVariable String accessToken,@PathVariable String idUser){
		model.addAttribute("idUser",idUser);
		model.addAttribute("accessToken", accessToken);
		userFacade.info(model);
		return model.asMap().get("userProfileDetails");
	}
}
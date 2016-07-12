package com.wenky.app.business.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.wenky.app.dto.AccessTokenDto;
import com.wenky.app.util.PreConditions;

@Service
public class CachingService {
	Map<String, AccessTokenDto> accessTokenMap = new HashMap<String, AccessTokenDto>();

	public void put(String key, AccessTokenDto accessTokenDto) {
		accessTokenMap.put(key, accessTokenDto);
	}

	public AccessTokenDto get(String key) {
		return accessTokenMap.get(key);
	}

	@Async
	@Scheduled(fixedDelay = 60 * 1000) //For every 60sec
	public void removeExpiredTokens() {
		System.out.println("Running...");
		// Timestamp current=new Date().getTime();
		if (!PreConditions.isEmpty(accessTokenMap)) {
			Set<String> keySet = accessTokenMap.keySet();

			for (String key : keySet) {
				AccessTokenDto accessTokenDto = accessTokenMap.get(key);
				if(System.currentTimeMillis()-accessTokenDto.getExpiry().getTime()>60*1000){
					accessTokenMap.remove(key);
					System.out.println("removing key"+key);
				}
				// //compare with current timestamp and deleteif it has exceeded
				// the expiry Time
			}
		}
	}

}

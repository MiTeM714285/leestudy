package com.springboot.leestudy.service.CoolSMS;

import java.util.HashMap;
import java.util.Random;

import org.springframework.stereotype.Service;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Service
public class CoolSMSService {
	
	// https://console.coolsms.co.kr/ 에서 발급
	private final String API_KEY = "NCSXIXEHONIJPAXX";
	private final String API_SECRET = "2NQBEETBATTTV68GEN1QIJXOFMWMAM8S";
	
	public String createAuthenticationCode() { // 랜덤 코드 4자리 생성 메소드
		String authenticationCode = "";
		
		Random randNumber = new Random();
		int codeLength = 4; 
		
		for(int i = 0; i<codeLength; i++) {
			String randCode = Integer.toString(randNumber.nextInt(10)); // 0~9 숫자중 랜덤으로 생성 
			authenticationCode += randCode;
		}
		return authenticationCode;
	}
	
	public String sendAuthenticationCode(String phoneNumber) {
		Message coolsms = new Message(API_KEY, API_SECRET);
		String authenticationCode = createAuthenticationCode();
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("to", phoneNumber); // 수신 측
		params.put("from", "01065850377"); // 송신 측
		params.put("type", "SMS");
		params.put("text", "[이과외 인증번호]\n" + "[" + authenticationCode + "]" + "입니다.");
		params.put("app_version", "leestudy app 1.0");
		
		try {
			coolsms.send(params);
		} catch (CoolsmsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return authenticationCode;
	}
}

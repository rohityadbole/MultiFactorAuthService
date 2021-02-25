package com.multifactauth.service;


import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class SMSService {

	private final static String ACCOUNT_SID = "AC7e12b10f82fbff34f1806e608085ba78";
	private final static String AUTH_ID = "3067ccb660f436417cec9135c0392f46";
	
	static {
		Twilio.init(ACCOUNT_SID, AUTH_ID);
	}
	
	public boolean send2FaCode(String mobilenumber, String twoFaCode) {
		
		Message.creator(new PhoneNumber(mobilenumber), new PhoneNumber("+15005550006"),
				"Your Two Factor Authentication code is: "+ twoFaCode).create();
		
		return true;
		
		
	}
}

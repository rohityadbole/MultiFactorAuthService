package com.multifactauth.service;


import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class SMSService {

	/*private final static String ACCOUNT_SID = "AC7e12b10f82fbff34f1806e608085ba78";
	private final static String AUTH_ID = "3067ccb660f436417cec9135c0392f46";
	*/
	private final static String ACCOUNT_SID = "ACd5154d27c8ed99b5fb85ce0e88983df3";
	private final static String AUTH_ID = "7cbd6d06b13326c37f055323a8939041";
	static {
		Twilio.init(ACCOUNT_SID, AUTH_ID);
	}
	
	public boolean send2FaCode(String mobilenumber, String twoFaCode) {
		
		Message.creator(new PhoneNumber(mobilenumber), new PhoneNumber("+14159655579"),
				"Your Two Factor Authentication code is: "+ twoFaCode).create();
		
		return true;
		
		
	}
}

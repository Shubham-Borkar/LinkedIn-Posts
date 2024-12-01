package com.app.otp;

import java.security.InvalidKeyException;
import java.security.Key;
import java.time.Instant;

import org.springframework.stereotype.Component;

import com.eatthepath.otp.TimeBasedOneTimePasswordGenerator;

@Component
public class TOTPGenerator extends TimeBasedOneTimePasswordGenerator {

	public TOTPGenerator() {
		super(TimeBasedOneTimePasswordGenerator.DEFAULT_TIME_STEP, 6,
				TimeBasedOneTimePasswordGenerator.TOTP_ALGORITHM_HMAC_SHA1);
	}

	public boolean isValidOTP(Key key, String otp) {

		Instant now = Instant.now();
		String generatedOtp;
		try {
			generatedOtp = this.generateOneTimePasswordString(key, now);
		} catch (InvalidKeyException e) {
			System.err.println("Invalid Key Exception");
			return false;
		}

		return otp.equals(generatedOtp);
	}
}

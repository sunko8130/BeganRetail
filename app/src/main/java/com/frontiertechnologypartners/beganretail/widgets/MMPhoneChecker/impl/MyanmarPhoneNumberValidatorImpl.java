package com.frontiertechnologypartners.beganretail.widgets.MMPhoneChecker.impl;


import com.frontiertechnologypartners.beganretail.widgets.MMPhoneChecker.MyanmarPhoneNumberValidator;
import com.frontiertechnologypartners.beganretail.widgets.MMPhoneChecker.Sanitizer;

public class MyanmarPhoneNumberValidatorImpl implements MyanmarPhoneNumberValidator {

	private static final String MM_PHONE = "^((09|\\+?950?9|\\+?95950?9)\\d{7,9})$";

	private Sanitizer sanitizer;

	public MyanmarPhoneNumberValidatorImpl(Sanitizer sanitizer) {
		this.sanitizer = sanitizer;
	}

	public boolean isMyanmarPhoneNumber(String phoneNumber, boolean sanitize) {
		if (sanitize) {
			phoneNumber = sanitizer.normalize(phoneNumber);
		}
		return (phoneNumber != null) && phoneNumber.matches(MM_PHONE);
	}

}

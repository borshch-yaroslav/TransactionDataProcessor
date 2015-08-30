package com.slavko.processor.tools;

import java.util.Currency;
import java.util.Set;

public class FieldValidator {

	public static boolean validateAccount(String account) {

		if (account instanceof String && account.matches("^[a-zA-Z0-9_]*$"))
			return true;

		else
			return false;
	}

	public static boolean validateDescription(String description) {

		if (description instanceof String && description.matches("^[a-zA-Z0-9_'.,\"*;:+\\!?]*$"))
			return true;
		else
			return false;
	}

	public static boolean validateCurrencyCode(String currencyCode) {
		
		if (Currency.getAvailableCurrencies().toString().contains(currencyCode)){
			return true;
		}
		else
			return false;
	}

	public static boolean validateAmount(Double amount) {
			return true;
	}
}

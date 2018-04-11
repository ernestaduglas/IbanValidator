package lt.seb.ernestaduglas.ibanValidator.service;

import java.math.BigInteger;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import lt.seb.ernestaduglas.ibanValidator.model.Iban;

@Service
public class Validator {
	public static final int IBANNUMBER_MIN_SIZE = 15;
	public static final int IBANNUMBER_MAX_SIZE = 34;
	public static final BigInteger CONTROL_NUMBER = new BigInteger("97");

	public String validate(Iban iban) {
		if (!checkIbanFormatIsCorrect(iban)) {
			return "invalid";
		} else if (iban.getNumericValue().mod(CONTROL_NUMBER).intValue() != 1) {
			return "invalid";
		} else {
			return "valid";
		}
	}
	
	public boolean checkIbanFormatIsCorrect(Iban iban) {
		if (iban.getLength() <= IBANNUMBER_MAX_SIZE && iban.getLength() >= IBANNUMBER_MIN_SIZE
				&& (Pattern.matches("[A-Z]{2}\\d{2}[A-Z0-9]*", iban.getValue()))) {
			return true;
		}
		return false;
	}

}

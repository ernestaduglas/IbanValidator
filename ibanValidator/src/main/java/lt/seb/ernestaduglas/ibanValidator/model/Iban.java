package lt.seb.ernestaduglas.ibanValidator.model;

import java.math.BigInteger;

public class Iban {
	private String value;

	public Iban(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}

	public BigInteger getNumericValue() {
		String newValue = value.substring(4) + value.substring(0, 4);
		StringBuilder numericValue = new StringBuilder();
		for (int i = 0; i < newValue.length(); i++) {
			numericValue.append(Character.getNumericValue(newValue.charAt(i)));
		}
		return new BigInteger(numericValue.toString());
	}
	
	public int getLength() {
		return value.length();
	}

}

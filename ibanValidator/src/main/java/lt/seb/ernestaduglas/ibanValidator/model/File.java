package lt.seb.ernestaduglas.ibanValidator.model;

import java.util.List;

public class File {
	private List<Iban> ibanList;
	
	public File(List<Iban> ibanList) {
		this.ibanList = ibanList;
	}

	public List<Iban> getIbanList() {
		return ibanList;
	}

}

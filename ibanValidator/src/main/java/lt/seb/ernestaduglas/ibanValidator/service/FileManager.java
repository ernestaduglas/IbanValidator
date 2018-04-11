package lt.seb.ernestaduglas.ibanValidator.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.seb.ernestaduglas.ibanValidator.model.File;
import lt.seb.ernestaduglas.ibanValidator.model.Iban;

@Service
public class FileManager {
	@Autowired
	private Validator validator;
	private static final String OUTPUT_FILE = System.getProperty("user.home") + "//validated.out";

	public File read(String filename) {
		List<Iban> ibanList = null;
		try {
			ibanList = Files.lines(Paths.get(filename)).map(iban -> new Iban(iban)).collect(Collectors.toList());
		} catch (IOException e) {
			e.getMessage();
		}
		return new File(ibanList);
	}

	public List<String> process(File file) {
		List<String> validatedIbans = file.getIbanList().stream()
				.map(iban -> iban.getValue() + ";" + validator.validate(iban)).collect(Collectors.toList());
		return validatedIbans;
	}

	public void write(List<String> validatedIbans) {
		try {
			Files.write(Paths.get(OUTPUT_FILE), validatedIbans);
		} catch (IOException e) {
			e.getMessage();
		}
	}

	public static String getOutputFile() {
		return OUTPUT_FILE;
	}

}

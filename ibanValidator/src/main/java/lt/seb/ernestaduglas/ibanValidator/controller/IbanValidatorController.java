package lt.seb.ernestaduglas.ibanValidator.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import lt.seb.ernestaduglas.ibanValidator.model.File;
import lt.seb.ernestaduglas.ibanValidator.model.Iban;
import lt.seb.ernestaduglas.ibanValidator.service.FileManager;
import lt.seb.ernestaduglas.ibanValidator.service.Validator;

@Controller
@RequestMapping(value = "/ibanvalidator")
public class IbanValidatorController {
	@Autowired
	private Validator validator;
	@Autowired
	private FileManager fileManager;
	private String message;

	@RequestMapping(method = RequestMethod.GET)
	public String getIndex(Model model) {
		model.addAttribute("message", message);
		return "index";
	}

	@RequestMapping(method = RequestMethod.POST, params="action=validateIban")
	public String validateSingleIban(String iban) {
		message = "IBAN is " + validator.validate(new Iban(iban));
		return "redirect:/ibanvalidator";

	}

	@RequestMapping(method = RequestMethod.POST, params="action=validateFile")
	public String validateFile(String filename) {
		File file = fileManager.read(filename);
		List<String> validatedIbans = fileManager.process(file);
		fileManager.write(validatedIbans);
		message = "Output file stored at: " + FileManager.getOutputFile();
		return "redirect:/ibanvalidator";
	}
}

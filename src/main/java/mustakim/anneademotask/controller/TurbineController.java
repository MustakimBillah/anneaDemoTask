package mustakim.anneademotask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import mustakim.anneademotask.service.TurbineService;

@RestController
public class TurbineController {

	@Autowired
	private TurbineService turbineService;
	
	@PostMapping("/importExcelData")
	public String importExcelData() {
		return turbineService.saveFromExcel();
	}
}

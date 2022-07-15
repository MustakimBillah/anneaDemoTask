package mustakim.anneademotask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import mustakim.anneademotask.service.TurbineService;

@RestController
public class TurbineController {

	@Autowired
	private TurbineService turbineService;
	
	@PostMapping("/importExcelData")
	public String importExcelData(@RequestParam("file") MultipartFile files) {
		return turbineService.saveFromExcel(files);
	}
}

package mustakim.anneademotask.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import mustakim.anneademotask.entity.Turbine;
import mustakim.anneademotask.service.TurbineService;

@RestController
public class TurbineController {

	@Autowired
	private TurbineService turbineService;
	
	@PostMapping("/importExcelData")
	public String importExcelData(@RequestParam("file") MultipartFile files) {
		return turbineService.saveFromExcel(files);
	}
	
	@GetMapping("/getTurbineData")
	public List<Turbine> getTurbineData(){
		return turbineService.getTurbineData();
	}
	@GetMapping("/getTurbineDataCount")
	public int getTurbineDataCount(){
		return turbineService.getTurbineData().size();
	}
}

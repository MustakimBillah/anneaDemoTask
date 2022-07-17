package mustakim.anneademotask.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("/search")
    public Map<String, Object> search(@RequestParam(value = "search") String search) {
        return turbineService.searchData(search);
    }

}

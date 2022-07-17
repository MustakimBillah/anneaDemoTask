package mustakim.anneademotask.controller;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import mustakim.anneademotask.entity.Turbine;
import mustakim.anneademotask.repository.TurbineRepository;
import mustakim.anneademotask.service.TurbineService;
import mustakim.anneademotask.specification.TurbineSpecificationsBuilder;

@RestController
public class TurbineController {

	@Autowired
	private TurbineService turbineService;
	@Autowired
	private TurbineRepository turbineRepository;
	
	@PostMapping("/importExcelData")
	public String importExcelData(@RequestParam("file") MultipartFile files) {
		return turbineService.saveFromExcel(files);
	}
	
	@GetMapping("/search")
    public Map<String, Object> search(@RequestParam(value = "search") String search) {
        return turbineService.searchData(search);
    }

}

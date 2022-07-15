package mustakim.anneademotask.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import mustakim.anneademotask.entity.Turbine;

public interface TurbineService {

	public String saveFromExcel(MultipartFile files);
	public List<Turbine> getTurbineData();
}

package mustakim.anneademotask.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface TurbineService {

	public String saveFromExcel(MultipartFile files);
	public Map<String, Object> searchData(String search);
}

package mustakim.anneademotask.service;

import org.springframework.web.multipart.MultipartFile;

public interface TurbineService {

	public String saveFromExcel(MultipartFile files);
}

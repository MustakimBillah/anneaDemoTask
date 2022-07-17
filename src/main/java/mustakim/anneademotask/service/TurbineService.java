package mustakim.anneademotask.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.web.multipart.MultipartFile;

import mustakim.anneademotask.entity.Turbine;

public interface TurbineService {

	public String saveFromExcel(MultipartFile files);
}

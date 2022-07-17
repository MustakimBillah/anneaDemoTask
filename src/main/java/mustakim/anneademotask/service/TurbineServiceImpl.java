package mustakim.anneademotask.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import mustakim.anneademotask.entity.Turbine;
import mustakim.anneademotask.repository.TurbineRepository;
import mustakim.anneademotask.specification.TurbineSpecificationsBuilder;

@Service
public class TurbineServiceImpl implements TurbineService {

	@Autowired
	private TurbineRepository turbineRepository;
	
	@Override
	public String saveFromExcel(MultipartFile files){
		
		try {
        List<Turbine> turbines = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream());
        // Read turbine data form excel file sheet1.
        XSSFSheet worksheet = workbook.getSheetAt(0);
        XSSFRow row;
        Turbine turbine=null;
        SimpleDateFormat datefmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String rowData=null;
        String[] convertedRow;
        Long serialNo=turbineRepository.getMaxSerialNo();
        
        for (int index = 1; index < worksheet.getPhysicalNumberOfRows(); index++) {
           
                row = worksheet.getRow(index);
                rowData=getCellValue(row, 0);
                convertedRow = rowData.split(",");
                turbine=new Turbine();
                turbine.setSerialNo(serialNo++);
                turbine.setTimeStamp(datefmt.parse(convertedRow[0]).getTime());
                turbine.setIndicator(Double.parseDouble(convertedRow[1]));
                turbine.setTurbineId(Integer.parseInt(convertedRow[2]));
                turbine.setVariable(Integer.parseInt(convertedRow[3]));
        
                turbines.add(turbine);
     
        }

        turbineRepository.saveAll(turbines);
        
		}catch(Exception ee) {
			System.out.println(ee);
		}
		return "saved successfully";
	}
	
    private String getCellValue(Row row, int cellNo) {
        DataFormatter formatter = new DataFormatter();
        Cell cell = row.getCell(cellNo);
        return formatter.formatCellValue(cell);
    }

	@Override
	public Map<String, Object> searchData(String search) {
		
		Map<String, Object> result = new HashedMap<String, Object>();
		
		try {
	        TurbineSpecificationsBuilder builder = new TurbineSpecificationsBuilder();
	        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\"([^\"]+)\")");
	        Matcher matcher = pattern.matcher(search + ",");
	  
	        SimpleDateFormat datefmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        Long timeInMilis=-1L;
	        Date date;
	        Integer pageNumber=0;
	        Integer pageSize=100;
	        String key,operator,value,parsedValue=null;
	        
	        while (matcher.find()) {
	        	key = matcher.group(1);
	        	operator = matcher.group(2);
	            value = matcher.group(3);
	            parsedValue = value.replace("\"", "");
	            
	            if(key.equalsIgnoreCase("pageNumber")) {
	            	pageNumber=Integer.parseInt(parsedValue);
	            	continue;
	            }
	            else if(key.equalsIgnoreCase("pageSize")) {
	            	pageSize=Integer.parseInt(parsedValue);
	            	continue;
	            }
	            
	            else if(key.equalsIgnoreCase("timeStamp")) {
	            	date =datefmt.parse(parsedValue);
	            	timeInMilis=date.getTime();
	            	builder.with(key, operator, timeInMilis);
	            } 
	            else builder.with(key, operator, parsedValue);
	        }
	        
	        Specification<Turbine> spec = builder.build();
	        Pageable pageParams = PageRequest.of(pageNumber-1,pageSize);
	        
	        
	        Page<Turbine> data = turbineRepository.findAll(spec,pageParams);
	     
	        result.put("content", data.getContent());
	        result.put("totalElements", data.getTotalElements());
	        result.put("totalPages", data.getTotalPages());
	        result.put("pageSize", data.getSize());
	        result.put("pageNumber", data.getNumber()+1);
	        result.put("numberOfElements", data.getNumberOfElements());
	        
		}catch(Exception ee) {
			
		}
	        return result;
	}



}

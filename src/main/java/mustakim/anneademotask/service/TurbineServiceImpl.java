package mustakim.anneademotask.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import mustakim.anneademotask.entity.Turbine;
import mustakim.anneademotask.repository.TurbineRepository;

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
        
        for (int index = 1; index < worksheet.getPhysicalNumberOfRows(); index++) {
           
                row = worksheet.getRow(index);
                rowData=getCellValue(row, 0);
                convertedRow = rowData.split(",");
                turbine=new Turbine();
                turbine.setTimeStamp(datefmt.parse(convertedRow[0]).getTime());
                turbine.setIndicator(Double.parseDouble(convertedRow[1]));
                turbine.setTurbine_id(Integer.parseInt(convertedRow[2]));
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

}

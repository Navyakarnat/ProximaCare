package business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	XSSFWorkbook wb;
	XSSFSheet sheet;

	public ExcelUtility(String Filepath) throws IOException {
		try {
			File path = new File(Filepath);
			FileInputStream fis = new FileInputStream(path);
			wb = new XSSFWorkbook(fis);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public String[][] ReadData(String ShName) {
		String[][] data = null;
		sheet = wb.getSheet(ShName);
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		for (int i = 1; i <= rowCount; i++) {
			data[i-1][0] = (String) (sheet.getRow(i).getCell(0).getRawValue().toString());
			data[i-1][1] = sheet.getRow(i).getCell(1).getStringCellValue();
		}
		return data;
	}

}

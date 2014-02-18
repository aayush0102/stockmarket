package testing;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.poi.sl.usermodel.Sheet;

import util.Xls_Reader;

import core.Page;

public class testExcel {
	
	//object for the xls_Reader
		public static Xls_Reader excel=new Xls_Reader(System.getProperty("user.dir")+"\\src\\main\\resources\\stockPrice.xlsx");
		
		
	
	public static String returnCurrentColumn()
	{
				
		DateFormat dateFormat = new SimpleDateFormat("MMM");
		DateFormat dateFormat1 = new SimpleDateFormat("dd");
		Date date = new Date();
		
		String colNum= dateFormat.format(date).toString().trim() + dateFormat1.format(date).toString().trim();
		return colNum;
	}
	
	
	
	public static void storeData(String sheet_name,String value)
	{

		//	System.out.println(returnCurrentColumn());
		excel.setCellData(sheet_name,returnCurrentColumn(),3,value);
				
			
	}
		
	
	public static void main(String[] args) {
		
		int rowNum=1;
		
		ArrayList arr=new ArrayList();
		while(!excel.getCellData("stocks","stocks",rowNum).trim().equals("")){
			
			//System.out.println(excel.getCellData("stocks","stocks",rowNum));
			arr.add(excel.getCellData("stocks","stocks",rowNum));
			rowNum++;
		
		
		}
	
		System.out.println(arr.get(1));
		
		System.out.println(arr.get(2));

	}}

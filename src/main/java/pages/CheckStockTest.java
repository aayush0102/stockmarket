package pages;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;


import util.SendMail;
import util.Xls_Reader;
import org.junit.*;


import com.sun.jna.platform.win32.WinUser.INPUT;

import core.Page;


@RunWith(Parameterized.class)
public class CheckStockTest {
	
	String stocks;
	
	//object for the xls_Reader
	public static Xls_Reader excel=new Xls_Reader(System.getProperty("user.dir")+"\\src\\main\\resources\\stockPrice.xlsx");
		
	//constructor
	public CheckStockTest(String stocks)
	{
		this.stocks=stocks;
				
	}
	
		
	@Parameters
	public static Collection<Object[]> getData(){
		
		//returns the data rows
		int records= excel.getRowCount("stocks") -1;
		//System.out.println(records);
		
		Object[][] data = new Object[records][1];
		ArrayList arr= CheckStock.returnStocks();
		
		for(int i=0;i<records;i++){
			data[i][0]=arr.get(i+1);
		}
		
		//data[0][0]=arr.get(1);
		//data[1][0]=arr.get(2);	
		
		System.out.println("folllowing stocks to monitor:-");
		for(int i=0;i<records;i++){
			System.out.println(data[i][0]);;
		}
		
		return Arrays.asList(data);
		
		
	} 
	
	
	
	@Test
public void testData() {
			
	InvokePage ch= new InvokePage();
		
			
		System.out.println("Updating   " + stocks);
		
		Hashtable table=CheckStockTest.returnValue(stocks);
		storeData(stocks,table);
		
				
	}	
	
	@AfterClass
	public static void sendMail(){
		
		//sending mail
				
		System.out.println("Sending mail");
		SendMail mail = new SendMail();
		try{
			mail.postMail(SendMail.emailList, SendMail.emailSubjectTxt, SendMail.emailMsgTxt, SendMail.emailFromAddress);
			}
		catch( Exception e)
		{
			System.out.println("error sending mail");
		}
					
			}
	
	
	/*reading the list of stocks to update */
	
		public static ArrayList returnStocks() {
		
		int rowNum=1;
		
		ArrayList arr=new ArrayList();
		while(!excel.getCellData("stocks","stocks",rowNum).trim().equals(""))
		{
					
			arr.add(excel.getCellData("stocks","stocks",rowNum));
			rowNum++;
		
		}
			
		return arr;

		}
		
	/* takes the stock name as input and returns it in the form of key/value pair */
	
		public static Hashtable<String, String> returnValue(String searchStock){
		
				
		Page.input(searchStock + "  stocks");
		
			try {
				Thread.sleep(5000);
				} 
			catch (InterruptedException e) 
			{
					e.printStackTrace();
			}
		
		Hashtable<String,String> table=new Hashtable<String,String>();
		
		String k= Page.driver.findElement(By.className("fmob_rd_it")).getText();
		 String key[] = k.split("\\r?\\n");
		 
		 String v= Page.driver.findElement(By.className("fmob_rd_itv")).getText();
		 String value[] = v.split("\\r?\\n");
		 
		 table.put(key[0],value[0]);
		 table.put(key[1],value[1]);
		 table.put(key[2],value[2]);
		 
		 System.out.println(table.get(key[0]));
		 System.out.println(table.get(key[1]));
		 System.out.println(table.get(key[2]));
		 
		 		 return table;
	}
	
			
	/* checks system date and returns the corresponding column in the excel*/	
	
		public static String returnCurrentColumn()
		{
			
					
			DateFormat dateFormat = new SimpleDateFormat("MMM");
			DateFormat dateFormat1 = new SimpleDateFormat("dd");
			Date date = new Date();
			
			String colNum= dateFormat.format(date).toString().trim() + dateFormat1.format(date).toString().trim();
			return colNum;
		}
		
	/* storing data in the excel */
		
		public static void storeData(String sheet_name, Hashtable table )
		{
								
			excel.setCellData(sheet_name,returnCurrentColumn(),3,table.get("Open").toString());
			excel.setCellData(sheet_name,returnCurrentColumn(),4,table.get("High").toString());
			excel.setCellData(sheet_name,returnCurrentColumn(),5,table.get("Low").toString());
			
			System.out.println("Data Stored!!");
											
		}
			
		
	

}

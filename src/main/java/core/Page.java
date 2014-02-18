package core;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;


import util.Xls_Reader;

public class Page {
	
	//making driver static so that only one reference of driver exists for one session.
	public static WebDriver driver;
	
	//properties files declaration.
	//config stores general project info
	public static Properties config = new Properties();
		
	//object stores the xpath
	public static Properties object = new Properties();
	
	
	
	
	public Page(){
		
		
		
		//loading the properties files
		
		try{
		
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\config.properties");
		config.load(fis);
		
		}
		catch(Throwable t){
			
		}
		
		try{
			
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\object.properties");
		object.load(fis);
			
		}
		catch(Throwable t){

		}
		
		//driver == null ensures that only one object exists. alternate way of using singleton.
		//checks for the browser type from the config.prop file and accordingly creates an object.
		
		if(driver==null){
			if(config.getProperty("browser").equals("firefox")){
				driver=new FirefoxDriver();
				
			}
			else if(config.getProperty("browser").equals("ie")){
				System.setProperty("webdriver.ie.driver","IEDriverServer.exe");
				driver=new InternetExplorerDriver();
				
			}
			else if(config.getProperty("browser").equals("chrome")){
				System.setProperty("webdriver.chrome.driver","chromedriver.exe");
				driver=new ChromeDriver();
			}
			
			
			//invokes the url
			driver.get(config.getProperty("testUrl"));
			
			//maximise the window
			driver.manage().window().maximize();
			
			//set the implicit waiting time
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
		
			
		}
	}
	public static void input(String value){
		
		try
		{
			
			
			driver.findElement(By.name("q")).sendKeys(value);
			Actions builder =new Actions(driver);
			builder.sendKeys(Keys.chord(Keys.CONTROL,"a")).perform();
			
			
		}
		catch (Throwable t)
		{
			
			//report error;
		}
		
		}
public static void click(){
		
		try
		{
			driver.findElement(By.name(("q"))).click();
			
		}
		catch (Throwable t)
		{
			//report error;
			
		}
		
		}	


}
	
	
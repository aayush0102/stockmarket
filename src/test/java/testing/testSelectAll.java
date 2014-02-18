package testing;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;


public class testSelectAll {
	
	public static void main(String[] args) {
		
		WebDriver driver=new FirefoxDriver();
		
		driver.get("http://www.google.com");

		driver.findElement(By.name("q")).sendKeys("aayush");
 try {
	Thread.sleep(1000);
} catch (InterruptedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
		
		Actions builder =new Actions(driver);
		builder.sendKeys(Keys.chord(Keys.CONTROL,"a")).perform();
		
		driver.findElement(By.name("q")).sendKeys("srivastava");
		
	}

}

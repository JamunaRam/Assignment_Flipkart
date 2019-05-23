package com.flipkart.login;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class OrderAnItem {

static WebDriver driver;
	
	@BeforeMethod
	
	public void login() throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\ls59\\Documents\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();		
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);		
		
	}
	
	@Test(priority=1)
	public void addAnItemToCart() throws InterruptedException, IOException 
	
	{
		driver.findElement(By.xpath("//button[@class='_2AkmmA _29YdH8']")).click();		
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//span[text()='Electronics']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()='OPPO']")).click();
		Thread.sleep(1000);
		
		WebElement search = driver.findElement(By.xpath("//input[@name ='q' and @class ='LM6RPg']"));		
		search.sendKeys("OPPO A3s (Purple, 16 GB)");
		search.sendKeys(Keys.ENTER);
		
        Thread.sleep(3000);		
	    WebElement item = driver.findElement(By.xpath("//a[text()='OPPO A3s (Purple, 16 GB)']"));	    
	    item.click();
		
		Set<String> list = driver.getWindowHandles();
		Iterator<String> it = list.iterator();
		String ParentWindow = it.next();
		driver.close();
		String childwindow = it.next();
		driver.switchTo().window(childwindow);	
		Thread.sleep(3000);
		
		
		driver.findElement(By.xpath("//button[text()='ADD TO CART']")).click();		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//img[@title='Flipkart']")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//span[text()='Cart']")).click();		
		Thread.sleep(2000);
		boolean itemverify = driver.findElement(By.xpath("//a[text()='OPPO A3s (Purple, 16 GB)']")).isDisplayed();
		System.out.println(itemverify);
		Thread.sleep(2000);	
		
		
		driver.findElement(By.xpath("//a[text()='Login & Signup']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@class = '_2zrpKA']")).sendKeys("8971148582");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@type = 'password']")).sendKeys("Ram@1712");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@class='_2AkmmA _1LctnI _7UHT_c']")).click();
		Thread.sleep(2000);		
		
		
		driver.findElement(By.xpath("//span[text()='Place Order']")).click();
		Thread.sleep(2000);
		
		
		
		/*WebElement orderSummary = driver.findElement(By.xpath("//input[@class='_35lzyU']"));
		orderSummary.click();
		Thread.sleep(2000);
		orderSummary.sendKeys("mailtojamuna1712@gmail.com");*/
		Thread.sleep(2000);
		
		
		driver.findElement(By.xpath("//button[text()='CONTINUE']")).click();
		Thread.sleep(2000);		
		JavascriptExecutor jse = (JavascriptExecutor)driver;		
	    jse.executeScript("window.scrollBy(0,700)");
	    
		driver.findElement(By.xpath("//input[@ id = 'NET_OPTIONS' and @name ='OTHERS']//parent::label[@for ='NET_OPTIONS']")).click();
		Thread.sleep(2000);
		
		Select bank = new Select(driver.findElement(By.xpath("//select[@class='_1CV081']")));
		bank.selectByValue("CORPORATION");
		takeScreenpShot("Screenshot");
	}
	
	 
	
	public static void takeScreenpShot(String filename) throws IOException {

	      File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);    
	      
	      FileUtils.copyFile(file, new File( "C:\\Projects\\rapl-web-selenium\\Flipkart\\src\\main\\java\\com\\flipkart\\login"+ filename +".jpg"));    
	}
	
	@AfterMethod
	public void logout() {			
		driver.quit();
		System.out.println(" Success");
		
	}	
}

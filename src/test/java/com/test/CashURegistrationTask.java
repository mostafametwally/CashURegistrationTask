package com.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CashURegistrationTask {
	WebDriver driver;

	@BeforeMethod
	 public void setupMethod() {
		System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.navigate().to("https://www.gocardi.com/register");
		
		//this block to switch from Arabic page to English Page.
		try {
			driver.findElement(By.linkText("English")).click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@AfterMethod
	public void tearDown() {
		//driver.quit();
	}
	
//	Note:
//	This test Case may fails due to google reCAPTCHA 
//	if you want to pass it,
//	please disable the reCAPTCHA and run it again.
	
	@Test
	public void registerNewUser() {
		
		//filling the fields and check boxes 
		driver.findElement(By.xpath("//input[@placeholder=\"First Name\"]")).sendKeys("Mostafa");
		driver.findElement(By.xpath("//input[@placeholder=\"Last Name\"]")).sendKeys("Metwally");
		driver.findElement(By.xpath("//input[@placeholder=\"Email Address\"]")).sendKeys("mostafa.ibrahim@gmail.com");
		driver.findElement(By.xpath("//input[@placeholder=\"Password\"]")).sendKeys("Mm123456789");
		driver.findElement(By.xpath("//label[@for=\"checkbox1\"]")).click();
		driver.findElement(By.xpath("//label[@for=\"checkbox2\"]")).click();
		
		// swtching to the reCAPTCHA frame and set the check box 
		
//		driver.switchTo().frame("a-z5mqq64wnsdn");
//		driver.findElement(By.xpath("//div[@id=\"rc-anchor-container\"]")).click();
//		driver.switchTo().defaultContent();
//		driver.findElement(By.xpath("//button[@class=\"btn btn-primary btn-block register\"]")).click();

		
		//After registration a text will appears to notify that the Registration completed successfully! 
		String ActualMsg= driver.findElement(By.xpath("//div[@class=\"row text-center col-12 col-lg-6 mx-auto\"]")).getText();
		String ExpectedMsg= "Registration completed successfully! Please check your email to complete your email address verification process.";
		Assert.assertEquals(ActualMsg, ExpectedMsg);
	}
	
	
}


package webdriver;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Alert {
	WebDriver driver;
	private String projectPath = System.getProperty("user.dir");
	private String authenticateChrome = projectPath+"\\autoITScript\\authen_chrome.exe";
	private String authenticateFireFox =projectPath+"\\autoITScript\\authen_firefox.exe" ;
	JavascriptExecutor jsExcutor;
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		jsExcutor = (JavascriptExecutor )driver;
	}
	public void TC_01_Alert() {
		driver.get("https://automationfc.github.io/basic-form/");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		
		Alert alert = driver.switchTo().alert();
		assertEquals(alert.getText(), "I am a JS Alert");
		
		alert.accept();
		assertEquals(driver.findElement(By.id("result")).getText(), "You clicked an alert successfully");
		
	}
	
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		Utils.sleep(2);
		
		Alert alert = driver.switchTo().alert();
		assertEquals(alert.getText(), "I am a JS Confirm");
		
		alert.dismiss();
		assertEquals(driver.findElement(By.id("result")).getText(), "You clicked: Cancel");
		
	}
	
	public void TC_03_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		Utils.sleep(2);
		
		Alert alert = driver.switchTo().alert();
		assertEquals(alert.getText(), "I am a JS prompt");
		
		alert.sendKeys("Automation FC");
		alert.accept();
		assertEquals(driver.findElement(By.id("result")).getText(), "You entered: Automation FC");
		
	}
	
	public void TC_04_Authenticate_Alert_I() {
		String uname ="admin";
		String pword ="admin";
		driver.get(String.format("http://%s:%s@the-internet.herokuapp.com/basic_auth", uname,pword));
		Utils.sleep(2);
		
		assertEquals(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed(), true);
	}
	public void TC_05_Authenticate_Alert_II() {
		String uname ="admin";
		String pword ="admin";
		driver.get("http://the-internet.herokuapp.com");
		String basicLink = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		
		driver.get(Utils.getAuthenticateLink(basicLink, uname, pword));
		Utils.sleep(2);
		
		assertEquals(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed(), true);
	}
	@Test
	public void TC_06_Authenticate_Alert_AutoIT() throws IOException {
		String uname ="admin";
		String pword ="admin";
		driver.get("http://the-internet.herokuapp.com");
		
		if(driver.toString().contains("Firefox")) {
			Runtime.getRuntime().exec(new String[] {authenticateFireFox,uname,pword});
		} else {
			Runtime.getRuntime().exec(new String[] {authenticateChrome,uname,pword});	
		}
		
		driver.findElement(By.xpath("//a[text()='Basic Auth']")).click();
		Utils.sleep(8);
		
		assertEquals(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed(), true);
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.close();
	}
	


}

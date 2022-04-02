package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Default_Dropdown {
	WebDriver driver;
	private String projectPath = System.getProperty("user.dir");
	private Select select ;
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		jsExecutor =( JavascriptExecutor ) driver;
		explicitWait = new WebDriverWait(driver, 30);
	}
	@Test
	public void TC_01_Dropdown_Is_Support_multi() {
		driver.get("https://www.rode.com/wheretobuy");
		select = new Select(driver.findElement(By.id("country")));
		
		//Check drop drown is not support multiple
		Assert.assertFalse(select.isMultiple());
		select.selectByValue("Vietnam");
		
		// Choose Item VietNam
		Assert.assertEquals(select.getFirstSelectedOption().getAttribute("value"), "Vietnam");
		
		List<WebElement> listResult = driver.findElements(By.xpath("//div/h3[text()='Dealers']/following-sibling::div/div"));
		//Check result have 32 item
		Assert.assertEquals(listResult.size(), 32);
		
		//Print Locator Name
		for(WebElement i : listResult) {
			System.out.println(i.findElement(By.cssSelector("h4")).getText());
		}
	}
	
	@Test
	public void TC_02_Dropdown_List() {
		driver.get("https://demo.nopcommerce.com/register");
		// Click header Register
		driver.findElement(By.cssSelector(".header-links .ico-register")).click();
		
		String email = "ngochoa" +getRandomNumber()+"@gmail.com";
		//input require data
		driver.findElement(By.id("gender-female")).click();
		driver.findElement(By.id("FirstName")).sendKeys("Hoa");
		driver.findElement(By.id("LastName")).sendKeys("Vo Thi Ngoc");
		driver.findElement(By.id("Email")).sendKeys(email);
		driver.findElement(By.id("Company")).sendKeys("TTL");
		driver.findElement(By.id("Password")).sendKeys("Abcd1234");
		driver.findElement(By.id("ConfirmPassword")).sendKeys("Abcd1234");
		
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		select.selectByValue("26");
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		select.selectByValue("3");	
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		select.selectByValue("1991");
		driver.findElement(By.id("register-button")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
		driver.findElement(By.cssSelector(".ico-account")).click();
		
		sleep(3);
		// Verify
		Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), "Hoa");
		Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), "Vo Thi Ngoc");
		Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"), email);
		Assert.assertEquals(driver.findElement(By.id("Company")).getAttribute("value"), "TTL");
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.close();
	}
	
	public int getRandomNumber() {
		Random r = new Random();
		return r.nextInt();
		
	}
	
	public void sleep(int second)   {
		try {
			Thread.sleep(second*1000);	
		}catch(InterruptedException ex) {
			System.out.println(ex.getMessage());
		}
		
	}
}

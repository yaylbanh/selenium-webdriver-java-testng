package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Exercise_Browser {
	WebDriver driver;
	private String projectPath = System.getProperty("user.dir");
	// url
	private String urlFirstPage = "http://live.techpanda.org/";
	private String urlRegisterPage = "http://live.techpanda.org/index.php/customer/account/create/";
	private String urlLoginPage = "http://live.techpanda.org/index.php/customer/account/login/";
	
	// title
	private String titleLoginPage = "Customer Login";
	private String titleRegisterPage = "Create New Customer Account";
	
	//element 
	private String eMyAccount ="div.footer a[title='My Account']";
	private String eRegister ="form#login-form a[title='Create an Account']";
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(urlFirstPage);
	}

	@Test
	public void TC_01_ValidateCurrentUrl() throws InterruptedException {
		driver.get(urlFirstPage);
		driver.findElement(By.cssSelector(eMyAccount)).click();
		Assert.assertEquals(driver.getCurrentUrl(),urlLoginPage);

		driver.findElement(By.cssSelector(eRegister)).click();
		Assert.assertEquals(driver.getCurrentUrl(), urlRegisterPage);

	}

	@Test
	public void TC_02_ValidatePageTitle() throws InterruptedException {
		driver.get(urlFirstPage);
		driver.findElement(By.cssSelector(eMyAccount)).click();
		Assert.assertEquals(driver.getTitle(), titleLoginPage);

		driver.findElement(By.cssSelector(eRegister)).click();
		Assert.assertEquals(driver.getTitle(), titleRegisterPage);

	}
	@Test
	public void TC_03_ValidateNavigate(){
		driver.get(urlFirstPage);
		driver.findElement(By.cssSelector(eMyAccount)).click();
		driver.findElement(By.cssSelector(eRegister)).click();
		Assert.assertEquals(driver.getCurrentUrl(),urlRegisterPage);
		driver.navigate().back();
		Assert.assertEquals(driver.getCurrentUrl(),urlLoginPage);
		driver.navigate().forward();
		Assert.assertEquals(driver.getTitle(), titleRegisterPage);
		
	}
	
	@Test
	public void TC_04_GetPageSourceCode(){
		driver.get(urlFirstPage);
		driver.findElement(By.cssSelector(eMyAccount)).click();
		Assert.assertEquals(driver.getPageSource().contains("Login or Create an Account"), true);

		driver.findElement(By.cssSelector(eRegister)).click();
		Assert.assertEquals(driver.getPageSource().contains("Create an Account"), true);
	}

	@AfterClass
	public void afterClass() {
		 driver.quit();
	}
}

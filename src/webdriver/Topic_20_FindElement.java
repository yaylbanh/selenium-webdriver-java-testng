package webdriver;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_FindElement {
	WebDriver driver;
	private String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		//System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver_v102.exe");
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver_v103-53.exe");
		driver = new ChromeDriver();
	}

	@Test
	public void TC_01_NotEnough_time() {
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElements(By.cssSelector("#start>button")).get(0).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
	}

	@Test
	public void TC_02_Enough_time() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElements(By.cssSelector("#start>button")).get(0).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
	}

	
	@Test
	public void TC_02_More_time() {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElements(By.cssSelector("#start>button")).get(0).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
	}
	@AfterClass
	public void afterClass() {
		driver.close();
	}

}

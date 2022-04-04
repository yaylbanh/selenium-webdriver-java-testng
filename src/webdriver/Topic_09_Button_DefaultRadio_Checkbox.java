package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Button_DefaultRadio_Checkbox {
	WebDriver driver;
	private String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExcutor;
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		jsExcutor = (JavascriptExecutor )driver;
	}
	
	public void TC_01_button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		
		driver.findElement(By.cssSelector("ul#popup-login-tab_list>li.popup-login-tab-item")).click();
		Assert.assertFalse(driver.findElement(By.cssSelector("button.fhs-btn-login")).isEnabled());
		driver.findElement(By.id("login_username")).sendKeys("ngochoa2603@gmail.com");
		driver.findElement(By.id("login_password")).sendKeys("ngochoa2603");
		
		Assert.assertTrue(driver.findElement(By.cssSelector("button.fhs-btn-login")).isEnabled());
		//verify button color = red
		String loginButtonBackgrountColor = driver.findElement(By.cssSelector("button.fhs-btn-login")).getCssValue("background-color");
		System.out.println("RGB color = " +loginButtonBackgrountColor);
		
		Assert.assertEquals(Color.fromString(loginButtonBackgrountColor).asRgb(), "rgb(201, 33, 39)");
		//Convert to Hexa 
		String loginButtonBackgrountColorHexa  = Color.fromString(loginButtonBackgrountColor).asHex();
		Assert.assertEquals(loginButtonBackgrountColorHexa.toUpperCase(), "#C92127");
		
		driver.navigate().refresh();
		driver.findElement(By.cssSelector("ul#popup-login-tab_list>li.popup-login-tab-item")).click();
		
		//Remove attributor
		jsExcutor.executeScript("arguments[0].removeAttribute('disabled');", driver.findElement(By.cssSelector("button.fhs-btn-login")));
		Assert.assertTrue(driver.findElement(By.cssSelector("button.fhs-btn-login")).isEnabled());
		
		
		driver.findElement(By.cssSelector("button.fhs-btn-login")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='Số điện thoại/Email']/following-sibling::div[@class='fhs-input-alert']")).getText(),"Thông tin này không thể để trống");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='Mật khẩu']/following-sibling::div[@class='fhs-input-alert']")).getText(),"Thông tin này không thể để trống");
	}
	
	public void TC_02_DefaultRadio() {
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		
		By onePertrolRadio = By.xpath("//label[text()='1.4 Petrol, 92kW']/preceding-sibling::input");
		By twoPertrolRadio = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");
		By threePertrolRadio = By.xpath("//label[text()='3.6 Petrol, 191kW']/preceding-sibling::input");
		
		//Select
		Assert.assertFalse(driver.findElement(twoPertrolRadio).isSelected());
		driver.findElement(twoPertrolRadio).click();
		sleep(2);
		Assert.assertTrue(driver.findElement(twoPertrolRadio).isSelected());
		
		//deselected
		driver.findElement(onePertrolRadio).click();
		sleep(2);
		Assert.assertFalse(driver.findElement(twoPertrolRadio).isSelected());
		//disable
		Assert.assertFalse(driver.findElement(threePertrolRadio).isEnabled());
		
	}
	
	public void TC_03_checkbox() {
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
		By luggageCB = By.xpath("//label[text()='Luggage compartment cover']/preceding-sibling::input");
		By heatedCB = By.xpath("//label[text()='Heated front and rear seats']/preceding-sibling::input");
		By dualCB = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input");
		By leatherCB = By.xpath("//label[text()='Leather trim']/preceding-sibling::input");
		
		if(!driver.findElement(luggageCB).isSelected()) {
			driver.findElement(luggageCB).click();
		}
		
		if(!driver.findElement(heatedCB).isSelected()) {
			driver.findElement(heatedCB).click();
		}
		//selected
		Assert.assertTrue(driver.findElement(luggageCB).isSelected());
		Assert.assertTrue(driver.findElement(heatedCB).isSelected());
		
		//disable
		Assert.assertFalse(driver.findElement(leatherCB).isEnabled());
		
		
		if(driver.findElement(luggageCB).isSelected()) {
			driver.findElement(luggageCB).click();
		}
		
		if(driver.findElement(heatedCB).isSelected()) {
			driver.findElement(heatedCB).click();
		}
		//De-selected
		Assert.assertFalse(driver.findElement(luggageCB).isSelected());
		Assert.assertFalse(driver.findElement(heatedCB).isSelected());
		
	}
	@Test
	public void TC_04_Multiple_Checkbox() {
		driver.get("https:automationfc.github.io/multiple-fields/");
		List<WebElement> checkboxs = driver.findElements(By.cssSelector("input[type='checkbox']"));
		
		System.out.println("checkbox size =" + checkboxs.size());
		for(WebElement e: checkboxs) {
			e.click();
			sleep(1);
		}
	}
	public void sleep(int second)   {
		try {
			Thread.sleep(second*1000);	
		}catch(InterruptedException ex) {
			System.out.println(ex.getMessage());
		}
		
	}

}

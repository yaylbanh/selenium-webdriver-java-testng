package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Custom_Dropdown {
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
	public void TC_01_Jquery() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		//Step 1: click vào element cho nó xổ hết ra 
		//"span#number-button>span.ui-selectmenu-icon"
		//
		selectItemInCustomDropdownList("span#number-button>span.ui-selectmenu-icon", "ul#number-menu div", "5");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(),"5");
		
		selectItemInCustomDropdownList("span#number-button>span.ui-selectmenu-icon", "ul#number-menu div", "15");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "15");
		
		selectItemInCustomDropdownList("span#number-button>span.ui-selectmenu-icon", "ul#number-menu div", "19");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "19");
		
		selectItemInCustomDropdownList("span#number-button>span.ui-selectmenu-icon", "ul#number-menu div", "3");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(),"3");
		
	}
	
	public void TC_02_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		
		selectItemInCustomDropdownList("div.dropdown", "div.item>span", "Elliot Fu");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.dropdown>div")).getText(),"Elliot Fu");
		
		selectItemInCustomDropdownList("div.dropdown", "div.item>span", "Stevie Feliciano");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.dropdown>div")).getText(),"Stevie Feliciano");
	}
	
	public void TC_03_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		
		selectItemInCustomDropdownList("div.btn-group", "ul.dropdown-menu>li>a", "Third Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.btn-group>li")).getText(),"Third Option");
	}
	
	@Test	
	public void TC_04_Angula() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		
		selectItemInCustomDropdownList("ng-select[bindvalue='provinceCode'] span.ng-arrow-wrapper", "div[role='option']>span.ng-option-label", "Thành phố Hải Phòng");
		selectItemInCustomDropdownList("ng-select[formcontrolname=provinceCode]", "div[role='option']>span", "Tỉnh Ninh Bình");
		Assert.assertEquals(driver.findElement(By.cssSelector("ng-select[bindvalue='provinceCode'] div.ng-value>span.ng-value-label")).getText(),"Tỉnh Ninh Bình");
	}
	public void selectItemInCustomDropdownList(String parentLocator, String childLocator, String expectedTextItem) {
		driver.findElement(By.cssSelector(parentLocator)).click();
		sleep(3);
		//Step 2 chờ cho các item load ra hết thành công
		// Lưu ý: 1. Locator chứa hết tất cả các item
		//        2. Locator phải đến note chứa text
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
		
		List<WebElement> allItems= driver.findElements(By.cssSelector(childLocator));
		
		for(WebElement e: allItems) {
			String actualText= e.getText();
			System.out.println("Actual Text =" + actualText);
			if(actualText.equals(expectedTextItem)) {
				jsExecutor.executeScript("arguments[0].scrollIntoView(true)", e);
				e.click(); break;
			}
		}
	}
	
	@AfterClass
	public void afterClass() {
//		driver.close();
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

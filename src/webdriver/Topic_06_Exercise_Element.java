
package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Exercise_Element {
	WebDriver driver;
	private String projectPath = System.getProperty("user.dir");
	private String urlFirstPage = "https://automationfc.github.io/basic-form/index.html";

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_ValidateIsDisplay() {
		driver.get(urlFirstPage);

		WebElement tbEmail = driver.findElement(By.id("mail"));
		System.out.println("Email display = " + tbEmail.isDisplayed());
		Assert.assertEquals(tbEmail.isDisplayed(), true);
		tbEmail.sendKeys("ngochoa2603@gmail.com");

		WebElement radioAgeUnder18 = driver.findElement(By.id("under_18"));
		System.out.println("Radio under 18 display = " + radioAgeUnder18.isDisplayed());
		Assert.assertEquals(radioAgeUnder18.isDisplayed(), true);
		radioAgeUnder18.click();

		WebElement tbEdu = driver.findElement(By.id("edu"));
		System.out.println("Education display = " + tbEdu.isDisplayed());
		Assert.assertEquals(tbEdu.isDisplayed(), true);
		tbEdu.sendKeys("Information Technology University");

		WebElement h5User5 = driver.findElement(By.xpath("//h5[text()='Name: User5']"));
		System.out.println("User 5 display = " + h5User5.isDisplayed());
		Assert.assertEquals(h5User5.isDisplayed(), false);
	}

	@Test
	public void TC_02_ValidateIsEnable() {
		driver.get(urlFirstPage);

		WebElement tbEmail = driver.findElement(By.id("mail"));
		System.out.println("Email isEnabled = " + tbEmail.isEnabled());
		Assert.assertEquals(tbEmail.isEnabled(), true);

		WebElement radioAgeUnder18 = driver.findElement(By.id("under_18"));
		System.out.println("Radio under 18 isEnabled = " + radioAgeUnder18.isEnabled());
		Assert.assertEquals(radioAgeUnder18.isEnabled(), true);

		WebElement tbEdu = driver.findElement(By.id("edu"));
		System.out.println("Education isEnabled = " + tbEdu.isEnabled());
		Assert.assertEquals(tbEdu.isEnabled(), true);
		
		WebElement secJob1 = driver.findElement(By.id("job1"));
		System.out.println("Job Role 1 isEnabled = " + secJob1.isEnabled());
		Assert.assertEquals(secJob1.isEnabled(), true);

		WebElement secJob2 = driver.findElement(By.id("job2"));
		System.out.println("Job Role 2 isEnabled = " + secJob2.isEnabled());
		Assert.assertEquals(secJob2.isEnabled(), true);
		
		WebElement cbDevelopment = driver.findElement(By.id("development"));
		System.out.println("development isEnabled = " + cbDevelopment.isEnabled());
		Assert.assertEquals(cbDevelopment.isEnabled(), true);
		
		WebElement slider1 = driver.findElement(By.id("slider-1"));
		System.out.println(" slider1 isEnabled = " +  slider1.isEnabled());
		Assert.assertEquals( slider1.isEnabled(), true);
		
		// check element not enable
		WebElement tbPwd = driver.findElement(By.id("disable_password"));
		System.out.println(" password isEnabled = " +  tbPwd.isEnabled());
		Assert.assertEquals( tbPwd.isEnabled(), false);
		
		WebElement radDisable = driver.findElement(By.id("radio-disabled"));
		System.out.println("radio disabled isEnabled = " +  radDisable.isEnabled());
		Assert.assertEquals( radDisable.isEnabled(), false);
		
		WebElement textareaBio = driver.findElement(By.id("bio"));
		System.out.println("radio disabled isEnabled = " +  textareaBio.isEnabled());
		Assert.assertEquals( textareaBio.isEnabled(), false);
		
		WebElement secJob3 = driver.findElement(By.id("job3"));
		System.out.println("Job role isEnabled = " +  secJob3.isEnabled());
		Assert.assertEquals( secJob3.isEnabled(), false);
		
		WebElement cbInterest = driver.findElement(By.id("check-disbaled"));
		System.out.println("Checkbox Interest isEnabled = " +  cbInterest.isEnabled());
		Assert.assertEquals( cbInterest.isEnabled(), false);
		
		WebElement slide2 = driver.findElement(By.id("slider-2"));
		System.out.println("Slide2 isEnabled = " +  slide2.isEnabled());
		Assert.assertEquals( slide2.isEnabled(), false);
	}
	
	@Test
	public void TC_03_ValidateIsSelected() {
		driver.get(urlFirstPage);
		
		WebElement radioAgeUnder18 = driver.findElement(By.id("under_18"));
		radioAgeUnder18.click();
		System.out.println("Radio under 18 isSelected = " + radioAgeUnder18.isSelected());
		Assert.assertEquals(radioAgeUnder18.isSelected(), true);
		WebElement radioAgeOver18 = driver.findElement(By.id("over_18"));
		radioAgeOver18.click();
		System.out.println("Radio under 18 isSelected = " + radioAgeUnder18.isSelected());
		Assert.assertEquals(radioAgeUnder18.isSelected(), false);
	}

	@Test
	public void TC_04_RegisterAtMailChimp() {
		driver.get("https://login.mailchimp.com/signup/");
		WebElement txtEmail = driver.findElement(By.id("email"));
		WebElement txtUserName = driver.findElement(By.id("new_username"));
		WebElement txtPwd = driver.findElement(By.id("new_password"));
		WebElement btnSignUp = driver.findElement(By.id("create-account"));
		
		txtEmail.sendKeys("ngochoa2603@gmail.com");
		txtUserName.sendKeys("ngochoa2603");
		
		WebElement verifyNumber = driver.findElement(By.cssSelector("ul>li.number-char"));
		WebElement verifyLowercase = driver.findElement(By.cssSelector("ul>li.lowercase-char"));
		WebElement verifyUppercase = driver.findElement(By.cssSelector("ul>li.uppercase-char"));
		WebElement verifySpecial = driver.findElement(By.cssSelector("ul>li.special-char"));
		WebElement verify8Char = driver.findElement(By.cssSelector("ul>li[class$='8-char']"));
		
		System.out.println("button sign up is enable = " + btnSignUp.isEnabled());
		Assert.assertEquals(btnSignUp.isEnabled(), false);
		
		txtPwd.sendKeys("1");
		Assert.assertEquals(verifyNumber.getAttribute("class").contains("completed"), true);
		txtPwd.sendKeys("C");
		Assert.assertEquals(verifyUppercase.getAttribute("class").contains("completed"), true);
		txtPwd.sendKeys("c");
		Assert.assertEquals(verifyLowercase.getAttribute("class").contains("completed"), true);
		txtPwd.sendKeys("@@");
		Assert.assertEquals(verifySpecial.getAttribute("class").contains("completed"), true);
		txtPwd.sendKeys("1234");
		Assert.assertEquals(verify8Char.getAttribute("class").contains("completed"), true);
		System.out.println("button sign up is enable = " + btnSignUp.isEnabled());
		Assert.assertEquals(btnSignUp.isEnabled(), true);
		
	}
	@AfterClass
	public void afterClass() {
//		driver.close();
	}

}

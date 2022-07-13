package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class Utils {
	
	public void clickByJavascript(By by,JavascriptExecutor jsExcutor,WebDriver driver) {
		jsExcutor.executeScript("arguments[0].click()", driver.findElement(by));
	}
	
	public static void sleepToSecond(int second)   {
		try {
			Thread.sleep(second*1000);	
		}catch(InterruptedException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public static String getAuthenticateLink(String link,String uname,String pword)   {
		String[] links = link.split("//");
		return String.format("%s//%s:%s@%s",links[0],uname,pword,links[1]);
	}
}

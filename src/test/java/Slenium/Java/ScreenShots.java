package Slenium.Java;

import java.io.File;
import java.io.FileOutputStream;
import java.time.Duration;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ScreenShots {
	WebDriver driver;
	String url="https://www.instagram.com/";
	@BeforeMethod
	public void setup() {
		driver = new ChromeDriver();
		WebDriverManager.chromedriver().setup();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
  @Test
  public void mainTest() throws Exception {
  TakesScreenshot takess = (TakesScreenshot) driver;
  File Source = takess.getScreenshotAs(OutputType.FILE);
  File dest = new File("./Resources/insta.jpg");
  FileUtils.copyFile(Source, dest);
  driver.get("https://github.com/");
  byte[] souce1=takess.getScreenshotAs(OutputType.BYTES);
  File dest1 = new File("./Resources/git.jpg");
  FileOutputStream fis = new FileOutputStream(dest1);
  fis.write(souce1);
  fis.close();
  driver.get("https://www.googleadservices.com/");
  String base64 = takess.getScreenshotAs(OutputType.BASE64);
  byte[] byt = Base64.getDecoder().decode(base64);
  File dest2 = new File("./Resources/google.jpg");
  FileOutputStream fis1 = new FileOutputStream(dest2);
  fis1.write(byt);
  fis1.close();
  driver.get("https://www.goto.com/meeting");
  Thread.sleep(5000);
  WebElement logo = driver.findElement(By.xpath("//a[text()='+91 1800 269 9248']"));
  
  File source4 =logo.getScreenshotAs(OutputType.FILE);
  File dest4=new File("./Resources/GOTO.png");
  FileUtils.copyFile(source4, dest4);
}
  @AfterMethod
	public void teardown() {

	  driver.quit();
  }
}
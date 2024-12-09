package Test;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class test_doithongtinnguoidung {
  public WebDriver driver = null;
  @BeforeTest
  public void beforeTest() throws InterruptedException{
	  driver = new ChromeDriver();
      driver.get("https://cinestream-kodxowwy5-soras-projects-05c822a4.vercel.app/");
      driver.manage().window().maximize();
      driver.findElement(By.cssSelector("a[href='/auth']")).click();
	  Thread.sleep(5000);
	  WebElement name = driver.findElement(By.id("email"));
      name.sendKeys("vandong1@gmail.com");
      
      WebElement pass = driver.findElement(By.xpath("//input[@id='password']"));
      pass.sendKeys("123321123");
      driver.findElement(By.xpath("//button[contains(text(),'Đăng nhập')]")).click();
      Thread.sleep(5000);
      WebElement profiles = driver.findElement(By.xpath("//figure[contains(@class,'')]"));
      profiles.click();
      Thread.sleep(500);
      WebElement profiless = driver.findElement(By.xpath(" li:nth-child(1)"));
      profiless.click();
      
  }
  @Test
  public void test_case_1() {
  }
  
  @AfterTest
  public void afterTest() throws InterruptedException{
	  Thread.sleep(8000);
	  driver.quit();
  }

}

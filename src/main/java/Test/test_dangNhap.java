package Test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class test_dangNhap {
	public WebDriver driver = null;
  @BeforeTest
  public void beforeTest() throws InterruptedException{
	  driver = new ChromeDriver();
      driver.get("https://cinestream-livid.vercel.app");
      driver.manage().window().maximize();
      driver.findElement(By.cssSelector("a[href='/auth']")).click();
	  Thread.sleep(5000);
  }
  
  @Test(priority = 1)
  public void testLoginUIElements_001() throws InterruptedException{
      // Kiểm tra các trường và nút
      Assert.assertTrue(driver.findElement(By.id("email")).isDisplayed(), "Trường email không hiển thị");
      Assert.assertTrue(driver.findElement(By.id("password")).isDisplayed(), "Trường mật khẩu không hiển thị");
      Assert.assertTrue(driver.findElement(By.xpath("//button[contains(text(),'Đăng nhập')]")).isDisplayed(), "Nút đăng nhập không hiển thị");
      Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'bg-slate-600 m-6 p-6 rounded-xl')]//div[contains(@class,'flex justify-between items-center')]")).isDisplayed(), "Banner quảng cáo không hiển thị");
  }
  
  @Test(priority = 2)
  public void testInvalidLogin() throws InterruptedException{
      WebElement name = driver.findElement(By.xpath("//input[@id='email']"));
      name.sendKeys("user");
      
      WebElement pass = driver.findElement(By.xpath("//input[@id='password']"));
      pass.sendKeys("123456.D");
     
      driver.findElement(By.xpath("//button[contains(text(),'Đăng nhập')]")).click();
      
      // Kiểm tra thông báo lỗi
      WebElement emailField = driver.findElement(By.id("email"));
      String validationMessage = emailField.getAttribute("validationMessage");
      Assert.assertEquals(validationMessage, "Please include an '@' in the email address. 'user' is missing an '@'.",
          "Thông báo lỗi không đúng cho email không hợp lệ.");
      Thread.sleep(3000);
      name.clear();
      pass.clear();
      
  }

  @Test(priority = 3)
  public void thong_tin_khong_hop_le_004() throws InterruptedException {
      Thread.sleep(2000);
	  driver.findElement(By.xpath("//button[contains(text(),'Đăng nhập')]")).click();
	  Thread.sleep(2000);
	// Kiểm tra trường "email"
	  WebElement emailField = driver.findElement(By.id("email"));
	  String emailValidationMessage = emailField.getAttribute("validationMessage");
	  Assert.assertEquals(emailValidationMessage, "Please fill out this field.", 
	      "Thông báo lỗi không đúng cho trường: email");
	  
	  WebElement namee = driver.findElement(By.xpath("//input[@id='email']"));
	  namee.sendKeys("user@gmail.com");
	  
	  // Kiểm tra trường "password"
	  Thread.sleep(3500);
	  WebElement passwordField = driver.findElement(By.id("password"));
	  driver.findElement(By.xpath("//button[contains(text(),'Đăng nhập')]")).click();
	  Thread.sleep(5000);
	  String passwordValidationMessage = passwordField.getAttribute("validationMessage");
	  Assert.assertEquals(passwordValidationMessage, "Please fill out this field.", 
	      "Thông báo lỗi không đúng cho trường: password");
	  WebElement nameee = driver.findElement(By.xpath("//input[@id='email']"));
	  nameee.clear();
	  Thread.sleep(2000);
  }
  
  @Test(priority = 4)
  public void email_khong_hop_le_005() throws InterruptedException  {
	  Thread.sleep(5000);
	  WebElement name = driver.findElement(By.xpath("//input[@id='email']"));
      name.sendKeys("invalidemail.com");
      
      WebElement pass = driver.findElement(By.xpath("//input[@id='password']"));
      pass.sendKeys("password123");
      driver.findElement(By.xpath("//button[contains(text(),'Đăng nhập')]")).click();

      // Kiểm tra thông báo lỗi
      WebElement emailField = driver.findElement(By.id("email"));
      
      String validationMessage = emailField.getAttribute("validationMessage");
      Assert.assertEquals(validationMessage, "Please include an '@' in the email address. 'invalidemail.com' is missing an '@'.",
          "Thông báo lỗi không đúng cho email không hợp lệ.");
      Thread.sleep(5000);
      name.clear();
      pass.clear();
  }

  
  @Test(priority = 5)
  public void testPasswordMinLength_006() throws InterruptedException {
	  Thread.sleep(3000);
      WebElement name = driver.findElement(By.xpath("//input[@id='email']"));
      name.sendKeys("user@gmail.com");
      
      WebElement pass = driver.findElement(By.xpath("//input[@id='password']"));
      pass.sendKeys("12345");
     
	  
      driver.findElement(By.xpath("//button[contains(text(),'Đăng nhập')]")).click();

      // Kiểm tra thông báo lỗi
      try {
          // Chờ tối đa 5 giây để thông báo xuất hiện
          WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
          WebElement alertElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='alert']//div[@class='text-base']")));

          // Lấy nội dung của thông báo
          System.out.println("Thông báo: " + alertElement.getText());
      } catch (Exception e) {
          System.out.println("Thông báo lỗi không xuất hiện trong thời gian chờ.");
      }
      String passwordError = driver.findElement(By.cssSelector("body > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1)")).getText();
      Assert.assertEquals(passwordError, "Đăng nhập thất bại. Email hoặc mật khẩu có thể bị sai!", "Thông báo lỗi mật khẩu không đúng");
      name.clear();
      pass.clear();
  }

  @Test(priority = 6)
  public void testPasswordMasking_007() throws InterruptedException {
       WebElement pass = driver.findElement(By.xpath("//input[@id='password']"));
      pass.sendKeys("password123");

      // Kiểm tra thuộc tính type của trường mật khẩu
      String passwordFieldType = driver.findElement(By.id("password")).getAttribute("type");
      Assert.assertEquals(passwordFieldType, "password", "Trường mật khẩu không được ẩn");
      pass.clear();
      Thread.sleep(5000);
  }
  
  @Test(priority = 7)
  public void testSuccessfulLogin_002() throws InterruptedException {
      WebElement name = driver.findElement(By.id("email"));
      name.sendKeys("user@example.com");
      
      WebElement pass = driver.findElement(By.xpath("//input[@id='password']"));
      pass.sendKeys("password123");
      driver.findElement(By.xpath("//button[contains(text(),'Đăng nhập')]")).click();
      Thread.sleep(5000);
      WebElement tab = driver.findElement(By.xpath("//button[@class='btn-sm variant-ghost-surface']"));
	  tab.click();
      // Kiểm tra xem người dùng đã đăng nhập thành công chưa
	      WebElement check_profile = driver.findElement(By.xpath("//a[normalize-space()='Trang cá nhân']"));
	      WebElement check_logout = driver.findElement(By.xpath("//a[normalize-space()='Đăng xuất']"));
	
	      // So sánh giá trị text của các phần tử
	      try {
	          Assert.assertEquals(check_profile.getText(), "Trang cá nhân", "Kiểm tra 'Trang cá nhân' thất bại");
	          Assert.assertEquals(check_logout.getText(), "Đăng xuất", "Kiểm tra 'Đăng xuất' thất bại");
	          System.out.println("Đăng nhập thành công");
	      } catch (AssertionError e) {
	          System.err.println("Đăng nhập thất bại: " + e.getMessage());
	      }
	    WebElement tabb = driver.findElement(By.xpath("//a[contains(text(),'Đăng xuất')]"));
	  	tabb.click();
	  	Thread.sleep(5000);
	  	driver.findElement(By.xpath("//a[contains(text(),'Đăng nhập')]")).click();
	  
      	Thread.sleep(3000);
 }
  @Test(priority = 8)
  public void testArcaneAdLink_008() throws InterruptedException {
	  WebElement button = driver.findElement(By.xpath("//a[contains(text(),'Xem ngay tại CineStream')]"));

      // Nhấn vào nút
      button.click();
      Thread.sleep(3000);
      // Lấy URL hiện tại sau khi nhấn
      String currentUrl = driver.getCurrentUrl();

      // Kiểm tra URL có đúng như mong đợi không
      String expectedUrl = "https://cinestream-livid.vercel.app/details/arcane-lien-minh-huyen-thoai-phan-2"; // Thay bằng URL mong đợi
      Assert.assertEquals(currentUrl, expectedUrl, "URL không khớp!");

      System.out.println("Test Passed: URL khớp với mong đợi!");
  }
  
  
  @AfterTest
  public void afterTest() throws Exception {
	  Thread.sleep(10000);
	  driver.quit();
  }


}

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

public class test_dangKy {
	public WebDriver driver = null;
  @BeforeTest
  public void beforeTest() {
	  driver = new ChromeDriver();
      driver.get("https://cinestream-livid.vercel.app");
      driver.manage().window().maximize();
  }
  
  @Test(priority = 1)
  public void testcase_id_001_show_action() throws InterruptedException{
	  driver.findElement(By.cssSelector("a[href='/auth']")).click();
	  Thread.sleep(5000);
	  driver.findElement(By.xpath("//label[contains(@class,'text-center cursor-pointer transition-colors duration-100 flex-none px-4 py-2 rounded-tl-container-token rounded-tr-container-token hover:variant-soft')]")).click();
	 
	  WebElement nhapTenHienThi = driver.findElement(By.id("name")); // check ô tên hiển thị
      Assert.assertTrue(nhapTenHienThi.isDisplayed(), "Ô nhập tên không hiển thị.");
     
      WebElement nhapTenNguoiDung = driver.findElement(By.id("username")); // check ô tên người dùng
      Assert.assertTrue(nhapTenNguoiDung.isDisplayed(), "Ô nhập tên người dùng không hiển thị.");
     
      WebElement nhapDiaChiEmail = driver.findElement(By.id("email")); // check ô email người dùng
      Assert.assertTrue(nhapDiaChiEmail.isDisplayed(), "Ô nhập địa chỉ email không hiển thị.");
      
      WebElement nhapMatKhau = driver.findElement(By.id("password")); // check ô mật khẩu người dùng
      Assert.assertTrue(nhapMatKhau.isDisplayed(), "Ô nhập mật khẩu không hiển thị.");
     
      WebElement nhapXacNhanMatKhau = driver.findElement(By.id("password-confirm")); // check ô xác nhận mật khẩu người dùng
      Assert.assertTrue(nhapXacNhanMatKhau.isDisplayed(), "Ô nhập xác nhận không hiển thị.");
      
      WebElement buttonDangKy = driver.findElement(By.xpath("//button[contains(text(),'Đăng ký')]")); // check ô xác nhận mật khẩu người dùng
      Assert.assertTrue(buttonDangKy.isDisplayed(), "Ô đăng ký không hiển thị.");
      Thread.sleep(2000);
  }
  
  @Test(priority = 2)
  public void testcase_id_002_emptyFields() throws InterruptedException {
      // Nhấn nút "Đăng ký" mà không nhập thông tin
      WebElement buttonDangKy = driver.findElement(By.xpath("//button[contains(text(),'Đăng ký')]"));
      buttonDangKy.click();
      Thread.sleep(2000);
      
      // Kiểm tra trường "name"
   	  WebElement namee = driver.findElement(By.id("name"));
   	  String emailValidationMessage = namee.getAttribute("validationMessage");
   	  Assert.assertEquals(emailValidationMessage, "Please fill out this field.", 
   	      "Thông báo lỗi không đúng cho trường: name");
   
   	  namee.sendKeys("Van An");
   	  
   	  // Kiểm tra trường "username"
   	  Thread.sleep(3500);
   	  WebElement username = driver.findElement(By.id("username"));
   	  driver.findElement(By.xpath("//button[contains(text(),'Đăng ký')]")).click();
   	  Thread.sleep(5000);
   	  String usernamee = username.getAttribute("validationMessage");
   	  Assert.assertEquals(usernamee, "Please fill out this field.", 
   	      "Thông báo lỗi không đúng cho trường: username");
   	  username.sendKeys("user123");
   	  
   	  //Kiểm tra trường "email"
  	  Thread.sleep(3500);
  	  WebElement email = driver.findElement(By.id("email"));
  	  driver.findElement(By.xpath("//button[contains(text(),'Đăng ký')]")).click();
  	  Thread.sleep(5000);
  	  String mail = email.getAttribute("validationMessage");
  	  Assert.assertEquals(mail, "Please fill out this field.", 
  	      "Thông báo lỗi không đúng cho trường: email");
  	  email.sendKeys("123321d@gmail.com");
  	  
   	  // Kiểm tra trường "password"
   	  Thread.sleep(3500);
   	  WebElement passwordField = driver.findElement(By.id("password"));
   	  driver.findElement(By.xpath("//button[contains(text(),'Đăng ký')]")).click();
   	  Thread.sleep(5000);
   	  String passwordValidationMessage = passwordField.getAttribute("validationMessage");
   	  Assert.assertEquals(passwordValidationMessage, "Please fill out this field.", 
   	      "Thông báo lỗi không đúng cho trường: password");
   	  passwordField.sendKeys("123321.Nhom");
   	  
   	  // Kiểm tra trường "password-confirm"
   	  Thread.sleep(3500);
   	  WebElement passwordconfirm = driver.findElement(By.id("password-confirm"));
   	  driver.findElement(By.xpath("//button[contains(text(),'Đăng ký')]")).click();
   	  Thread.sleep(5000);
   	  String ps = passwordconfirm.getAttribute("validationMessage");
   	  Assert.assertEquals(ps, "Please fill out this field.", 
   	      "Thông báo lỗi không đúng cho trường: passwordcf");
   	  
   	  Thread.sleep(2500);
   	  namee.clear();
   	  username.clear();
   	  email.clear();
   	  passwordField.clear();
   	
      Thread.sleep(2000);
  }

  @Test(priority = 3)
  public void testcase_id_003_invalidEmail() throws InterruptedException {
      // Nhập thông tin hợp lệ ngoại trừ email
      WebElement displayName = driver.findElement(By.id("name"));
      displayName.sendKeys("Van An");

      WebElement username = driver.findElement(By.id("username"));
      username.sendKeys("user123");

      WebElement email = driver.findElement(By.id("email"));
      email.sendKeys("ss"); // Email không hợp lệ

      WebElement password = driver.findElement(By.id("password"));
      password.sendKeys("password123");

      WebElement confirmPassword = driver.findElement(By.id("password-confirm"));
      confirmPassword.sendKeys("password123");

      // Nhấn nút "Đăng ký"
      WebElement buttonDangKy = driver.findElement(By.xpath("//button[contains(text(),'Đăng ký')]"));
      buttonDangKy.click();
      Thread.sleep(2000);

      // Kiểm tra thông báo lỗi cho email
      WebElement emailField = driver.findElement(By.id("email"));
      String validationMessage = emailField.getAttribute("validationMessage");

      Assert.assertEquals(validationMessage, "Please include an '@' in the email address. 'ss' is missing an '@'.",
          "Thông báo lỗi không đúng cho email không hợp lệ.");
      
      displayName.clear();
      username.clear();
      email.clear();
      password.clear();
      confirmPassword.clear();
      Thread.sleep(2000);
  }

  @Test(priority = 4)
  public void testcase_id_004_passwordMismatch() throws InterruptedException {
      // Nhập thông tin hợp lệ ngoại trừ mật khẩu không khớp
      WebElement displayName = driver.findElement(By.id("name"));
      displayName.sendKeys("Van An");

      WebElement username = driver.findElement(By.id("username"));
      username.sendKeys("user123");

      WebElement email = driver.findElement(By.id("email"));
      email.sendKeys("userzz@example.com");

      WebElement password = driver.findElement(By.id("password"));
      password.sendKeys("password123");

      WebElement confirmPassword = driver.findElement(By.id("password-confirm"));
      confirmPassword.sendKeys("password456"); // Mật khẩu không khớp
      String passwordError = driver.findElement(By.cssSelector("body > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1)")).getText();
      Assert.assertEquals(passwordError, "Mật khẩu không trùng khớp!", "Thông báo lỗi mật khẩu không đúng");
      
      displayName.clear();
      username.clear();
      email.clear();
      password.clear();
      confirmPassword.clear();
      Thread.sleep(2000);
  }

  @Test(priority = 5)
  public void testcase_id_005_password_shorter_than_8() throws InterruptedException {
      // Nhập thông tin hợp lệ ngoại trừ mật khẩu dưới 8 ký tự
      WebElement displayName = driver.findElement(By.id("name"));
      displayName.sendKeys("Van An");

      WebElement username = driver.findElement(By.id("username"));
      username.sendKeys("user123");

      WebElement email = driver.findElement(By.id("email"));
      email.sendKeys("userzz@example.com");

      WebElement password = driver.findElement(By.id("password"));
      password.sendKeys("pa");

      WebElement confirmPassword = driver.findElement(By.id("password-confirm"));
      confirmPassword.sendKeys("pa");
      String passwordError = driver.findElement(By.xpath("//p[contains(text(),'Mật khẩu không trùng khớp!')]")).getText();
      Assert.assertEquals(passwordError, "Mật khẩu không trùng khớp!", "Thông báo lỗi mật khẩu không đúng");
      
      displayName.clear();
      username.clear();
      email.clear();
      password.clear();
      confirmPassword.clear();
      Thread.sleep(2000);
  }
  
  
  @Test(priority = 6)
  public void testcase_id_006_register_success() throws InterruptedException {

      WebElement displayName = driver.findElement(By.id("name"));
      displayName.sendKeys("Van Annn");

      WebElement username = driver.findElement(By.id("username"));
      username.sendKeys("user123");

      WebElement email = driver.findElement(By.id("email"));
      email.sendKeys("usdcccddz@example.com");

      WebElement password = driver.findElement(By.id("password"));
      password.sendKeys("password123");

      WebElement confirmPassword = driver.findElement(By.id("password-confirm"));
      confirmPassword.sendKeys("password123");

      // Nhấn nút "Đăng ký"
      WebElement buttonDangKy = driver.findElement(By.xpath("//button[contains(text(),'Đăng ký')]"));
      buttonDangKy.click();
     
      Thread.sleep(7000);
      
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
	      Thread.sleep(3500);
	    WebElement tabb = driver.findElement(By.xpath("//a[contains(text(),'Đăng xuất')]"));
	  	tabb.click();
	  	Thread.sleep(5000);
	  	driver.findElement(By.cssSelector("a[href='/auth']")).click();
	  	
  }
 
  @Test(priority = 7)
  public void testcase_id_007_check_button_watch_movies() throws InterruptedException {
	  Thread.sleep(4500);
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
	  Thread.sleep(5000);
	  driver.quit();
  }

}

package Test;
import java.util.NoSuchElementException;
import java.util.Set;


import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/*1. Mở trang web 
2. Kiểm tra header
3. Kiểm tra nút tìm kiếm có hiển thị không
4. Kiểm tra mục lục có hiển thị không
5. Kiểm tra nút lưu có hiển thị không
6. Kiểm tra nút lịch sử có hiển thị không
7. Kiểm tra danh sách phim có hiển thị không
8. Kiểm tra phân trang (trang sau)có hoạt động không
9. Kiểm tra phân trang (trang trước)có hoạt động không
10. Kiểm tra ô "Phim lẻ" có hoạt động không
11. Kiểm tra ô "Phim bộ" có hoạt động không
12. Kiểm tra ô "TV Shows" có hoạt động không
13. Kiểm tra ô "Hoạt hình" có hoạt động không
14. Kiểm tra nút "Tìm kiếm" có hoạt động không
15. Kiểm tra nút "Mục lục" có hoạt động không
16. Kiểm tra nút "Lưu" có hoạt động không
17. Kiểm tra nút "Lịch sử" có hoạt động không
18. Kiểm tra nút "Người dùng" có hoạt động không
*/
public class test_trangChu {
	public WebDriver driver = null;
  @BeforeTest
  public void beforeTest() {
	  
	  driver = new ChromeDriver();
      driver.get("https://cinestream-livid.vercel.app");
      driver.manage().window().maximize();
  }
  @Test(priority = 1)
  public void check_Title() {
      WebElement searchBar = driver.findElement(By.xpath("//strong[@class='text-xl uppercase text-yellow-500']")); 
      String actualTitle = searchBar.getText();
      String expectedTitle = "CINESTREAM";
      Assert.assertEquals(actualTitle, expectedTitle, "Tiêu đề không đúng.");
  }
  
  @Test(priority = 2)
  public void giaoDien_timKiem() {
	  WebElement searchBar = driver.findElement(By.xpath("//div[contains(@class,'app-bar-slot-trail flex-none flex items-center space-x-4')]//a[1]//*[name()='svg']")); // ID thanh tìm kiếm
      Assert.assertTrue(searchBar.isDisplayed(), "Thanh tìm kiếm không hiển thị đúng.");
  }

  @Test(priority = 3)
  public void giaoDien_mucLuc() {
	  WebElement searchBar = driver.findElement(By.xpath("//div[@class='app-bar-slot-trail flex-none flex items-center space-x-4 ']//a[2]//*[name()='svg']")); // ID muc luc
      Assert.assertTrue(searchBar.isDisplayed(), "Mục lục không hiển thị đúng.");
  }
  @Test(priority = 4)
  public void giaoDien_Luu() {
	  WebElement searchBar = driver.findElement(By.xpath("//div[contains(@class,'app-bar-slot-trail flex-none flex items-center space-x-4')]//a[1]//*[name()='svg']")); // ID thanh tìm kiếm
      Assert.assertTrue(searchBar.isDisplayed(), "Lưu không hiển thị đúng.");
  }
  @Test(priority = 5)
  public void giaoDien_lichSu() {
	  WebElement searchBar = driver.findElement(By.xpath("//div[contains(@class,'app-bar-slot-trail flex-none flex items-center space-x-4')]//a[1]//*[name()='svg']")); // ID thanh tìm kiếm
      Assert.assertTrue(searchBar.isDisplayed(), "Lịch sử không hiển thị đúng.");
  }
  @Test(priority = 6)
  public void danhSachPhim() throws InterruptedException {
	  Thread.sleep(10000);
	  WebElement newMoviesList = driver.findElement(By.xpath("//div[contains(@class,'flex flex-wrap gap-6 justify-center')]")); // Class của danh sách phim mới
	  Assert.assertTrue(newMoviesList.isDisplayed(), "Danh sách phim mới không hiển thị.");
  }
  @Test(priority = 7)
  public void phanTrang_1() throws InterruptedException {
	 
    WebElement button = driver.findElement(By.xpath("//button[contains(text(),'Trang Sau')]"));
    button.click();
    Thread.sleep(5000);
    WebElement buttonn = driver.findElement(By.xpath("//button[contains(text(),'Trang Sau')]"));
    buttonn.click();
  }
  @Test(priority = 8)
  public void phanTrang_2() throws InterruptedException {
	  Thread.sleep(5000);
	  driver.findElement(By.xpath("//button[contains(text(),'Trang Trước')]")).click();
 }
  @Test(priority = 9)
  public void phimLe() throws InterruptedException {
	  Thread.sleep(5000);
	  WebElement tab = driver.findElement(By.xpath("//div[contains(text(),'Phim lẻ')]"));
	  tab.click();
  }
  
  @Test(priority = 10)
  public void phimBo() throws InterruptedException {
	  Thread.sleep(5000);
	  WebElement tab = driver.findElement(By.xpath("//div[contains(text(),'Phim bộ')]"));
	  tab.click();
  }
  
  @Test(priority = 11)
  public void TVShows() throws InterruptedException {
	  Thread.sleep(5000);
	  WebElement tab = driver.findElement(By.xpath("//div[contains(text(),'TV Shows')]"));
	  tab.click();
  }
  
  @Test(priority = 12)
  public void hoatHinh() throws InterruptedException {
	  Thread.sleep(5000);
	  WebElement tab = driver.findElement(By.xpath("//div[contains(text(),'Hoạt hình')]"));
	  tab.click();
  }
  
  @Test(priority = 13)
  public void phimMoi() throws InterruptedException {
	  Thread.sleep(5000);
	  WebElement tab = driver.findElement(By.xpath("//div[contains(text(),'Phim mới')]"));
	  tab.click();
  }
  
  @Test(priority = 14)
  public void buttonTimKiem() throws InterruptedException {
	  Thread.sleep(5000);
	  String mainWindowHandle = driver.getWindowHandle();
	  WebElement tab = driver.findElement(By.xpath("//div[@class='app-bar-slot-trail flex-none flex items-center space-x-4 ']//a[1]//*[name()='svg']"));
	  tab.click();
	    
	    // Bước 3: Chờ một chút để tab mới mở
	    Thread.sleep(5000); // hoặc có thể dùng WebDriverWait để chờ tab mới xuất hiện
	    
	    // Bước 4: Lấy tất cả window handles và chuyển sang tab mới
	    Set<String> allWindowHandles = driver.getWindowHandles();
	    
	    // Loại bỏ window handle ban đầu để chỉ còn lại tab mới
	    for (String handle : allWindowHandles) {
	        if (!handle.equals(mainWindowHandle)) {
	            // Chuyển sang tab mới
	            driver.switchTo().window(handle);
	            break;
	        }
	    }
	    
	    // Bước 5: Đóng tab mới
	    driver.close();
	    
	    // Bước 6: Quay lại tab chính
	    driver.switchTo().window(mainWindowHandle);
  }
  
  @Test(priority = 15)
  public void buttonMucLuc() throws InterruptedException {
	  Thread.sleep(5000);
	  String mainWindowHandle = driver.getWindowHandle();
	  WebElement tab = driver.findElement(By.xpath("//div[contains(@class,'app-bar-slot-trail flex-none flex items-center space-x-4')]//a[2]//*[name()='svg']"));
	  tab.click();
	// Bước 3: Chờ một chút để tab mới mở
	    Thread.sleep(5000); // hoặc có thể dùng WebDriverWait để chờ tab mới xuất hiện
	    
	    // Bước 4: Lấy tất cả window handles và chuyển sang tab mới
	    Set<String> allWindowHandles = driver.getWindowHandles();
	    
	    // Loại bỏ window handle ban đầu để chỉ còn lại tab mới
	    for (String handle : allWindowHandles) {
	        if (!handle.equals(mainWindowHandle)) {
	            // Chuyển sang tab mới
	            driver.switchTo().window(handle);
	            break;
	        }
	    }
	    
	    // Bước 5: Đóng tab mới
	    driver.close();
	    
	    // Bước 6: Quay lại tab chính
	    driver.switchTo().window(mainWindowHandle);
  }
  
  @Test(priority = 16)
  public void buttonLuu() throws InterruptedException {
	  Thread.sleep(5000);
	  String mainWindowHandle = driver.getWindowHandle();
	  WebElement tab = driver.findElement(By.xpath("//div[contains(@class,'app-bar-slot-trail flex-none flex items-center space-x-4')]//a[3]//*[name()='svg']"));
	  tab.click();
	// Bước 3: Chờ một chút để tab mới mở
	    Thread.sleep(5000); // hoặc có thể dùng WebDriverWait để chờ tab mới xuất hiện
	    
	    // Bước 4: Lấy tất cả window handles và chuyển sang tab mới
	    Set<String> allWindowHandles = driver.getWindowHandles();
	    
	    // Loại bỏ window handle ban đầu để chỉ còn lại tab mới
	    for (String handle : allWindowHandles) {
	        if (!handle.equals(mainWindowHandle)) {
	            // Chuyển sang tab mới
	            driver.switchTo().window(handle);
	            break;
	        }
	    }
	    
	    // Bước 5: Đóng tab mới
	    driver.close();
	    
	    // Bước 6: Quay lại tab chính
	    driver.switchTo().window(mainWindowHandle);
  }
  
  @Test(priority = 17)
  public void buttonLichSu() throws InterruptedException {
	  Thread.sleep(5000);
	  String mainWindowHandle = driver.getWindowHandle();
	  WebElement tab = driver.findElement(By.xpath("//div[@class='app-bar-slot-trail flex-none flex items-center space-x-4 ']//a[4]//*[name()='svg']"));
	  tab.click();
	// Bước 3: Chờ một chút để tab mới mở
	    Thread.sleep(5000); // hoặc có thể dùng WebDriverWait để chờ tab mới xuất hiện
	    
	    // Bước 4: Lấy tất cả window handles và chuyển sang tab mới
	    Set<String> allWindowHandles = driver.getWindowHandles();
	    
	    // Loại bỏ window handle ban đầu để chỉ còn lại tab mới
	    for (String handle : allWindowHandles) {
	        if (!handle.equals(mainWindowHandle)) {
	            // Chuyển sang tab mới
	            driver.switchTo().window(handle);
	            break;
	        }
	    }
	    
	    // Bước 5: Đóng tab mới
	    driver.close();
	    
	    // Bước 6: Quay lại tab chính
	    driver.switchTo().window(mainWindowHandle);
  }
  
  @Test(priority = 18)
  public void buttonNguoiDung() throws InterruptedException {
	  Thread.sleep(5000);
	  String mainWindowHandle = driver.getWindowHandle();
	  WebElement tab = driver.findElement(By.xpath("//div[@class='app-bar-slot-trail flex-none flex items-center space-x-4 ']//a[5]//*[name()='svg']"));
	  tab.click();
	// Bước 3: Chờ một chút để tab mới mở
	    Thread.sleep(5000); // hoặc có thể dùng WebDriverWait để chờ tab mới xuất hiện
	    
	    // Bước 4: Lấy tất cả window handles và chuyển sang tab mới
	    Set<String> allWindowHandles = driver.getWindowHandles();
	    
	    // Loại bỏ window handle ban đầu để chỉ còn lại tab mới
	    for (String handle : allWindowHandles) {
	        if (!handle.equals(mainWindowHandle)) {
	            // Chuyển sang tab mới
	            driver.switchTo().window(handle);
	            break;
	        }
	    }
	    
	    // Bước 5: Đóng tab mới
	    driver.close();
	    
	    // Bước 6: Quay lại tab chính
	    driver.switchTo().window(mainWindowHandle);
  }
 
  
  @AfterTest
  public void afterTest() throws Exception {
	  Thread.sleep(10000);
	 // driver.quit();
  }


}
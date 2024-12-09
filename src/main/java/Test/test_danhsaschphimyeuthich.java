package Test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;

public class test_danhsaschphimyeuthich {
	public String searchMovieAndClick(String searchQuery) throws InterruptedException {
	    // Chờ 3 giây
	    Thread.sleep(3000);

	    // Tìm thanh tìm kiếm
	    WebElement searchBar = driver.findElement(By.cssSelector("input[placeholder='Search...']"));
	    searchBar.clear();
	    
	    Actions actions = new Actions(driver);

	    // Mô phỏng gõ phím từng ký tự
	    actions.click(searchBar).perform();
	    for (char c : searchQuery.toCharArray()) {
	        actions.sendKeys(String.valueOf(c)).perform();
	        Thread.sleep(200); // Thêm độ trễ giữa các lần gõ
	    }

	    // Chờ 2 giây để gợi ý xuất hiện
	    Thread.sleep(2000);
	    WebElement popup = driver.findElement(By.cssSelector("div[data-popup='searchResultPopup']"));
	    Thread.sleep(2000);

	    // Lấy liên kết của phim trong gợi ý tìm kiếm
	    WebElement link = driver.findElement(By.cssSelector("div[data-popup='searchResultPopup'] ul.list li a"));
	    String nameMovie = link.getText();
	    
	    // Lấy dòng đầu tiên nếu có nhiều dòng trong tên phim
	    String firstLine = nameMovie.split("\\n")[0];
	    
	    // Nhấn vào liên kết phim
	    link.click();
	    // Trả về tên phim đã tìm
	    return firstLine;
	}

	public WebDriver driver = null;
	  @BeforeTest
	  public void beforeTest() throws InterruptedException{
		  driver = new ChromeDriver();
	      driver.get("https://cinestream-livid.vercel.app");
	      driver.manage().window().maximize();
	      driver.findElement(By.cssSelector("a[href='/auth']")).click();
		  Thread.sleep(5000);
		  WebElement name = driver.findElement(By.id("email"));
	      name.sendKeys("user@example.com");
	      
	      WebElement pass = driver.findElement(By.xpath("//input[@id='password']"));
	      pass.sendKeys("password123");
	      driver.findElement(By.xpath("//button[contains(text(),'Đăng nhập')]")).click();
	      Thread.sleep(5000);
	  }
  @Test(priority = 1)
  public void test_case_001() throws InterruptedException{
	  Thread.sleep(3000);
	  WebElement movie = driver.findElement(By.xpath("//a[contains(@href,'/bookmark')]"));
	  AssertJUnit.assertTrue("Nút phim đã yêu thích không hiển thị!", movie.isDisplayed());
	  try {
		    AssertJUnit.assertTrue("Nút 'phim đã yêu thích' không khả dụng!", movie.isEnabled());
		} catch (Exception e) {
		    System.out.println("Đã xảy ra lỗi: " + e.getMessage());
		}
  }
  @Test(priority = 2)
  public void test_case_002() throws InterruptedException{
	  WebElement movie = driver.findElement(By.xpath("//a[contains(@href,'/bookmark')]"));
      movie.click();
      Thread.sleep(4000);
      String text ="Phim đã yêu thích";
      WebElement title = driver.findElement(By.xpath("//h1[contains(text(),'Phim đã yêu thích')]"));
      String name_title = title.getText();
      if(text.equals(name_title)) {
    	  System.out.println("Danh mục giống nhau");
      } else {
    	  System.out.println("Danh mục khác nhau");
      }
      // Bước 3: Kiểm tra thông báo "Bạn chưa xem phim nào"
      try {
          // Kiểm tra xem có tồn tại phần tử thông báo "Bạn chưa xem phim nào." hay không
          WebElement emptyMessageElement = null;
          try {
              emptyMessageElement = driver.findElement(By.xpath("//div[@class='flex flex-col px-56 pt-20 mr']"));
          } catch (Exception e) {
              // Nếu không tìm thấy phần tử, emptyMessageElement sẽ là null
          }

          if (emptyMessageElement != null) {
              // Nếu tìm thấy phần tử thông báo
              String emptyMessageText = emptyMessageElement.getText();
              // In ra console hoặc kiểm tra giá trị
              System.out.println("Thông báo: " + emptyMessageText);
              // Kiểm tra giá trị
              if (emptyMessageText.equals("Loading movies...")) {
                  System.out.println("Thông báo hiển thị chính xác!");
              } else {
                  System.out.println("Thông báo không đúng!");
              }
          } else {
              // Nếu không tìm thấy thông báo, tức là có danh sách phim
              System.out.println("Danh sách yêu thích phim đã tồn tại. Lấy thông tin phim:");
          }
      } finally {
          
      }
}
  @Test(priority = 4)
  public void test_case_004() throws InterruptedException{
	  String movieName = searchMovieAndClick("Deep lurk");
	  Thread.sleep(5000);
	  WebElement movieTitleElement = driver.findElement(By.xpath("//p[@class='text-xl font-bold mb-2']"));
	  String movieTitle = movieTitleElement.getText();
	  System.out.println("Tên phim: " + movieTitle);

	  // Lấy link hình ảnh
	  WebElement imageElement = driver.findElement(By.cssSelector("img.w-52.h-80"));
	  String imageUrl = imageElement.getAttribute("src");
	  System.out.println("Link hình ảnh: " + imageUrl);
	  WebElement favourite = driver.findElement(By.xpath("//button[normalize-space()='Yêu thích']"));
	  favourite.click();
	  try {
		    // Chờ alert xuất hiện trong vòng 10 giây
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    wait.until(ExpectedConditions.alertIsPresent());

		    // Lấy alert và nhấn OK (accept)
		    Alert alert = driver.switchTo().alert();
		    System.out.println("Alert message: " + alert.getText()); // In thông báo từ alert
		    alert.accept(); // Nhấn OK trên alert

		} catch (Exception e) {
		    System.out.println("Không có alert nào xuất hiện.");
		}
	  Thread.sleep(2000);
	  WebElement movie = driver.findElement(By.xpath("//a[contains(@href,'/bookmark')]"));
      movie.click();
      Thread.sleep(5000);
   // Lấy phần tử cuối cùng trong danh sách
      WebElement lastItem = driver.findElement(By.cssSelector("ul.grid.grid-cols-2.gap-12 li:last-child"));

      // Lấy tên phim trong phần tử cuối cùng
      String lastMovieTitle = lastItem.findElement(By.cssSelector("div.text-xl.font-semibold.text-white-800")).getText();
      System.out.println("Tên phim cuối cùng: " + lastMovieTitle);

      // Lấy đường dẫn hình ảnh trong phần tử cuối cùng
      String lastImageUrl = lastItem.findElement(By.cssSelector("a.flex-shrink-0 img")).getAttribute("src");
      System.out.println("Link hình ảnh cuối cùng: " + lastImageUrl);


      System.out.println("Tên phim: " + movieTitle);
      System.out.println("Link hình ảnh: " + imageUrl);
	  if(lastMovieTitle.equals(movieTitle) && lastImageUrl.equals(imageUrl)) {
		  System.out.println("Giống nhau");
	  }
	  else {
		  System.out.println("Khác nhau");
	  }
}
  @Test(priority = 5)
  public void test_case_005() throws InterruptedException{ 
	  driver.navigate().refresh();
	  Thread.sleep(4000);
	  WebElement searchBar = driver.findElement(By.cssSelector("input[placeholder='Search...']"));
	  searchBar.clear();
	  Actions actions = new Actions(driver);

	  // Mô phỏng gõ phím từng ký tự
	  actions.click(searchBar).perform();
	  String searchQuery = "Deep lurk";
	  for (char c : searchQuery.toCharArray()) {
	      actions.sendKeys(String.valueOf(c)).perform();
	      Thread.sleep(200); // Thêm độ trễ giữa các lần gõ
	  }
	  Thread.sleep(2000);
	  // Đợi gợi ý xuất hiện
	  WebElement popup = driver.findElement(By.cssSelector("div[data-popup='searchResultPopup']"));
	  Thread.sleep(2000);
	  WebElement link = driver.findElement(By.cssSelector("div[data-popup='searchResultPopup'] ul.list li a"));
	  String nameMovie = link.getText();
	  String firstLine = nameMovie.split("\\n")[0];
	  link.click();
	  Thread.sleep(3000);
	  WebElement favourite = driver.findElement(By.xpath("//button[normalize-space()='Yêu thích']"));
	  favourite.click();
	  try {
		    // Chờ alert xuất hiện trong vòng 10 giây
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    wait.until(ExpectedConditions.alertIsPresent());
		    String n = "Bộ phim này đã có trong danh sách yêu thích của bạn!";
		    // Lấy alert và nhấn OK (accept)
		    Alert alert = driver.switchTo().alert();
		    if(n.equals(alert)) {
		    	System.out.println("Thông báo trùng khớp");
		    } else {
		    	System.out.println("Thông báo không trùng khớp");
		    }
		  
		    alert.accept(); // Nhấn OK trên alert
		    

		} catch (Exception e) {
		    System.out.println("Không có alert nào xuất hiện.");
		}
  }
  @AfterTest
  public void afterTest() throws InterruptedException{
	  Thread.sleep(5000);
	  driver.quit();
  }

}

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

public class test_luuphimdaxem {
	
	public WebDriver driver = null;
	@BeforeTest
	  public void beforeTest() throws InterruptedException{
		  driver = new ChromeDriver();
	      driver.get("https://cinestream-livid.vercel.app");
	      driver.manage().window().maximize();
	      driver.findElement(By.cssSelector("a[href='/auth']")).click();
		  Thread.sleep(5000);
		  WebElement name = driver.findElement(By.id("email"));
	      name.sendKeys("vandong1@gmail.com");
	      
	      WebElement pass = driver.findElement(By.xpath("//input[@id='password']"));
	      pass.sendKeys("123321123");
	      driver.findElement(By.xpath("//button[contains(text(),'Đăng nhập')]")).click();
	      Thread.sleep(5000);
	  }

  @Test(priority = 1)
  public void test_case_001() throws InterruptedException{
	  Thread.sleep(3000);
	  WebElement history = driver.findElement(By.cssSelector("a[href='/history']"));
	  AssertJUnit.assertTrue("Nút lịch sử không hiển thị!", history.isDisplayed());
	  try {
		    AssertJUnit.assertTrue("Nút 'Xem phim' không khả dụng!", history.isEnabled());
		} catch (Exception e) {
		    System.out.println("Đã xảy ra lỗi: " + e.getMessage());
		}
  }
  @Test(priority = 2)
  public void test_case_002() throws InterruptedException{
	  WebElement watchHistoryMenu = driver.findElement(By.xpath("//a[@href='/history']"));
      watchHistoryMenu.click();
      Thread.sleep(4000);
      // Bước 3: Kiểm tra thông báo "Bạn chưa xem phim nào"
      try {
          // Kiểm tra xem có tồn tại phần tử thông báo "Bạn chưa xem phim nào." hay không
          WebElement emptyMessageElement = null;
          try {
              emptyMessageElement = driver.findElement(By.xpath("//section[@class='py-6']//p[@class='text-center text-gray-500']"));
          } catch (Exception e) {
              // Nếu không tìm thấy phần tử, emptyMessageElement sẽ là null
          }

          if (emptyMessageElement != null) {
              // Nếu tìm thấy phần tử thông báo
              String emptyMessageText = emptyMessageElement.getText();

              // In ra console hoặc kiểm tra giá trị
              System.out.println("Thông báo: " + emptyMessageText);

              // Kiểm tra giá trị
              if (emptyMessageText.equals("Bạn chưa xem phim nào.")) {
                  System.out.println("Thông báo hiển thị chính xác!");
              } else {
                  System.out.println("Thông báo không đúng!");
              }
          } else {
              // Nếu không tìm thấy thông báo, tức là có danh sách phim
              System.out.println("Danh sách phim đã tồn tại. Lấy thông tin phim:");
          }
      } finally {
          
      }
}
  @Test(priority = 3)
  public void test_case_003() throws InterruptedException{
	  Thread.sleep(3000);
	  try {
          // Tìm danh sách các phần tử <li> trong <ul>
          List<WebElement> movieItems = driver.findElements(By.xpath("//ul[@class='space-y-4']/li"));

          // Duyệt qua từng phần tử <li> để lấy thông tin
          for (WebElement item : movieItems) {
              // Lấy tên phim
              WebElement movieTitleElement = item.findElement(By.xpath(".//span[1]")); // Phần tử <span> đầu tiên
              String movieTitle = movieTitleElement.getText();

              // Lấy số tập phim
              WebElement episodeElement = item.findElement(By.xpath(".//span[2]")); // Phần tử <span> thứ hai
              String episode = episodeElement.getText();

              // Lấy đường dẫn video
              String videoLink = item.findElement(By.xpath(".//a")).getAttribute("href");

              // Lấy đường dẫn ảnh
              String imageLink = item.findElement(By.xpath(".//img")).getAttribute("src");

              // In thông tin ra console
              System.out.println("Tên phim: " + movieTitle);
              System.out.println("Tập phim: " + episode);
              System.out.println("Đường dẫn video: " + videoLink);
              System.out.println("Đường dẫn ảnh: " + imageLink);
              System.out.println("-------------------");
          }
      } catch (Exception e) {
          System.out.println("Có lỗi xảy ra: " + e.getMessage());
      }
  }
  @Test(priority = 4)
  public void test_case_004() throws InterruptedException{

	  try {
          // Tìm phần tử cần nhấn
          WebElement movieElement = driver.findElement(By.xpath("//ul[@class='space-y-4']/li[1]/a"));

          // Lưu URL hiện tại trước khi nhấn
          String originalUrl = driver.getCurrentUrl();

          // Nhấn vào phần tử
          movieElement.click();

          // Kiểm tra URL thay đổi (vòng lặp thủ công)
          boolean isUrlChanged = false;
          int timeout = 5; // Thời gian chờ tối đa (5 giây)
          int elapsedTime = 0; // Thời gian đã trôi qua

          while (elapsedTime < timeout) {
              String currentUrl = driver.getCurrentUrl();
              if (!currentUrl.equals(originalUrl)) {
                  isUrlChanged = true;
                  break;
              }
              // Chờ 1 giây trước khi kiểm tra lại
              Thread.sleep(1000);
              elapsedTime++;
          }

          if (isUrlChanged) {
              // Nếu URL thay đổi
              System.out.println("Thành công: URL đã thay đổi!");
              System.out.println("URL mới: " + driver.getCurrentUrl());
          } else {
              // Nếu URL không thay đổi, kiểm tra nội dung trang
              System.out.println("URL không thay đổi. Kiểm tra nội dung trang...");

              // Kiểm tra sự xuất hiện của player hoặc nội dung mới
              boolean isContentUpdated = false;
              elapsedTime = 0; // Reset thời gian đã trôi qua

              while (elapsedTime < timeout) {
                  try {
                      WebElement playerElement = driver.findElement(By.xpath("//div[@class='flex flex-col']"));
                      if (playerElement.isDisplayed()) {
                          isContentUpdated = true;
                          break;
                      }
                  } catch (Exception e) {
                      // Nếu phần tử chưa xuất hiện, tiếp tục chờ
                  }
                  // Chờ 1 giây trước khi kiểm tra lại
                  Thread.sleep(1000);
                  elapsedTime++;
              }
              if (isContentUpdated) {
                  System.out.println("Thành công: Nội dung trang đã được cập nhật!");
              } else {
                  System.out.println("Thất bại: Không có thay đổi nào trên trang!");
              }
          }
      } catch (Exception e) {
          System.out.println("Có lỗi xảy ra: " + e.getMessage());
      } finally {
      }
	  driver.navigate().back();
  }
  @Test(priority = 5)
  public void test_case_005() throws InterruptedException{
	  Thread.sleep(3000);
	  WebElement searchBar = driver.findElement(By.cssSelector("input[placeholder='Search...']"));
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
	  Thread.sleep(5000);
	  WebElement movieTitleElement = driver.findElement(By.xpath("//p[@class='text-xl font-bold mb-2']"));
      String movieTitle = movieTitleElement.getText();
      System.out.println("Tên phim: " + movieTitle);

      // Lấy link hình ảnh
      WebElement imageElement = driver.findElement(By.cssSelector("img.w-52.h-80"));
      String imageUrl = imageElement.getAttribute("src");
      System.out.println("Link hình ảnh: " + imageUrl);
      String originalTab = driver.getWindowHandle();
      // Lấy tập phim đầu tiên
      WebElement firstEpisodeElement = driver.findElement(By.cssSelector("div.flex.flex-wrap.gap-5 button:first-of-type"));
      String firstEpisode = firstEpisodeElement.getText();
      System.out.println("Tập phim đầu tiên: " + firstEpisode);
      firstEpisodeElement.click();
	  Thread.sleep(3000);
	  for (String handle : driver.getWindowHandles()) {
		    if (!handle.equals(originalTab)) {
		        driver.switchTo().window(handle);
		        break;
		    }
		}

		// Đóng tab mới
		driver.close();

		// Quay lại tab gốc
		driver.switchTo().window(originalTab);
	  Thread.sleep(2000);
	  WebElement phimmoi = driver.findElement(By.cssSelector("a[href='/']"));
	  phimmoi.click();
	  Thread.sleep(2000);
	  WebElement history = driver.findElement(By.cssSelector("a[href='/history']"));
	  history.click();
	  Thread.sleep(5000);
	  
      // Tìm danh sách ul có class "space-y-4"
      WebElement ulElement = driver.findElement(By.cssSelector("ul.space-y-4"));

      // Lấy danh sách các phần tử li bên trong ul
      List<WebElement> listItems = ulElement.findElements(By.tagName("li"));

      // Lấy phần tử li cuối cùng
      WebElement latestElement = listItems.get(listItems.size() - 1);

      // Lấy các thông tin bên trong phần tử
      String imageSrc = latestElement.findElement(By.cssSelector("img")).getAttribute("src");
      String title = latestElement.findElement(By.cssSelector("div span:first-child")).getText();
      String episode = latestElement.findElement(By.cssSelector("div span:last-child")).getText();
      // In thông tin ra console
      System.out.println("Image Source: " + imageSrc);
      System.out.println("Title: " + title);
      System.out.println("Episode: " + episode);
      if (movieTitle.equals(title) || imageUrl.equals(imageSrc) || firstEpisode.equals(episode)) {
    	    System.out.println("Cùng phim");
    	} else {
    	    System.out.println("Khác phim");
    	}

  }
  @AfterTest
  public void afterTest() throws InterruptedException{
	  Thread.sleep(5000);
	  driver.quit();
  }

}

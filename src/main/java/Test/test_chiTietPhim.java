package Test;

import static org.testng.AssertJUnit.assertTrue;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.Assert;


public class test_chiTietPhim {
	public WebDriver driver = null;
  @BeforeTest
  public void beforeTest() {
	  driver = new ChromeDriver();
      driver.get("https://cinestream-livid.vercel.app");
      driver.manage().window().maximize();
  }

  @Test(priority = 1)
  public void tim_kiem_phim_va_so_sanh() throws InterruptedException{
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
	  Thread.sleep(3000);
	  WebElement element = driver.findElement(By.cssSelector("p.text-xl.font-bold.mb-2"));
	  String content = element.getText();
	  System.out.println("Nội dung của phần tử: " + content);
	  System.out.println(" " + firstLine );
	  if (firstLine.equalsIgnoreCase(content)) {
		    System.out.println("Tên phim khớp với trang chi tiết: " + content);
		} else {
		    System.out.println("Tên phim không khớp với trang chi tiết.");
		}
  }
  
  
  @Test(priority = 2)
  public void kiem_tra_danh_sasch_tap_phim_va_so_sanh_voi_tap_phim() {
	  int countt = 0; // Khởi tạo giá trị mặc định
	  int count = 0;  // Khởi tạo giá trị mặc định

	  try {
	      // Tìm phần tử có class "font-bold mb-2 text-base"
	      WebElement element = driver.findElement(By.cssSelector("p.font-bold.mb-2.text-base"));

	      // Lấy nội dung văn bản của phần tử
	      String text = element.getText();

	      // Loại bỏ chữ cái, chỉ giữ lại số
	      String number = text.replaceAll("[^0-9]", ""); // Regex để loại bỏ tất cả ký tự không phải số

	      // Kiểm tra chuỗi trước khi chuyển đổi
	      if (!number.isEmpty()) {
	          countt = Integer.parseInt(number);
	      } else {
	          System.out.println("Chuỗi không chứa số hợp lệ.");
	      }

	      // In ra kết quả
	      System.out.println("Nội dung số: " + countt);
	  } catch (Exception e) {
	      // Xử lý lỗi
	      System.out.println("Đã xảy ra lỗi: " + e.getMessage());
	  }

	  try {
	      // Tìm tất cả các phần tử có class "bg-blue-500 text-white py-2 px-4 rounded hover:bg-blue-700"
	      List<WebElement> buttons = driver.findElements(By.cssSelector("button.bg-blue-500.text-white.py-2.px-4.rounded.hover\\:bg-blue-700"));

	      // Đếm số lượng phần tử
	      count = buttons.size();

	      // In ra kết quả
	      System.out.println("Số lượng phần tử: " + count);
	  } catch (Exception e) {
	      // Xử lý lỗi
	      System.out.println("Đã xảy ra lỗi: " + e.getMessage());
	  }

	  // So sánh hai giá trị
	  if (countt == 0 || count == 0) {
	      System.out.println("Không thể so sánh do dữ liệu không hợp lệ.");
	  } else if (countt == count) {
	      System.out.println("Bằng nhau");
	  } else {
	      System.out.println("Khác nhau");
	  }

  }

  @Test(priority = 3)
  public void testWatchButton() {
      // Tìm nút "Xem phim"
	  WebElement xemPhimButton = driver.findElement(By.xpath("//button[text()='Xem phim']"));
	  WebElement yeuThichButton = driver.findElement(By.xpath("//button[text()='Yêu thích']"));


      // Kiểm tra nút hiển thị và khả dụng
	  try {
		  Assert.assertTrue(xemPhimButton.isDisplayed(), "Nút 'Xem phim' không hiển thị!");
		  Assert.assertTrue(xemPhimButton.isEnabled(), "Nút 'Xem phim' không khả dụng!");
		  Assert.assertTrue(yeuThichButton.isDisplayed(), "Nút 'Xem phim' không hiển thị!");
		  Assert.assertTrue(yeuThichButton.isEnabled(), "Nút 'Xem phim' không khả dụng!");
	  }
	  catch (Exception e) {
		  System.out.println("Đã xảy ra lỗi: " + e.getMessage());
	}
  }
@Test(priority = 4)
public void check() {
	 try {
         // 1. Lấy ảnh phim
         WebElement movieImage = driver.findElement(By.cssSelector("img.w-52.h-80.rounded-md"));
         String imageUrl = movieImage.getAttribute("src");
         System.out.println("URL ảnh phim: " + imageUrl);

         // 2. Lấy tên phim
         WebElement movieTitle = driver.findElement(By.cssSelector("p.text-xl.font-bold.mb-2"));
         System.out.println("Tên phim: " + movieTitle.getText());

         // 3. Lấy thể loại
         List<WebElement> genres = driver.findElements(By.cssSelector("div.flex.flex-wrap.space-x-4 > p.bg-gray-200.text-black.px-2.py-1.rounded.text-sm"));
         System.out.print("Thể loại: ");
         for (WebElement genre : genres) {
             System.out.print(genre.getText() + ", ");
         }
         System.out.println();

         // 4. Lấy quốc gia
         WebElement country = driver.findElement(By.xpath("//h2[text()='Quốc gia:']/following-sibling::div/p"));
         System.out.println("Quốc gia: " + country.getText());

         // 5. Lấy trạng thái
         WebElement status = driver.findElement(By.xpath("//h2[text()='Trạng thái:']/following-sibling::p"));
         System.out.println("Trạng thái: " + status.getText());

         // 6. Lấy thời lượng
         WebElement duration = driver.findElement(By.xpath("//h2[text()='Thời lượng:']/following-sibling::p"));
         System.out.println("Thời lượng: " + duration.getText());

         // 7. Lấy chất lượng
         WebElement quality = driver.findElement(By.xpath("//h2[text()='Chất lượng:']/following-sibling::p"));
         System.out.println("Chất lượng: " + quality.getText());

         // 8. Lấy nút "Xem phim" và "Yêu thích"
         WebElement xemPhimButton = driver.findElement(By.xpath("//button[text()='Xem phim']"));
         WebElement yeuThichButton = driver.findElement(By.xpath("//button[text()='Yêu thích']"));
         System.out.println("Nút Xem phim: " + xemPhimButton.getText());
         System.out.println("Nút Yêu thích: " + yeuThichButton.getText());
         
         // 9. Lấy danh sách tập phim
         List<WebElement> episodes = driver.findElements(By.cssSelector("div.flex.flex-wrap.justify-start.gap-5 > div > button"));
         System.out.println("Danh sách tập phim:");
         for (WebElement episode : episodes) {
             System.out.println(episode.getText());
         }
         
         // 10. Lấy nội dung phim
         Thread.sleep(3000);
         WebElement movieContent = driver.findElement(By.xpath("//p[@class='text-base']"));
         System.out.println("\nNội dung phim:");
         System.out.println(movieContent.getText());

     } catch (Exception e) {
         // Xử lý lỗi
         System.out.println("Đã xảy ra lỗi: " + e.getMessage());
     }
}
  
  @AfterTest
  public void afterTest() throws InterruptedException{
	  Thread.sleep(5000);
	  driver.quit();
  }

}

package Test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;


import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;


public class test_timkiem {
	public WebDriver driver = null;
	@BeforeTest
	  public void beforeTest() throws InterruptedException{
		  driver = new ChromeDriver();
	      driver.get("https://cinestream-livid.vercel.app");
	      driver.manage().window().maximize();
		  Thread.sleep(5000);
	  }
  
  @Test(priority = 1)
  public void Id_001_hien_thi() {
      // Kiểm tra thanh tìm kiếm
      WebElement searchBar = driver.findElement(By.xpath("//input[@placeholder='Search...']"));
      Assert.assertTrue(searchBar.isDisplayed(), "Thanh tìm kiếm không hiển thị!");

      // Kiểm tra biểu tượng kính lúp
      WebElement searchIcon = driver.findElement(By.xpath("//div[@class='relative']//*[name()='svg']"));
      Assert.assertTrue(searchIcon.isDisplayed(), "Biểu tượng kính lúp không hiển thị!");
  }

  // TC_002: Tìm kiếm với từ khóa hợp lệ
  @Test(priority = 2)
  public void Id_002_tim_kiem_hop_le() throws InterruptedException {
	  WebElement searchBar = driver.findElement(By.cssSelector("input[placeholder='Search...']"));
	  Actions actions = new Actions(driver);

	  // Mô phỏng gõ phím từng ký tự
	  actions.click(searchBar).perform();
	  String searchQuery = "one piece";
	  for (char c : searchQuery.toCharArray()) {
	      actions.sendKeys(String.valueOf(c)).perform();
	      Thread.sleep(200); // Thêm độ trễ giữa các lần gõ
	  }

	  // Đợi gợi ý xuất hiện
	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	  List<WebElement> suggestions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
	      By.cssSelector("div[data-popup='searchResultPopup'] ul.list li")
	  ));

	  // In ra danh sách gợi ý
	  for (WebElement suggestion : suggestions) {
	      System.out.println(suggestion.getText());
	  }
	  
	  Thread.sleep(6000);
	  searchBar.clear();
      
  }


  // TC_003: Tìm kiếm với từ khóa không hợp lệ
  @Test(priority = 3)
  public void Id_003_tu_khoa_khong_hop_le() throws InterruptedException {
	  WebElement searchBar = driver.findElement(By.cssSelector("input[placeholder='Search...']"));
	  Actions actions = new Actions(driver);

	  // Mô phỏng gõ phím từng ký tự
	  actions.click(searchBar).perform();
	  String searchQuery = "xyz123";
	  for (char c : searchQuery.toCharArray()) {
	      actions.sendKeys(String.valueOf(c)).perform();
	      Thread.sleep(200); // Thêm độ trễ giữa các lần gõ
	  }

      // Kiểm tra thông báo không có kết quả
      WebElement noResultMessage = driver.findElement(By.cssSelector(".no-result-message")); // Thay selector nếu cần
      Assert.assertTrue(noResultMessage.isDisplayed(), "Thông báo 'Không tìm thấy kết quả nào' không hiển thị!");
      Assert.assertEquals(noResultMessage.getText(), "Không tìm thấy kết quả nào", "Thông báo không đúng!");
  }
  
  @Test(priority = 5)
  public void id_005_kiem_tra_ket_qua_co_dau_va_khong_dau() throws InterruptedException {
	  WebElement searchBar = driver.findElement(By.cssSelector("input[placeholder='Search...']"));
	  Actions actions = new Actions(driver);

	  // Hành động 1: Nhập "Người nhện" và lưu gợi ý vào A
	  actions.click(searchBar).perform();
	  String searchQueryA = "Người nhện";
	  List<String> suggestionsA = new ArrayList<>();

	  for (char c : searchQueryA.toCharArray()) {
	      actions.sendKeys(String.valueOf(c)).perform();
	      Thread.sleep(200); // Độ trễ để mô phỏng nhập từng ký tự
	  }

	  // Đợi gợi ý xuất hiện
	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	  List<WebElement> elementsA = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
	      By.cssSelector("div[data-popup='searchResultPopup'] ul.list li")
	  ));
	  // In ra danh sách gợi ý
	  for (WebElement suggestion : elementsA) {
	      System.out.println(suggestion.getText());
	  }
	  // Lưu gợi ý vào danh sách A
	  for (WebElement suggestion : elementsA) {
	      suggestionsA.add(suggestion.getText());
	  }
	  
	  Thread.sleep(6000);
	  // Làm mới thanh tìm kiếm
	  String currentText = searchBar.getAttribute("value");
	  for (int i = 0; i < currentText.length(); i++) {
	      searchBar.sendKeys(Keys.BACK_SPACE);
	      Thread.sleep(100); // Độ trễ để mô phỏng gõ phím
	  }
	  
	  // Hành động 2: Nhập "Nguoi nhen" và lưu gợi ý vào B
	  
	  actions.click(searchBar).perform();
	  String searchQueryB = "Nguoi nhen";
	  List<String> suggestionsB = new ArrayList<>();

	  for (char c : searchQueryB.toCharArray()) {
	      actions.sendKeys(String.valueOf(c)).perform();
	      Thread.sleep(200); // Độ trễ để mô phỏng nhập từng ký tự
	  }

	  // Đợi gợi ý xuất hiện
	  List<WebElement> elementsB = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
	      By.cssSelector("div[data-popup='searchResultPopup'] ul.list li")
	  ));
	  
	// In ra danh sách gợi ý
	  for (WebElement suggestion : elementsB) {
	      System.out.println(suggestion.getText());
	  }
	  // Lưu gợi ý vào danh sách B
	  for (WebElement suggestion : elementsB) {
	      suggestionsB.add(suggestion.getText());
	  }

	  // So sánh A và B
	  System.out.println("Gợi ý cho 'Người nhện': " + suggestionsA);
	  System.out.println("Gợi ý cho 'Nguoi nhen': " + suggestionsB);

	  if (suggestionsA.equals(suggestionsB)) {
	      System.out.println("Kết quả: A và B giống nhau.");
	  } else {
	      System.out.println("Kết quả: A và B khác nhau.");
	  }

	  // Thêm thời gian quan sát (tuỳ chọn)
	  Thread.sleep(6000);
  }
	
  @AfterTest
  public void afterTest() throws InterruptedException{
	  Thread.sleep(10000);
	  driver.quit();
  }

}

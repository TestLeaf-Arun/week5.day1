package week5.day1;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseClassServiceNow {
	
	public ChromeDriver driver;

	@BeforeMethod
	public void preCondition() {
		
	//  Load ServiceNow Application
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://dev89690.service-now.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	//  Enter Username as admin
		WebElement frame1 = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));																		
		driver.switchTo().frame(frame1);
		driver.findElement(By.id("user_name")).sendKeys("admin");

	//  Enter Password as Vingar@123
		driver.findElement(By.id("user_password")).sendKeys("Vingar@123");

	//  Click Login
		driver.findElement(By.id("sysverb_login")).click();
	}
	

	@AfterMethod
	public void postCondition() {
		driver.close();
	}
}
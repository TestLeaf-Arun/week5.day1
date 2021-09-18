package week5.day1;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ServiceNowDeleteIncident extends BaseClassServiceNow {
	
	@Test
	public void deleteIncident() throws InterruptedException {
		
	//  Enter incident in Filter Navigator and press Enter
		driver.findElement(By.xpath("//input[@id='filter']")).sendKeys("incident", Keys.ENTER);
		driver.findElement(By.xpath("(//div[@class='sn-widget-list-title' and text()='Open'])[1]")).click();
		Thread.sleep(2000);
		
	//  Search for the existing incident; Navigate into it and Delete the incident	
		WebElement frame2 = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(frame2);
		Thread.sleep(2000);
			
		driver.findElement(By.xpath("//label[text()='Search']//following-sibling::input")).sendKeys("INC0010011", Keys.ENTER);
		driver.findElement(By.xpath("//a[@class='linked formlink']")).click();
		driver.findElement(By.xpath("(//button[text()='Delete'])[1]")).click();
		
	//  Verify the Deleted Incident	
		String update = driver.findElement(By.xpath("//tbody[@class='list2_body']/tr/td[text()='No records to display']")).getText();
			if (update.contains("No records")) {
				System.out.println("Incident is Deleted");
			} else {
				System.out.println("Incident is not Deleted");
			}	
	}
}
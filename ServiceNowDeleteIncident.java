package week5.day1;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ServiceNowDeleteIncident extends BaseClassServiceNow {
	
	@Test
	public void deleteIncident() throws InterruptedException {
		
	//  Enter incident in Filter Navigator and press Enter
		driver.findElement(By.xpath("//input[@id='filter']")).sendKeys("incident");
		driver.findElement(By.xpath("(//div[@class='sn-widget-list-title' and text()='All'])[2]")).click();
		
	//  Search for the existing incident; Navigate into it and Delete the incident	
		WebElement frame2 = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(frame2);
		Thread.sleep(2000);
			
		String incidentnumber = driver.findElement(By.xpath("(//a[@class='linked formlink'])[1]")).getText();
		System.out.println("Incident number to be deleted: " + incidentnumber);
		driver.findElement(By.xpath("(//a[@class='linked formlink'])[1]")).click();
		driver.findElement(By.id("sysverb_delete")).click();
		driver.findElement(By.id("ok_button")).click();
		
		WebElement searchbox = driver.findElement(By.xpath("(//input[@class='form-control'])[1]"));
		searchbox.sendKeys(incidentnumber);
		searchbox.sendKeys(Keys.ENTER);
		
	//  Verify the deleted incident	
		String searchresults = driver.findElement(By.xpath("(//table)[2]//tbody//td")).getText();
			if (searchresults.equalsIgnoreCase("No records to display")) {
				System.out.println("Incident is Deleted");
			} else {
				System.out.println("Incident is not Deleted");
			}
	}
}
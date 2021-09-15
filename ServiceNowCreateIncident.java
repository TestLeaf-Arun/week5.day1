package week5.day1;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class ServiceNowCreateIncident extends BaseClassServiceNow {
	
	@Test
	public void createIncident() throws InterruptedException {
		
	//  Enter incident in Filter Navigator and press Enter
		driver.findElement(By.xpath("//input[@id='filter']")).sendKeys("incident");
		driver.findElement(By.xpath("(//div[@class='sn-widget-list-title' and text()='All'])[2]")).click();

	//  Click New button
		WebElement frame2 = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(frame2);
		driver.findElement(By.xpath("//button[@id='sysverb_new']")).click();

	//  Select a value for Caller and Enter value for short_description
		driver.findElement(By.id("lookup.incident.caller_id")).click();
		
		Set<String> windowHandlesSet = driver.getWindowHandles();
		List<String> windowHandlesList = new ArrayList<String>(windowHandlesSet);
		driver.switchTo().window(windowHandlesList.get(1));
		
		driver.findElement(By.xpath("(//a[@class='glide_ref_item_link'])[1]")).click();
		
		driver.switchTo().window(windowHandlesList.get(0));
		Thread.sleep(2000);		
		driver.switchTo().frame(frame2);
		driver.findElement(By.id("incident.short_description")).sendKeys("Description is important for an incident");

	//  Read the incident number and save it a variable
		String incidentnumber = driver.findElement(By.xpath("//input[@id='incident.number']")).getAttribute("value");
		System.out.println("Incident number: " + incidentnumber);

	//  Click on Submit button
		driver.findElement(By.id("sysverb_insert")).click();

	//  Search the same incident number in the next search screen
		WebElement searchtext = driver.findElement(By.xpath("(//input[@class='form-control'])[1]"));
		searchtext.sendKeys(incidentnumber);
		searchtext.sendKeys(Keys.ENTER);

	//  Verify the incident is created successful
		String resultincident = driver.findElement(By.xpath("//a[@class='linked formlink']")).getText();
			if (incidentnumber.equalsIgnoreCase(resultincident)) {
				System.out.println("Incident is created successfully");
			} else {
				System.out.println("Incident is not created");
			}
	}
}
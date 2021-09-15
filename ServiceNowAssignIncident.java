package week5.day1;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class ServiceNowAssignIncident extends BaseClassServiceNow {
	
	@Test
	public void assignIncident() throws InterruptedException {
		
	//  Enter incident in Filter Navigator and press Enter
		driver.findElement(By.xpath("//input[@id='filter']")).sendKeys("incident");
		driver.findElement(By.xpath("(//div[@class='sn-widget-list-title' and text()='All'])[2]")).click();

	//  Click on open and Search for the existing incident and click on the incident
		driver.findElement(By.xpath("(//div[contains(text(),'Open') and @class='sn-widget-list-title'])[1]")).click();
		
		WebElement frame2 = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(frame2);
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//a[@class='linked formlink'])[2]")).click();

	//  Assign the incident to Software group
		driver.findElement(By.id("lookup.incident.assignment_group")).click();
		
		Set<String> windowHandlesSet = driver.getWindowHandles();
		List<String> windowHandlesList = new ArrayList<String>(windowHandlesSet);
		driver.switchTo().window(windowHandlesList.get(1));
		Thread.sleep(2000);
		
		WebElement searchbar = driver.findElement(By.xpath("(//input[@class='form-control'])[1]"));
		searchbar.sendKeys("software");
		searchbar.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//a[@class='glide_ref_item_link'])[1]")).click();

	//  Update the incident with Work Notes
		driver.switchTo().window(windowHandlesList.get(0));
		WebElement frame3 = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(frame3);
		
		driver.findElement(By.id("activity-stream-textarea")).sendKeys("Updating the Assignment group");
		driver.findElement(By.id("sysverb_update")).click();

	//  Verify the Assignment group and Assigned for the incident
		String assigngroup = driver.findElement(By.xpath("(//a[@class='linked'])[4]")).getText();
		System.out.println("The Assignment group is " + assigngroup);
	}
}
package week5.day1;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ServiceNowUpdateIncident extends BaseClassServiceNow {

	@Test
	public void updateIncident() throws InterruptedException {
		
	//  Enter incident in Filter Navigator and press Enter
		WebElement filternav = driver.findElement(By.xpath("//input[@id='filter']"));
		filternav.sendKeys("Incident");
		filternav.sendKeys(Keys.ENTER);
		
		driver.findElement(By.xpath("(//div[@class='sn-widget-list-title' and text()='All'])[2]")).click();

	//  Search for the existing incident and click on the incident
		WebElement frame2 = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(frame2);
		Thread.sleep(2000);
		
		WebElement searchbox = driver.findElement(By.xpath("//span[@class='input-group-addon input-group-select']/following-sibling::input"));
		searchbox.sendKeys("INC0000020");
		searchbox.sendKeys(Keys.ENTER);
		
		driver.findElement(By.xpath("//a[@class='linked formlink']")).click();

	//  Update the incidents with Urgency as High and State as In Progress
		WebElement urgencydrop1 = driver.findElement(By.xpath("//select[@id='incident.urgency']"));
		Select drpdwn1 = new Select(urgencydrop1);
		drpdwn1.selectByVisibleText("1 - High");
		
		WebElement statedrop = driver.findElement(By.id("incident.state"));
		Select drpdwn2 = new Select(statedrop);
		drpdwn2.selectByIndex(1);
		
		driver.findElement(By.id("sysverb_update")).click();

	//  Verify the priority
		String prioritytext = driver.findElement(By.xpath("(//table)[2]//tbody//td[7]")).getText();
			if (prioritytext.equalsIgnoreCase("3 - Moderate")) {
				System.out.println("Priority is verified");
			} else {
				System.out.println("Priority is not verified");
			}
		
	//  Verify the state
		String statetext = driver.findElement(By.xpath("(//table)[2]//tbody//td[8]")).getText();
			if (statetext.equalsIgnoreCase("In Progress")) {
				System.out.println("State is verified");
			} else {
				System.out.println("State is not verified");
			}
	}
}
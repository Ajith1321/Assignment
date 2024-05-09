package assignment;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Tc_02_functional_testing {

	public static void main(String[] args) throws InterruptedException, IOException {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\HP\\eclipse-workspace\\Automation_Assignnment\\drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get("https://demo.dealsdray.com/");

		driver.findElement(By.name("username")).sendKeys("prexo.mis@dealsdray.com");

		driver.findElement(By.name("password")).sendKeys("prexo.mis@dealsdray.com");

		driver.findElement(By.xpath("//button[text()='Login']")).click();

		Thread.sleep(1000);

		driver.findElement(By.xpath("//div[@class='css-sukebr']/button[@type='button']")).click();

		driver.findElement(By.xpath("//span[text()='Orders']")).click();

		Thread.sleep(1000);

		driver.findElement(By.xpath("//button[text()='Add Bulk Orders']")).click();

		Thread.sleep(1000);

		driver.findElement(By.xpath("//input[@type='file']")).sendKeys("C:\\Users\\HP\\Downloads\\demo-data.xlsx");

		driver.findElement(By.xpath("//button[text()='Import']")).click();

		driver.findElement(By.xpath("//button[text()='Validate Data']")).click();

		Thread.sleep(1000);

		Alert al = driver.switchTo().alert();
		al.accept();

		
		
		 Screenshot screenshot = new AShot()
				  .shootingStrategy(ShootingStrategies.viewportPasting(1000))
				  .takeScreenshot(driver);
		  
		 ImageIO.write(screenshot.getImage(), "PNG", new File("full-page-screenshot.png"));
	 
		 
		
	}

}

package assignment;
import org.openqa.selenium.By;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.imageio.ImageIO;


public class Tc_01_Ui_testing {
	
	public static void main(String[] args) throws InterruptedException
	{
		
	WebDriver driver = null ;
	
	List<Dimension> resolutions = new ArrayList<>();
	resolutions.add(new Dimension(1920, 1080));
    resolutions.add(new Dimension(1366, 768));
    resolutions.add(new Dimension(1536, 864));
    

    
    List<String> browsers = new ArrayList<>();
    browsers.add("Chrome");
    browsers.add("Firefox");
    //browsers.add("Safari");

    
    for (String browser : browsers) {
        
        if (browser.equals("Chrome"))
        {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\HP\\eclipse-workspace\\Automation_Assignnment\\drivers\\chromedriver.exe");
            driver = new ChromeDriver();
        }
        else if (browser.equals("Firefox")) 
        {
            System.setProperty("webdriver.gecko.driver", "C:\\Users\\HP\\eclipse-workspace\\Automation_Assignnment\\drivers\\geckodriver.exe");
            driver = new FirefoxDriver();
        }
        else if (browser.equals("Safari")) {
            driver = new SafariDriver();
        }

        
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        
        driver.get("https://www.getcalley.com/page-sitemap.xml");
        List<String> xpath = new ArrayList<>();
        xpath.add("//a[text()='https://www.getcalley.com/']");
        xpath.add("//a[text()='https://www.getcalley.com/calley-call-from-browser/']");
        xpath.add("//a[text()='https://www.getcalley.com/calley-pro-features/']");
        xpath.add("//a[text()='https://www.getcalley.com/best-auto-dialer-app/']");
        xpath.add("//a[text()='https://www.getcalley.com/how-calley-auto-dialer-app-works/']");
        
        for (String path : xpath)
        {
        	driver.findElement(By.xpath(path)).click();
   
         for (Dimension resolution : resolutions) {
            driver.manage().window().setSize(resolution);
            try {
            	
            	LocalDateTime currentDateTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH-mm-ss");
                String formattedDateTime = currentDateTime.format(formatter);

                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                String fileName =  "Desktop-" + browser +"-" + resolution.getWidth() + "x" + resolution.getHeight() + "-" + formattedDateTime + ".png";
                ImageIO.write(ImageIO.read(screenshot), "png", new File("screenshots/" + fileName));
               
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            
        		}
        		driver.navigate().back();
        }
        driver.quit();
    }
}

}



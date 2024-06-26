package myproject;

/*import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	WebDriver driver;
	String url ="https://cloud-frontend-topaz.vercel.app/login";
	
	public void twoSecWait() throws InterruptedException {
		Thread.sleep(2000);
	}
	
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		Reporter.log("======Browser Seccion Start======",true);
		try (Scanner scan = new Scanner(System.in)) {
			System.out.println("Please Select 1-3 number to open any browser");
			System.out.println("1. Google Chrome \n" + "2. Firefox \n"+ "3. Ms Edge");
			
			
			
			int browserChoice = scan.nextInt();
			switch (browserChoice) {
			case 1:Reporter.log("=====Chrome Browser is Opening=====",true);
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				driver.get(url);
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				
				break;

			case 2:
				Reporter.log("=====Firefox Browser is Opening=====",true);
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				driver.get(url);
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

				break;
				
			case 3:
				Reporter.log("=====Edge Browser is Opening=====",true);
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				driver.get(url);
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

				break;
				
				default:
				System.out.println("Your Choice is Invalid");
			}
		}
	}
	
		@AfterMethod
		public void close() {
		driver.close();	
	}*/




import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseClass {
	
	static WebDriver driver;
	Properties prop;
	
	@BeforeMethod
	public void setUp() {
		
		Reporter.log("====BROWSER SESSION STARTED====", true);
		
		prop = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");

        try {
        	if (inputStream != null) {
                prop.load(inputStream);
            } else {
                System.out.println("config.properties file not found!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        String browserChoice = prop.getProperty("browser");
        String baseUrl = prop.getProperty("baseUrl");
		
      while(browserChoice == null) {
        	
        	browserChoice = "chrome";
        	break;
        }			
	switch(browserChoice) {
	
	case "chrome": 
			Reporter.log("===STARTING WITH CHROME BROWSER===", true);
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get(baseUrl);
			break;
			
	case "firefox": 
			Reporter.log("===STARTING WITH FIREFOX BROWSER===", true);
			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get(baseUrl);
			break;
	
	
	case "edge": 
			Reporter.log("===STARTING WITH EDGE BROWSER===", true);
			WebDriverManager.edgedriver().setup();
			driver= new EdgeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get(baseUrl);
			break;
			
	default: 
		
			System.out.println("Your Browser choice has invalid selection!");		
		}
	
	}
	
	@AfterMethod
	public void tearDown() {
		
		Reporter.log("==BROWSER SESSION END==", true);
		driver.close();
		
	}

}


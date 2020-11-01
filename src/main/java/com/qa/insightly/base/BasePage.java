package com.qa.insightly.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.Parameters;

import com.qa.insightly.utils.ElementUtil;
import com.qa.insightly.utils.JavaScriptUtil;
import com.qa.insightly.utils.OptionsManager;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author Adith
 *
 */
public class BasePage {
	
	public WebDriver driver;
	public Properties prop;
	public ElementUtil elementUtil;
	public JavaScriptUtil jsUtil;
	public static String highlight;
	public OptionsManager optionsManager;

	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	
	
	/**
	 * This method is used to initialize the WebDriver on the basis of given browser...
	 * @param browserName
	 * @return
	 */

	public WebDriver init_driver(Properties prop) {
		
		String browserName = null;
//		browserName = prop.getProperty("browser");
		
		if(System.getProperty("browser") ==null){
			browserName = prop.getProperty("browser");
		}else {
			browserName = System.getProperty("browser");
		}
		
		System.out.println("Running on ----->" +browserName + "browser");
		
		highlight = prop.getProperty("highlight"); //used to highlight the webelement...
		//Create Object of OptionsManager
		optionsManager = new OptionsManager(prop);
		
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver();
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			//driver = new FirefoxDriver();
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		} 
		else if(browserName.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			//driver = new EdgeDriver();
			tlDriver.set(new EdgeDriver());
		}
		else if(browserName.equalsIgnoreCase("Safari")) {
			WebDriverManager.getInstance(SafariDriver.class);
			//driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		getDriver().get(prop.getProperty("url"));
		
		return getDriver();
		
		
	}
	
	/**
	 * This method is used to initialize the properties from config.properties file on bais of given env variable...
	 * @return
	 */
	public Properties init_prop() {
		prop = new Properties();
		String path = null;
		String env = null;

		try {
			env = System.getProperty("env");
			System.out.println("env value is--->" + env);
			if (env == null) {
				path = System.getProperty("user.dir")+"\\src\\main\\java\\com\\qa\\insightly\\config\\config.properties";
			} else {
				switch (env) {
				case "qa":
					path = System.getProperty("user.dir")+"\\src\\main\\java\\com\\qa\\insightly\\config\\qa.config.properties";
					break;
				case "dev":
					path = System.getProperty("user.dir")+"\\src\\main\\java\\com\\qa\\insightly\\config\\dev.config.properties";
					break;
				case "stage":
					path = System.getProperty("user.dir")+"\\src\\main\\java\\com\\qa\\insightly\\config\\stg.config.properties";
					break;
				default:
					System.out.println("Please pass the correct env value----> " + env);
					break;
				}
			}

			FileInputStream ip = new FileInputStream(path);
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}
	
	
	/**
	 * take screenshot
	 */
	
	public String getScreenshot() {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	
	

}

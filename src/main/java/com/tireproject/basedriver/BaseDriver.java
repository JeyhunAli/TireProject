package com.tireproject.basedriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.opencsv.CSVWriter;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author jey
 *
 */
public class BaseDriver {
	
	public WebDriver driver;
	public Properties property;
	public WebDriverWait wait;
	public OptionsManager optionsmanager;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	/**
	 * @pass correctbrowser
	 * @param browsername
	 * @return driver 
	 */
	
	
	 //Constructor
//    public BaseDriver(WebDriver driver) {
//        this.driver = driver;
//        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//       
//    }
	
	public WebDriver start_driver(Properties property) {
		
		String browsername = property.getProperty("browser").trim();
		System.out.println("browser name is: " + browsername);
		
		optionsmanager = new OptionsManager();
		
		if(browsername.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			tlDriver.set(new ChromeDriver(OptionsManager.getChromeOptions()));
		}
		else if(browsername.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver(OptionsManager.getFirefoxOptions()));
		}
		else if(browsername.equalsIgnoreCase("safari")) {
			tlDriver.set(new SafariDriver());
		}
		else {
			System.out.println("plz pass correct browser.... " + browsername);
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(property.getProperty("url").trim());
		return getDriver();
	}
	
	
	
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	
	
	
	/**
	 * reading & making connections with config folder
	 * @return properties
	 */
	public Properties start_properties() {
		property = new Properties();
		try {
			FileInputStream fip = new FileInputStream("./src/test/resources/config/config.properties");
			property.load(fip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		return property;
	}
	
	 public void writeDataToCSV(String filePath, List<String[]> data){
	        // first create file object for file placed at location
	        // specified by filepath
	        File file = new File(filePath);

	        try {
	            // create FileWriter object with file as parameter
	            FileWriter outputfile = new FileWriter(file);

	            CSVWriter writer = new CSVWriter(outputfile);
	            writer.writeAll(data);

	            // closing writer connection
	            writer.close();
	        }
	        catch (IOException e) {
	           
	            e.printStackTrace();
	        }
	    }
	   
	   

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

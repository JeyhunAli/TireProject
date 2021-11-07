package com.tireproject.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.tireproject.basedriver.BaseDriver;
import com.tireproject.pages.HomePage;

public class BaseTest {

	public BaseDriver bd;
	public Properties property;
	public WebDriver driver;
	public HomePage homePage;

	@Parameters({"browser"})
	@BeforeTest
	public void setUp(String browser) {
		
		
		bd = new BaseDriver();
		property = bd.start_properties();

		if (browser != null) {
			property.setProperty("browser", browser);

		}

		driver = bd.start_driver(property);
		homePage = new HomePage(driver);

	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}

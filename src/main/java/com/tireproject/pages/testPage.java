package com.tireproject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.tireproject.utils.ElementUtil;
import com.tireproject.utils.JavaScriptUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class testPage {
	
	static WebDriver driver;
	static ElementUtil u;
	static JavaScriptUtil util;
	
	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		u = new ElementUtil(driver);
		util = new JavaScriptUtil(driver);
		
		
		By name = By.xpath("//input[@name='q']");
		
	
		
		
	}
	
	
	
	public void getElementByJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("return arguments[0].innerHTML", (element));
	     js.executeScript("arguments[0].click()", (element));
	     
		}	
	

}

package com.tireproject.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tireproject.basedriver.BaseDriver;
import com.tireproject.utils.ElementUtil;
import com.tireproject.utils.JavaScriptUtil;
public class HomePage {
	
	/**
	 * class properties
	 */
	private WebDriver driver;
	private ElementUtil util;
	private BaseDriver baseDriver;
	static List<String[]> data;
	
	 /**
     * Constructor
     */
    public HomePage(WebDriver driver) {
       this.driver = driver;
       util = new ElementUtil(this.driver);
       baseDriver =new BaseDriver();
       
    }

    
    /**
     * Web Elements
     */
    By sizeButton = By.xpath("//div[@class='cmp-in-page-nav']//div//a");
    By subSizeButton = By.xpath("//ul[@class='tires-by-diameter__links']//li//a");
    By results = By.xpath("//div[@class='tsr-profile__results h3']");
    By cookieOkButton = By.xpath("//a[@title='agree button']");
  
    

    /**
     * launcing Homepage
     * @return  Homepage
     * because im handling at time only one page
     */
    public HomePage getMainSizePage() {
    	util.click(cookieOkButton);
        return this;
        
    }
    
	
    //iterate for all the sizes and get the data for diameter 1
    public HomePage getSizeResult(){
        data = new ArrayList<String[]>();
        
        data.add(new String[] { "Tier Diameter", "Tire Size", "Tire Count", "URL" });
        List<WebElement> sizeButtons = util.waitVisibilityElements(sizeButton);
        for(int i=0; i<sizeButtons.size(); i++){
            List<WebElement> sizeButtonsInLoop = util.waitVisibilityElements(sizeButton);
            String diameter = sizeButtonsInLoop.get(i).getText();
            sizeButtonsInLoop.get(i).click();
           List<WebElement> subSizeButtons = util.waitVisibilityElements(subSizeButton);
            for(int j=0; j<subSizeButtons.size(); j++){
                List<WebElement> subSizeButtonsInLoop = util.waitVisibilityElements(subSizeButton);
            
                JavascriptExecutor js = (JavascriptExecutor) driver;
                String size = (String) js.executeScript("return arguments[0].innerHTML", subSizeButtonsInLoop.get(j));
                js.executeScript("arguments[0].click()", subSizeButtonsInLoop.get(j));
                
                System.out.println("Results : " + util.readText(results));
                String count = util.readText(results);
                String url = driver.getCurrentUrl();
               // System.out.println("current url: " +url);
                data.add(new String[] { diameter.trim(), size.trim(), count.trim(), url });
                //break;
                driver.navigate().back();
            }
          // break;
            driver.navigate().back();
        }
        baseDriver.writeDataToCSV("extentReport/data.csv", data);
        return this;
    }
	
	
   

}

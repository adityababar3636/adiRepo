package com.exam.my_aditya_project;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FitPeoAutomation {

	 public static void ScrollToElement(WebDriver driver, WebElement element) {
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
	    }
	 
	  private static int calculateSliderOffset(WebDriver driver, WebElement slider, int targetValue) {
	        int sliderWidth = slider.getSize().getWidth();
	        int minValue = 0;  // Set the slider's minimum value (e.g., 0)
	        int maxValue = 2000;  // Set the slider's maximum value (e.g., 2000)

	        // Calculate the offset based on slider range and width
	        double pixelsPerUnit = (double) sliderWidth / (maxValue - minValue);
	        return (int) ((targetValue - minValue) * pixelsPerUnit - sliderWidth / 2);
	    }
	 
    public static void main(String[] args) throws InterruptedException {
    	
    
        // Set up ChromeDriver
            WebDriverManager.chromedriver().setup();
            WebDriver driver = new ChromeDriver();
        
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get("https://www.fitpeo.com");
            driver.manage().window().maximize();
            System.out.println("Navigated to FitPeo homepage.");

            // Navigate to Revenue Calculator Page
            WebElement revenueCalculatorLink = driver.findElement(By.linkText("Revenue Calculator"));
            revenueCalculatorLink.click();
            System.out.println("Navigated to Revenue Calculator page.");

        	try {
            
            // Scroll to slider section
            WebElement slider = driver.findElement(By.xpath("//div[@class='MuiBox-root css-j7qwjs']/span[1]"));
             ScrollToElement(driver, slider);
            System.out.println("Scrolled to the slider section.");
            
         // Adjust slider to 820
            WebElement textField = driver.findElement(By.xpath("//div[@class='MuiBox-root css-j7qwjs']/div/div/input"));
            textField.clear();
            // Determine the offset to set slider to 820


            // Calculate the offset
            int offset = calculateSliderOffset(driver, slider, 820);
            // Move the slider
            Actions actions = new Actions(driver);
            actions.clickAndHold(slider).moveByOffset(offset, 0).click().perform();            
            System.out.println("Scrolled slider to 820.");
				

            // Enter the value "560" into the text field
    			driver.findElement(By.xpath("//div[@class='MuiBox-root css-j7qwjs']/div/div/input")).clear();
    			Thread.sleep(3000);
    			driver.findElement(By.xpath("//div[@class='MuiBox-root css-j7qwjs']/div/div/input")).sendKeys("560");

    			
    			//Validate Slider Value:560
    			
    			 String enteredamount = textField.getAttribute("value");
    	            if ("560".equals(enteredamount)) {
    	                System.out.println("Test Passed: Slider set to 560, and text field updated.");
    	            } else {
    	                System.out.println("Test Failed: Text field value is " + enteredamount);
    	            }
//    			
   
//    	         	Select CPT Codes
    	            
    	            
    	            WebElement cpt99091Checkbox = driver.findElement(By.xpath("//div[@class=\"MuiBox-root css-1p19z09\"]/div[1]/label/span/input"));
    	            WebElement cpt99453Checkbox = driver.findElement(By.xpath("//div[@class=\"MuiBox-root css-1p19z09\"]/div[2]/label/span/input"));
    	            WebElement cpt99454Checkbox = driver.findElement(By.xpath("//div[@class=\"MuiBox-root css-1p19z09\"]/div[3]/label/span/input"));
    	            WebElement cpt99474Checkbox = driver.findElement(By.xpath("//div[@class=\"MuiBox-root css-1p19z09\"]/div[8]/label/span/input"));
    	            
    	            
    	            if (!cpt99091Checkbox.isSelected()) {
    	                cpt99091Checkbox.click();  // Select CPT-99091
    	            }
    	            if (!cpt99453Checkbox.isSelected()) {
    	                cpt99453Checkbox.click();  // Select CPT-99453
    	            }
    	            if (!cpt99454Checkbox.isSelected()) {
    	                cpt99454Checkbox.click();  // Select CPT-99454
    	            }
    	            if (!cpt99474Checkbox.isSelected()) {
    	                cpt99474Checkbox.click();  // Select CPT-99474
    	            }

    	            // Optionally, verify that the checkboxes are selected
    	            System.out.println("CPT-99091 selected: " + cpt99091Checkbox.isSelected());
    	            System.out.println("CPT-99453 selected: " + cpt99453Checkbox.isSelected());
    	            System.out.println("CPT-99454 selected: " + cpt99454Checkbox.isSelected());
    	            System.out.println("CPT-99474 selected: " + cpt99474Checkbox.isSelected());


    
        	} catch (Exception e) {
			    e.printStackTrace();
			} finally {
			    // Close the browser
//			    driver.quit();
			}
			}
    
    	
    	
    }























        

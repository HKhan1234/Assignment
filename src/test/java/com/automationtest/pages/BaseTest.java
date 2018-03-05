package com.automationtest.pages;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTest {
	public WebDriver driver;
	public WebDriverWait wait;
	
	@Before
	public void setup() {
		
		System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
		
		// Create a Fire fox driver. All test and page classes use this driver.
		driver = new FirefoxDriver();
		
		// Create a wait. All test and page classes use this wait.
		wait = new WebDriverWait(driver, 10);
		driver.get("https://www.funda.nl/");
	}

	@After
	public void teardown() {
		driver.quit();
	}
	
	public HomePage getHomePage() {
		return new HomePage(driver);
	}
	
	public SearchResultPage getSearchResultPage() {
		return new SearchResultPage(driver);
	}
	
	public ZoekSuggestiePage getZoekSuggestion() {
		return new ZoekSuggestiePage(driver);
	}
}

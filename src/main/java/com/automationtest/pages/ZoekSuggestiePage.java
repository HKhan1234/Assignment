package com.automationtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ZoekSuggestiePage extends BasePage {

	public ZoekSuggestiePage(WebDriver driver) {
		super(driver);
		}
	private By zoekSuggestion = By.className("location-suggestions-header-content");
	
	public String getZoekSuggestion() {
		return readText(zoekSuggestion);
	} 

}

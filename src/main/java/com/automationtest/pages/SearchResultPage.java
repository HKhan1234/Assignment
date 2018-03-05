package com.automationtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultPage extends BasePage{

	public SearchResultPage(WebDriver driver) {
		super(driver);
	}
	
	private By searchOutputResult = By.className("search-output-result-count");
	
	public String getSearchOutputResult() {
		return readText(searchOutputResult);
	}	

}

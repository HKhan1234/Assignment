package com.automationtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	private WebDriver driver;
	public WebDriverWait wait;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 10);
	}

	// FindElement Method
	public WebElement findElement(By elementLocation) {
		return driver.findElement(elementLocation);
	}

	// Click Method
	public void click(By elementLocation) {
		findElement(elementLocation).click();
	}

	// Read Text
	public String readText(By elementLocation) {
		return findElement(elementLocation).getText();
	}
}

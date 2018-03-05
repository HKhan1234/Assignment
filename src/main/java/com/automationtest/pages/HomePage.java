package com.automationtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {
	private By searchBlockNavigationItemKoop = By.cssSelector("a[href*='koop']");
	private By searchBlockNavigationItemHuur = By.cssSelector("a[href*='huur']");
	private By searchBlockNavigationItemNieuwBouw = By.cssSelector("a[href*='nieuwbouw']");
	private By searchBlockNavigationItemRecreatie = By.cssSelector("a[href*='recreatie']");
	private By searchBlockNavigationItemEurope = By.cssSelector("a[href*='europe']");
	private By fieldLocation = By.id("autocomplete-input");
	private By fieldStraal = By.id("Straal");
	private By filterKoopPrijsVan = By.id("range-filter-selector-select-filter_koopprijsvan");
	private By filterKoopPrijsTot = By.id("range-filter-selector-select-filter_koopprijstot");
	private By buttonZoek = By.cssSelector("button[type=submit]");
	private By arrowSelectVan = By.className("range-filter-selector__select");
	private By lastquery = By.className("search-block__last-query-label");
	private By lastSearchCountryLink = By.className("link-alternative");
	private By inputFieldTot = By.cssSelector("input[name='filter_KoopprijsTot'][type='number']");
	private By inputFieldVan = By.cssSelector("input[name='filter_KoopprijsVan'][type='number']");
	private By AutoCompleteFirstOption = By.id("autocomplete-list-option1");

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public String getSearchBlockNavigationItemKoopName() {
		return readText(searchBlockNavigationItemKoop);
	}

	public String getSearchBlockNavigationItemHuurName() {
		return readText(searchBlockNavigationItemHuur);
	}

	public String getSearchBlockNavigationItemNieuwBouwName() {
		return readText(searchBlockNavigationItemNieuwBouw);
	}

	public String getSearchBlockNavigationItemRecreatieName() {
		return readText(searchBlockNavigationItemRecreatie);
	}

	public String getSearchBlockNavigationItemEuropeName() {
		return readText(searchBlockNavigationItemEurope);
	}

	public WebElement getFieldLocation() {
		return findElement(fieldLocation);
	}

	public boolean isFieldLocationPresent() {
		if (getFieldLocation() != null ) 
			return true;
		else
			return false;
	}

	public WebElement getFieldStraal() {
		return findElement(fieldStraal);
	}

	public boolean isFieldStraalPresent() {
		if (getFieldStraal() != null ) 
			return true;
		else
			return false;
	}
	
	public WebElement getFilterkoopprijsvan() {
		return findElement(filterKoopPrijsVan);
	}

	public boolean isFilterkoopprijsvanPresent() {
		if (getFilterkoopprijsvan() != null ) 
			return true;
		else
			return false;
	}
	
	public WebElement getFilterkoopprijstot() {
		return findElement(filterKoopPrijsTot);
	}

	public boolean isFilterkoopprijstotPresent() {
		if (getFilterkoopprijstot() != null ) 
			return true;
		else
			return false;
	}
	
	
	public boolean isButtonzoekPresent() {
		if (findElement(buttonZoek) != null ) 
			return true;
		else
			return false;
	}

	public void clickZoekButton() {
		click(buttonZoek);
	}

	public WebElement getArrowSelectVan() {
		return findElement(arrowSelectVan);
	}

	public void clickVanArrow() {
		click(arrowSelectVan);
	}

	public WebElement getLastquery() {
		return findElement(lastquery);
	}

	public WebElement getLastSearchCountryLink() {
		return findElement(lastSearchCountryLink);
	}

	public void clicklastSearchCountry() {
		click(lastSearchCountryLink);
	}

	public WebElement getInputFieldTot() {
		return findElement(inputFieldTot);
	}

	public WebElement getInputFieldVan() {
		return findElement(inputFieldVan);
	}

	public WebElement getAutoCompleteFirstOption() {
		return findElement(AutoCompleteFirstOption);
	}
}

package com.automationtest.pages;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class KoopBlockTest extends BaseTest {

	/**
	 * Validates that Location field is present
	 */
	@Test
	public void testLocationIsPresent() {
		assertTrue(getHomePage().isFieldLocationPresent());
	}

	/**
	 * Validates that Location field has the placeholder text as "Plaats, buurt, adres etc."
	 */
	@Test
	public void testLocationPlaceHolderIsDisplayed() {
		assertThat(getHomePage().getFieldLocation().getAttribute("placeholder"), is("Plaats, buurt, adres etc."));
	}

	/**
	 * Validates that distance range drop down is present
	 */
	@Test
	public void testDistanceRangeIsPresent() {
		assertTrue(getHomePage().isFieldStraalPresent());
	}

	/**
	 * Validates that default value displayed in distance range drop down is + 0 km
	 */
	@Test
	public void testDefaultValueInDistanceRangeIsDisplayed() {
		Select defaultValue = new Select(getHomePage().getFieldStraal());
		assertThat(defaultValue.getFirstSelectedOption().getText(), is("+ 0 km"));
	}

	/**
	 * Validates that Minimum price i.e Van field is present
	 */
	@Test
	public void testMinimumPriceVanIsPresent() {
		assertTrue(getHomePage().isFilterkoopprijsvanPresent());
	}

	/**
	 * Validates that default value displayed in Minimum price i.e Van is € 0
	 */
	@Test
	public void testDefaultValueInMinimumPriceVanIsDisplayed() {
		Select defaultValue = new Select(getHomePage().getFilterkoopprijsvan());
		assertThat(defaultValue.getFirstSelectedOption().getText(), is("€ 0"));
	}

	/**
	 * Validates that Maximum price i.e Tot field is present
	 */
	@Test
	public void testMaximumPriceTotIsPresent() {
		assertTrue(getHomePage().isFilterkoopprijstotPresent());
	}

	/**
	 * Validates that default value displayed in Maximum price i.e Tot is Geen maximum
	 */
	@Test
	public void testDefaultValueInMaximumPriceTotIsDisplayed() {
		Select defaultValue = new Select(getHomePage().getFilterkoopprijstot());
		assertThat(defaultValue.getFirstSelectedOption().getText(), is("Geen maximum"));
	}

	// Validates that Zoek button is present
	@Test
	public void testZoekIsPresent() {
		assertTrue(getHomePage().isButtonzoekPresent());
	}

	/**
	 * Validates that search results are returned for default values present in fields
	 */
	@Test
	public void testDefaultSearch() {
		getHomePage().clickZoekButton();
		wait.until(ExpectedConditions.urlContains("heel-nederland"));
		assertThat(getSearchResultPage().getSearchOutputResult(), containsString("resultaten"));
	}

	/**
	 * Validates that search with Invalid characters entered in location field stays on same page. *No warning is shown to the user in this case.
	 */
	@Test
	public void testValidtextAcceptedInLocationField() {
		HomePage page = getHomePage();
		page.getFieldLocation().sendKeys("Amstelveen#%%$!!");
		page.clickZoekButton();
		assertTrue(page.isButtonzoekPresent());
	}

	/**
	 * Validates that incorrect spelling entered in location results in zoeksuggestie page
	 */
	@Test
	public void testIncorrectSpellingInLocationField() {
		HomePage page = getHomePage();
		page.getFieldLocation().sendKeys("Amsfred");
		page.clickZoekButton();
		wait.until(ExpectedConditions.visibilityOf(page.findElement(By.className("autocomplete-no-suggestion-message"))));
		assertThat(page.findElement(By.className("autocomplete-no-suggestion-message")).getText(),
				is("Ai! Deze locatie kunnen we helaas niet vinden."));
		page.clickZoekButton();
		wait.until(ExpectedConditions.urlContains("zoeksuggestie"));
		
		assertThat(getZoekSuggestion().getZoekSuggestion(), containsString("niet vinden"));
	}

	/**
	 * Validates that search stays on same page for closest text entered in
	 * location and suggestion message "Bedoel je misschien..." is shown . * The suggestions in drop down stays visible when the search button is clicked twice.
	 */
	@Test
	public void testSuggestionTextForClosestMatchShownInLocationField() {
		HomePage page = getHomePage();
		page.getFieldLocation().sendKeys("Amstlveen");
		page.clickZoekButton();
		page.clickZoekButton();
		wait.until(ExpectedConditions.visibilityOf(page.findElement(By.className("autocomplete-suggestion-message"))));
		assertThat(page.findElement(By.className("autocomplete-suggestion-message")).getText(),
				is("Bedoel je misschien..."));
	}

	/**
	 * Validates that search results are returned as per chosen option from location drop down
	 */
	@Test
	public void testChooseFromDropdownInLocationField() throws InterruptedException {
		HomePage page = getHomePage();
		page.getFieldLocation().sendKeys("Amstelveen ");
		Thread.sleep(5000);
		WebElement selectedOption = page.getAutoCompleteFirstOption();
		selectedOption.click();
		Thread.sleep(3000);
		page.clickZoekButton();
		wait.until(ExpectedConditions.urlContains("koop"));
		assertThat(getSearchResultPage().getSearchOutputResult(), containsString("in koopwoningen"));
	}

	/**
	 * Validates that search results are returned as per chosen option from location drop down
	 */
	@Test
	public void testEnterNegativeNumberInVanAndersAmount() {
		HomePage page = getHomePage();
		Select dropdown = new Select(page.getArrowSelectVan());
		dropdown.selectByValue("other");
		page.getInputFieldVan().sendKeys("-1");
		page.clickZoekButton();
		assertTrue(page.isButtonzoekPresent());

	}

	/**
	 * Validates that Link Nederland in last search query returns results
	 */
	@Test
	public void testLastSearchProductTitlePresent() {
		HomePage page = getHomePage();
		testDefaultSearch();
		driver.get("https://www.funda.nl/");
		assertNotNull(page.getLastquery());
		page.clicklastSearchCountry();
		wait.until(ExpectedConditions.urlContains("heel-nederland"));
		assertThat(getSearchResultPage().getSearchOutputResult(), containsString("resultaten"));
	}

	/**
	 * Validates that Tot field turns Red and returns 0 search results on next page when value entered in Van>Tot
	 */
	@Test
	public void testValidateForVanGreaterThanTot() {
		HomePage page = getHomePage();
		Select dropdownVan = new Select(page.getArrowSelectVan());
		dropdownVan.selectByValue("75000");

		Select dropdownTot = new Select(page.getFilterkoopprijstot());
		dropdownTot.selectByValue("50000");

		// Tot field turns red when value is less than value in Van
		assertThat(page.getFilterkoopprijstot().getCssValue("border-bottom-color"), is("rgb(240, 60, 48)"));

		page.clickZoekButton();

		// 0 results are returned on next page
		wait.until(ExpectedConditions.urlContains("heel-nederland"));
		assertThat(getSearchResultPage().getSearchOutputResult(), containsString("0 resultaten"));

	}

	/**
	 * Validates that Decimal value entered in Van is rounded to 0 in search query
	 */
	@Test
	public void testDecimalValuesInVan() {
		HomePage page = getHomePage();
		Select dropdown = new Select(page.getArrowSelectVan());
		dropdown.selectByValue("other");
		// *Shows a scroll bar in Chrome and not in Firefox
		page.getInputFieldVan().sendKeys("1234.56");
		page.clickZoekButton();
		wait.until(ExpectedConditions.urlContains("heel-nederland"));
		Select dropdownVan = new Select(page.getArrowSelectVan());
		assertThat(dropdownVan.getFirstSelectedOption().getText(), is("€ 0"));

	}

	/**
	 * Validates that Decimal value entered in Tot is rounded to Geen maximum in search query
	 */
	@Test
	public void testDecimalValuesInTot() {
		HomePage page = getHomePage();
		Select dropdown = new Select(page.getFilterkoopprijstot());
		dropdown.selectByValue("other");
		page.getInputFieldTot().sendKeys("1234.56");
		page.clickZoekButton();
		wait.until(ExpectedConditions.urlContains("heel-nederland"));
		Select dropdownVan = new Select(page.getFilterkoopprijstot());
		assertThat(dropdownVan.getFirstSelectedOption().getText(), is("Geen maximum"));
	}
}

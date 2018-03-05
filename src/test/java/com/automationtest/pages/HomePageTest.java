package com.automationtest.pages;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class HomePageTest extends BaseTest{
	
	@Test
	public void testHomePageAppearCorrect() {
		HomePage homePage = getHomePage();
		
		// Verify home page search titles
		assertThat(homePage.getSearchBlockNavigationItemKoopName(), is("Koop"));
		assertThat(homePage.getSearchBlockNavigationItemHuurName(), is("Huur"));
		assertThat(homePage.getSearchBlockNavigationItemNieuwBouwName(), is("Nieuwbouw"));
		assertThat(homePage.getSearchBlockNavigationItemRecreatieName(), is("Recreatie"));
		assertThat(homePage.getSearchBlockNavigationItemEuropeName(), is("Europa"));
	}

}

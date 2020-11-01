package com.qa.insightly.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.insightly.base.BaseTest;
import com.qa.insightly.pages.HomePage;
import com.qa.insightly.utils.Constants;

public class HomePageTest extends BaseTest {
	
	//HomePage homePage;
	
	@BeforeClass
	public void homeSetup() {
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));	
	}
	
	@Test(priority=1)
	public void verifyHomePageTitle() {
		String title = homePage.getHomePageTitle();
		Assert.assertEquals(title, Constants.HOME_PAGE_TITLE,"Home page title is not matching...");
	}
	
	@Test(priority=2)
	public void verifyHomePageHeader() {
		String header =  homePage.getHomePageHeaderText();
		System.out.println("Home page header is :" +header);
		Assert.assertEquals(header, Constants.HOME_PAGE_HEADER, "Home page header is not matching...");
	}
	
	@Test(priority=3)
	public void verifyLoggedInUserTest() throws InterruptedException {
		String loggedInUser = homePage.getLoggedInUser();
		System.out.println("Logged In User is :" +loggedInUser);
		Assert.assertEquals(loggedInUser, prop.getProperty("accountname"), "logged in user is not matching...");
	}
	

}

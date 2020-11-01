package com.qa.insightly.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.insightly.base.BaseTest;
import com.qa.insightly.listeners.ExtentReportListener;
import com.qa.insightly.utils.Constants;

//@Listeners(ExtentReportListener.class)
public class LoginPageTest extends BaseTest {
	
	@Test(priority=1)
	public void verifyLoginPageTitleTest() {
		String title = loginPage.getLoginPageTitle();
		System.out.println("Login Page title is " +title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE, "Login Page title is not matching..");
	}
	
	
	@Test(priority=2)
	public void verifySignUpLinkTest() {
		Assert.assertTrue(loginPage.verifySignUpLink(), "SignUp Link is not displayed...");
	}
	
	
	@Test(priority=3)
	public void login() throws InterruptedException {
		loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	

	

}

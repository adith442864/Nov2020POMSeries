package com.qa.insightly.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.insightly.base.BasePage;
import com.qa.insightly.utils.Constants;
import com.qa.insightly.utils.ElementUtil;

public class LoginPage extends BasePage {
	
	public WebDriver driver;

	
	//1. Object Repository -- By locators.
	By email = By.name("email");
	By password = By.name("password");
	By loginButton = By.id("login-button");
	By signUpLink = By.linkText("Sign Up");
	
	//2. Create Constructor of Page Class
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		elementUtil = new ElementUtil(this.driver);
	}
	
	//3. Page Actions / Page Methods:
	
	public String getLoginPageTitle() {
		return elementUtil.waitForTitlePresent(Constants.LOGIN_PAGE_TITLE, 10);
	}
	
	
	public boolean verifySignUpLink() {
		//return driver.findElement(signUpLink).isDisplayed();
		return elementUtil.doIsDisplayed(signUpLink);
	}
	
	
	public HomePage doLogin(String un, String pwd)  {
		
		elementUtil.waitForElementToBeVisible(email, 10);
		elementUtil.doSendKeys(this.email, un);
		
		elementUtil.doSendKeys(this.password, pwd);
		elementUtil.doClick(this.loginButton);
		
		return new HomePage(driver);

	}
	
	
	
	
	
	
	
	

}

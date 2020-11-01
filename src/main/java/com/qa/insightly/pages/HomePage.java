package com.qa.insightly.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.qa.insightly.base.BasePage;
import com.qa.insightly.utils.Constants;
import com.qa.insightly.utils.ElementUtil;

public class HomePage extends BasePage {
	
	WebDriver driver;
//	ElementUtil elementUtil;
	
	// 1. By locator
	
	By header = By.id("btn-list-title");
	By accountName = By.className("upgrade-name");
	By userNameClick = By.id("user-menu-dd");
	
	By contactLink = By.xpath("//span[text()='Contacts']");
	
//	WebElement usernameClick = driver.findElement(By.id("user-menu-dd"));
	
	// 2. Constructor
	public HomePage(WebDriver driver) {
		this.driver=driver;
		elementUtil = new ElementUtil(this.driver);
		
	}
	
	// 3. Page Actions:
	
	public String getHomePageTitle() {
		return elementUtil.waitForTitlePresent(Constants.HOME_PAGE_TITLE, 15);
	}
	
	public String getHomePageHeaderText() {
		if(elementUtil.doIsDisplayed(header)) {
			return elementUtil.doGetText(header);
		}
		return null;
	}
	
	
	public String getLoggedInUser() throws InterruptedException {
		elementUtil.doClick(userNameClick);
		Thread.sleep(2000);
		if(elementUtil.doIsDisplayed(accountName)) {
			return elementUtil.doGetText(accountName);
		}
		return null;
	}
	
	public ContactsPage goToContactsPage() {
		clickOnContact();
		return new ContactsPage(driver);
	}
	
	private void clickOnContact() {
		elementUtil.waitForElementToBeClickable(contactLink, 15);
		elementUtil.doClick(contactLink);
		
		
	}

}

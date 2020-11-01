package com.qa.insightly.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.insightly.base.BasePage;
import com.qa.insightly.utils.Constants;
import com.qa.insightly.utils.ElementUtil;
import com.qa.insightly.utils.JavaScriptUtil;

public class ContactsPage extends BasePage {
	
	WebDriver driver;
	
	// 1. Page Objects
	By header = By.xpath("//div[text()='Contacts']");
	By createContact = By.xpath("//*[@id='AddNewButton']/a");
	
	//*[@id="AddNewButton"]/a
	By close = By.id("modal-close-tab");
	
	// Fill up details in a form:
	By firstName = By.name("FIRST_NAME");
	By lastName = By.name("LAST_NAME");
	By jobTitle = By.id("Contact_TITLE");
	By email = By.id("Contact_EMAIL_ADDRESS");
//	By mobileNumber = By.id("Contact_PHONE_MOBILE");

	By save = By.xpath("//button[@id='btn-save']");
	

	
	// 2. Constructor
	public ContactsPage(WebDriver driver) {
		this.driver=driver;
		elementUtil = new ElementUtil(this.driver);
		jsUtil = new JavaScriptUtil(this.driver);
		
	}
	
	// 3. Page Actions:
	
	public String getContactsPageTitle() {
		return elementUtil.waitForTitlePresent(Constants.CONTACTS_PAGE_TITLE, 10);
		
	}
	
	public String verifyContactsPageHeader() {
		elementUtil.waitForElementToBeVisible(header, 10);
		if(elementUtil.doIsDisplayed(header)) {
			return elementUtil.doGetText(header);	
		}
		return null;
	}
	
	
	public void createContact(String fn, String ln, String title, String mail) {
		
		elementUtil.waitForElementToBeClickable(createContact, 20);
		elementUtil.doClick(createContact);
		
	
		elementUtil.waitForElementPresent(firstName, 15);
		elementUtil.doSendKeys(firstName, fn);
		
		elementUtil.waitForElementPresent(lastName, 5);
		elementUtil.doSendKeys(lastName, ln);
		
		elementUtil.waitForElementPresent(jobTitle, 10);
		elementUtil.doSendKeys(jobTitle, title);
		
		elementUtil.waitForElementPresent(email, 5);
		elementUtil.doSendKeys(email, mail);
	
		elementUtil.waitForElementToBeVisible(save, 05);
		elementUtil.doActionsClick(save);
		
		elementUtil.waitForElementToBeVisible(close, 10);
		elementUtil.doClick(close);
		
	}
	
	
	
	
	
	
	
	
	

}

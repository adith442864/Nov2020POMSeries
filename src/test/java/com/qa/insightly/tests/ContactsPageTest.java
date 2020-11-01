package com.qa.insightly.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.insightly.base.BaseTest;
import com.qa.insightly.pages.ContactsPage;
import com.qa.insightly.pages.HomePage;
import com.qa.insightly.utils.Constants;
import com.qa.insightly.utils.ExcelUtil;

public class ContactsPageTest extends BaseTest{
	
	WebDriver driver;
	
	@BeforeClass
	public void homeSetup() {
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		contactsPage = homePage.goToContactsPage();
	}
	
	@Test(priority=1)
	public void verifyContactsPageTitle() {
		String title = contactsPage.getContactsPageTitle();
		System.out.println("Contacts Page title is :" +title);
		Assert.assertEquals(title, Constants.CONTACTS_PAGE_TITLE);
		
	}

	@Test(priority=2)
	public void verifyContactsPageHeader() {
		String header = contactsPage.verifyContactsPageHeader();
		System.out.println("contacts page header value is:" + header);
		Assert.assertEquals(header, Constants.CONTACTS_PAGE_HEADER);
	}
	
	@DataProvider
	public Object[][] getContactsTestData() {
		Object data[][] = ExcelUtil.getTestData(Constants.CONTACTS_SHEET_NAME);
		return data;
	}
	
	@Test(priority=3, dataProvider = "getContactsTestData")
	public void createContacts(String fn,String ln, String title, String mail) {
		contactsPage.createContact(fn, ln, title, mail);
	}
	
	
}

package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic-3: Design a Account Page  for demo cart application")
@Story("US:301:Account Page features account page :title, header, pagesections, search, product selection")
public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accPageSetUp() {
		accPage = loginPage.doLogin(prop.getProperty("emailAddress").trim(), prop.getProperty("password").trim());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Description("Account Page Title Test")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void accPageTitleTest() {
		String title = accPage.getAccountPageTitile();
		System.out.println("Account Page Title is ::" + title);
		Assert.assertEquals(title, Constants.ACC_PAGE_TITLE);
	}
	
	@Description("Account Page Header Test")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void accPageHeaderTest() {
		String header = accPage.getAccountPageHeader();
		System.out.println("Account Page Header is ::" + header);
		Assert.assertEquals(header, Constants.ACC_PAGE_HEADER);
	}
	
	@Description("Account Page Sections Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void accPageSectionsListTest() {
		List<String> secList = accPage.accountSectionsList();
		softAssert.assertEquals(secList.size(), Constants.ACC_PAGE_SECTIONS_COUNT);
		Assert.assertEquals(secList, Constants.EXPECTED_ACC_SECS_LIST);
		softAssert.assertAll();
	}

	@DataProvider
	public Object[][] productData() {
		return new Object[][] { { "iMac" }, { "MacBook" }, { "Apple" } };
	}

	@Description("Account Page Search Product Test")
	@Severity(SeverityLevel.BLOCKER)
	@Test(dataProvider = "productData")
	public void searchTest(String productName) {
		searchResultsPage = accPage.doSearch(productName);
		 Assert.assertTrue(searchResultsPage.getSerachProductListCount()>0);
	}

	@DataProvider
	public Object[][] productSelectData() {
		return new Object[][] { 
			{ "MacBook", "MacBook Pro" } 
			};
	}
	
	@Description("Account Page Select Searched Product Test")
	@Severity(SeverityLevel.BLOCKER)
	@Test(dataProvider = "productSelectData")
	public void selectProductTest(String mainProductName,String productName ) {
		searchResultsPage = accPage.doSearch(mainProductName);
		searchResultsPage.selectProduct(productName);

	}
}

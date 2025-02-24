package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic-4: Design a ProductInfo Page for demo cart application")
@Story("US:401:Account Page features account page :title, header, pagesections, search, product selection")
public class ProductInfoPageTest extends BaseTest{
	
	
	@BeforeClass
	public void productInfoPageSetUp() {
		accPage = loginPage.doLogin(prop.getProperty("emailAddress").trim(), prop.getProperty("password").trim());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Description("ProductInfo Page Header Test")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void productInfoHeaderTest() {
		searchResultsPage = accPage.doSearch("Macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Assert.assertEquals(productInfoPage.getProductHeaderText(),"MacBook Pro");
	}

	@Description("ProductInfo Page Product Images Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void productImagesTest() {
		searchResultsPage = accPage.doSearch("iMac");
		productInfoPage = searchResultsPage.selectProduct("iMac");
		Assert.assertEquals(productInfoPage.getProductImagesCount(), Constants.IMAC_IMAGE_COUNT);
	}
	
	@Description("ProductInfo Page Product Information Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void productInfoTest() {
		searchResultsPage = accPage.doSearch("Macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Map<String,String> actProductInfoMap = productInfoPage.getProductInfo();
		
		actProductInfoMap.forEach((k,v) -> System.out.println(k+ " : " + v  ));
		
		softAssert.assertEquals(actProductInfoMap.get("name"), "MacBook Pro");
		softAssert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actProductInfoMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(actProductInfoMap.get("Reward Points"), "800");
		softAssert.assertEquals(actProductInfoMap.get("Availability"), "In Stock");

		softAssert.assertTrue(actProductInfoMap.get("price").contains("2,000.00"));
		softAssert.assertAll();
		

		

		
	}
	
}

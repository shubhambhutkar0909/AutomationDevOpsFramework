package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic-1: Design a Registration Page  for demo cart application")
@Story("US:101:Registration Page features User Registration")
public class RegistrationPageTest extends BaseTest {

	@BeforeClass
	public void setUpRegistrationPage() {
		registerationPage = loginPage.navigateToRegistrationPage();
	}

	public String getRandomNumber() {
		Random randomGenrator = new Random();
		String email = "testAutomation" + randomGenrator.nextInt(1000) + "@testmails.com";
		//System.out.println(email);
		return email;
	}
	
	
	@DataProvider
	public Object[][] getRegisterData() {
		Object regData[][] = ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
		return regData;
	}
	
	
	@Description("Registration Page User Registration Test")
	@Severity(SeverityLevel.BLOCKER)
	@Test(dataProvider = "getRegisterData")
	public void userRegistrationTest(String firstName, String lastName, String password) {
		Assert.assertTrue(registerationPage.accounRegistration(firstName, lastName, getRandomNumber(), password));
	}

	
}

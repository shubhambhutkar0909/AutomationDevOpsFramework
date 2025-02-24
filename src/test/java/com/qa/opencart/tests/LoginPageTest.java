package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic-2: Design a login Page  for demo cart application")
@Story("US:201:Login Page Features with title, forgot password link and login feature")
public class LoginPageTest extends BaseTest {

	@Description("Login Page Title Test")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void loginPageTitleTest() {
		String title = loginPage.getLoginPageTitle();
		System.out.println("Login Page Title is::" + title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Description("Forgot Passworrd Link Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void forgotPwdLinkTest() {
		Assert.assertTrue(loginPage.isForgotPasswordLinkExist());
	}

	@Description("User Login Test")
	@Severity(SeverityLevel.BLOCKER)
	@Test
	public void loginTest() {
		loginPage.doLogin(prop.getProperty("emailAddress").trim(), prop.getProperty("password").trim());
	}

}

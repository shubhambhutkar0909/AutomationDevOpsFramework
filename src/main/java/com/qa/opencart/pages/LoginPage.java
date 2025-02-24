package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil elementUtil;

	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginButton = By.xpath("//button[contains(text(),'Login')]");
	private By forgotPasswordLink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}
	
	@Step("Getting Login Page Title")
	public String getLoginPageTitle() {
		return elementUtil.getPageTitle(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);

	}
	
	@Step("Checking Forgot password link exist or not")
	public boolean isForgotPasswordLinkExist() {
		return elementUtil.doIsDisplayed(forgotPasswordLink);

	}
	
	@Step("Login to app with userEmail: {0} and password: {1}")
	public AccountsPage doLogin(String emailAddress, String pwd) {

		elementUtil.doPresenceOfElementLocated(emailId, Constants.DEFAULT_TIME_OUT).sendKeys(emailAddress);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginButton);
		return new AccountsPage(driver);
	}
	
	/**
	 * Navigate to Register Page
	 * @return 
	 */
	@Step("Navigating to Registeration page")
	public RegistrationPage navigateToRegistrationPage() {
		elementUtil.doClick(registerLink);
		return new RegistrationPage(driver);
	}
	
	
}

package com.qa.opencart.pages;

import org.apache.commons.math3.analysis.function.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.JavaScriptUtil;

public class RegistrationPage {

	private WebDriver driver;
	private ElementUtil elementUtil;

	private By firstNameTxt = By.id("input-firstname");
	private By lastNameTxt = By.id("input-lastname");
	private By emailIdTxt = By.id("input-email");
	private By passwordTxt = By.id("input-password");
	private By subnewsLetterBtn = By.xpath("//input[@type='checkbox' and @id='input-newsletter']");
	private By privacyPolicyBtn = By.xpath("//input[@type='checkbox' and @name='agree']");
	private By continueBtn = By.xpath("//button[@type='submit' and text()='Continue']");

	private By successMsg = By.cssSelector("div#content h1");
	private By logOutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);

	}

	public boolean accounRegistration(String firstName, String lastName, String emailD, String password) {

		elementUtil.doSendKeys(firstNameTxt, firstName);
		elementUtil.doSendKeys(lastNameTxt, lastName);
		elementUtil.doSendKeys(emailIdTxt, emailD);
		elementUtil.doSendKeys(passwordTxt, password);

		elementUtil.doActionsClick(subnewsLetterBtn);
		elementUtil.doActionsClick(privacyPolicyBtn);
		elementUtil.doClick(continueBtn);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String msg = elementUtil.doVisibilityOfElement(successMsg, Constants.REGISTRATION_PAGE_TIME_OUT).getText();

		if (msg.contains(Constants.REGISTER_SUCCESS_MEG)) {
			elementUtil.waitForElementToBeClickable(logOutLink, Constants.DEFAULT_TIME_OUT);

			if (elementUtil.doIsDisplayed(logOutLink)) {
				elementUtil.doActionsClick(logOutLink);
			}
			elementUtil.waitForElementToBeClickable(registerLink, Constants.DEFAULT_TIME_OUT);
			elementUtil.doActionsClick(registerLink);

			return true;
		} else {
			return false;
		}

	}

}

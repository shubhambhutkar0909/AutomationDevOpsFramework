package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil elementUtil;

	private By accSections = By.cssSelector("div#content h2");
	private By header = By.cssSelector("div#logo a");
	private By logoutLink = By.linkText("Logout");
	private By searchBox = By.name("search");
	private By searchButton = By.cssSelector("div#search button");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}

	public String getAccountPageTitile() {
		return elementUtil.getPageTitle(Constants.ACC_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);

	}

	public boolean getAccountPageURL() {
		return elementUtil.waitForURL("route=account", Constants.DEFAULT_TIME_OUT);
	}

	public String getAccountPageHeader() {
		return elementUtil.doGetText(header);
	}

	public List<String> accountSectionsList() {
		List<String> accSecValList = new ArrayList<String>();
		List<WebElement> accSecList = elementUtil.waitForVisibilityOfElements(accSections, Constants.DEFAULT_TIME_OUT);

		for (WebElement e : accSecList) {
			accSecValList.add(e.getText());
		}
		return accSecValList;

	}

	public boolean isLogoutLinkExist() {
		return elementUtil.doIsDisplayed(logoutLink);
	}

	public void logOut() {
		if(isLogoutLinkExist()) {
			elementUtil.doClick(logoutLink);
		}
	}
	
	public SearchResultsPage doSearch(String productName) {
		System.out.println("Searching the Product::" + productName);
		elementUtil.doSendKeys(searchBox, productName);
		elementUtil.doClick(searchButton);
		return new SearchResultsPage(driver);
	}

}

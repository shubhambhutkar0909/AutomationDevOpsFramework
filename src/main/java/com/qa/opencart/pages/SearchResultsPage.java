package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.JavaScriptUtil;

public class SearchResultsPage {

	private WebDriver driver;
	private ElementUtil elementUtil;
	private JavaScriptUtil jsUtil;

	private By serachHeaderName = By.cssSelector("div#content h1");
	private By productResults = By.cssSelector("div.content a");

	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
		jsUtil = new JavaScriptUtil(this.driver);
	}

	public String getSerchHeaderName() {
		return elementUtil.doGetText(serachHeaderName);
	}

	public int getSerachProductListCount() {
		return elementUtil.waitForVisibilityOfElements(productResults, Constants.DEFAULT_TIME_OUT).size();
	}

	public ProductInfoPage selectProduct(String mainProductName)  {
		List<WebElement> searchList = elementUtil.waitForVisibilityOfElements(productResults,
				Constants.DEFAULT_TIME_OUT);
		
		for (WebElement e : searchList) {
			String text = e.getText();
			if (text.equalsIgnoreCase(mainProductName)) {
				jsUtil.clickElementByJS(e);
				break;
			}
		}
		return new ProductInfoPage(driver);
	}

}

package com.qa.opencart.pages;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil elementUtil;

	private By productHeader = By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("div.image.magnific-popup a");
	private By productMetaData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	// private By productMetaDataBrand = By.cssSelector("div#content
	// ul.list-unstyled:nth-of-type(1) li a");
	private By productPriceData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");

	private By quantity = By.id("input-quantity");
	private By addToCartBtn = By.id("button-cart");

	Map<String, String> productInfoMap;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}

	public String getProductHeaderText() {
		return elementUtil.doGetText(productHeader).trim();
	}

	public int getProductImagesCount() {
		return elementUtil.waitForVisibilityOfElements(productImages, Constants.DEFAULT_TIME_OUT).size();
	}

	public Map<String, String> getProductInfo() {
		productInfoMap = new LinkedHashMap<String, String>();
		productInfoMap.put("name", getProductHeaderText());
		getProductMetaData();
		getProductPricingData();
		return productInfoMap;

	}

	private void getProductMetaData() {
		List<WebElement> metaDataList = Optional.ofNullable(elementUtil.getElements(productMetaData))
				.orElse(Collections.emptyList());

		if (metaDataList.isEmpty()) {
			System.out.println("No metadata found.");
			return;
		}

		System.out.println("Total Meta Data::" + metaDataList.size());
//
//		if (productInfoMap == null) {
//			productInfoMap = new LinkedHashMap<>();
//		}

		for (WebElement e : metaDataList) {
			String text = e.getText();
			if (!text.contains(":")) {
				System.out.println("Invalid metadata format: " + text);
				continue;
			}
			String[] metaProduct = text.split(":", 2);
			String metaProductKey = metaProduct[0].trim();
			String metaProductVal = metaProduct[1].trim();
			productInfoMap.put(metaProductKey, metaProductVal);
		}
	}

	private void getProductPricingData() {

		List<WebElement> priceDataList = elementUtil.getElements(productPriceData);
		System.out.println("Total Meta Data::" + priceDataList.size());

		String price = priceDataList.get(0).getText().trim();
		String exPrice = priceDataList.get(1).getText().trim();

		productInfoMap.put("price", price);
		productInfoMap.put("exPrice", exPrice);
	}

}

package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * @author : Shubham Bhutkar
 */
public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public static String highlight;
	public OptionsManager optionsManager;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	/**
	 * This Method will return the driver
	 * 
	 * @param browser
	 * @return
	 */

	public WebDriver init_driver(Properties prop) {

		String browser = prop.getProperty("browser").trim();
		System.out.println("Browser name is::" + browser);

		highlight = prop.getProperty("highlight");
		optionsManager = new OptionsManager(prop);

		if (browser.equalsIgnoreCase("chrome")) {
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteDriver("chrome");
			} else {
				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));

			}

		} else if (browser.equalsIgnoreCase("firefox")) {
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteDriver("firefox");
			} else {

				tlDriver.set(new FirefoxDriver(optionsManager.getFireFoxOptions()));
			}

		} else if (browser.equalsIgnoreCase("edge")) {
			tlDriver.set(new EdgeDriver());

		} else {
			System.out.println("Please pass the right browser.." + browser);
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url").trim());

		return getDriver();
	}

	private void init_remoteDriver(String browserName) {

		System.out.println("Running Test on Remote Envoironment" + browserName);
		if (browserName.equalsIgnoreCase("chrome")) {
			ChromeOptions chromeOptions = optionsManager.getChromeOptions();

			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), chromeOptions));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		} else if (browserName.equalsIgnoreCase("firefox")) {
			FirefoxOptions firefoxOptions = optionsManager.getFireFoxOptions();

			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), firefoxOptions));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}

	}

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * This Method will be used to Intialize Property
	 * 
	 * @return
	 */
	public Properties init_prop() {
		prop = new Properties();
		FileInputStream ip = null;
		String env = System.getProperty("env");
		if (env == null) {
			System.out.println("We are running on Pre-Production env:: QA Env");
			try {
				ip = new FileInputStream(
						System.getProperty("user.dir") + "/src/test/resource/config/config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Running on Environment::" + env);
			try {
				switch (env) {
				case "qa":
					ip = new FileInputStream(
							System.getProperty("user.dir") + "/src/test/resource/config/qa.config.properties");
					break;
				case "dev":
					ip = new FileInputStream(
							System.getProperty("user.dir") + "/src/test/resource/config/dev.config.properties");
					break;
				case "stage":
					ip = new FileInputStream(
							System.getProperty("user.dir") + "/src/test/resource/config/stage.config.properties");
					break;

				default:
					break;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return prop;
	}

	public String getScreenShot() {

		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);

		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}

}

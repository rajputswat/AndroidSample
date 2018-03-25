package appium;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BaseAndroidTest extends BaseTest {

	@Override
	protected void setAppiumDriver() throws IOException {
		logger.debug("Setting up AndroidDriver");
		this.wd = new AndroidDriver<MobileElement>(new URL(getAppiumServerAddress() + "/wd/hub"),
				capabilities);
		wd.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}


	@Override
	protected String getDesiredCapabilitiesPropertiesFileName() {

		return "desiderCapabilities";
	}
	@BeforeMethod
	public void setUp() throws Exception {
		setUpTest();
		login("flight@gmail.com","mradu123");
	}
	@Attachment("Screenshot on failure")
	public byte[] makeScreenshotOnFailure() {
		logger.debug("Taking screenshot");
		return  ((TakesScreenshot) wd).getScreenshotAs(OutputType.BYTES);

		//return ((TakesScreenshot) .getScreenshotAs(OutputType.BYTES);
	}
	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result)
	{
		makeScreenshotOnFailure();
		try
		{
			if (result.getStatus() == ITestResult.FAILURE)
			{

				takeScreenshot(getClass().getSimpleName());
			}


		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		wd.closeApp();
		wd.quit();
	}
	/**
	 * give total window height and width
	 * @return
	 */
	public List getWindowSize()
	{
		Integer y=wd.manage().window().getSize().getHeight();
		Integer x=wd.manage().window().getSize().getWidth();
		ArrayList<Integer> cordinates=new ArrayList();
		cordinates.add(y);
		cordinates.add(x);
		return cordinates;
	}
	public void horizintalSwipe(Integer Y,Integer x)
	{
		wd.swipe(x-50,Y-50,10,Y-50,500);
	}
	/**
	 * login
	 */
	public void login(String username,String password)throws Exception
	{
		int count=0;
		Thread.sleep(1000);
		while (count<5) {
			horizintalSwipe(Integer.parseInt(getWindowSize().get(0).toString()), Integer.parseInt(getWindowSize().get(1).toString()));
			count++;
		}
		wd.findElement(By.id("blibli.mobile.commerce:id/login_button")).click();
		Thread.sleep(3000);
		wd.findElement(By.id("blibli.mobile.commerce:id/et_user_email_id")).sendKeys(username);
		wd.findElement(By.id("blibli.mobile.commerce:id/et_user_password")).sendKeys(password);
		wd.findElement(By.id("blibli.mobile.commerce:id/bt_login")).click();
	}
	/**
	 * add text to be scrolled
	 * @param text
	 */
	public void scroll( String text)
	{

		wd.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(" + "new UiSelector().text(\""+text+"\"));"));


	}

	/**
	 * checks if an element is present or not
	 * @param by
	 * @return
	 */
	public boolean isElementPresent(By by) {
		try {
			wd.findElements(by);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

}

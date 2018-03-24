package appium;


import io.appium.java_client.AppiumDriver;
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
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BaseAndroidTest extends BaseTest {

	@Override
	protected void setAppiumDriver() throws IOException {
	    logger.debug("Setting up AndroidDriver");
		this.wd = new AndroidDriver<MobileElement>(new URL(getAppiumServerAddress() + "/wd/hub"),
				capabilities);
	}


    @Override
    protected String getDesiredCapabilitiesPropertiesFileName() {

            return "desiredCapabilities.android.clientside.properties";
    }
	@BeforeClass
	public void setUp() throws Exception {
		setUpTest();
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

		// driver.closeApp();
		// driver.quit();
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
//	/*
//       verify if element is present or not
//    */
//	protected boolean isElementPresent(String element)
//	{
//		try
//		{
//			if(wd.findElement(element) == null)
//			{
//				return false;
//			};
//
//			return true;
//		}
//		catch (Exception e)
//		{
//			return false;
//		}
//	}

}

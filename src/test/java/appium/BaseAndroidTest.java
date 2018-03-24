package appium;


import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import java.io.IOException;
import java.net.URL;

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
}

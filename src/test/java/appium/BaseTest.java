package appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;
import java.util.Set;

public abstract class BaseTest {
    private static final String LOCAL_APPIUM_ADDRESS = "http://localhost:4723";
    protected AppiumDriver driver;
    protected DesiredCapabilities capabilities;
    protected static Logger logger = LoggerFactory.getLogger(BaseTest.class);
    protected static String AppPath="/Users/17787/Documents";
    protected static String AppName="Blibli-App-for-Android-4.5.0-(1025).apk";


    public void setUpTest() throws IOException {
        setUpAppiumDriver();
    }

    public void setUpAppiumDriver() throws IOException {
        DesiredCapabilities desiredCapabilities = getDesiredCapabilitiesFromProperties();
        this.capabilities = desiredCapabilities;
        File appDir = new File(AppPath);
        File app = new File(appDir, AppName);
        capabilities.setCapability("app", app.getAbsolutePath());
        setAppiumDriver();
    }


    private DesiredCapabilities getDesiredCapabilitiesFromProperties() {
        logger.debug("Setting desiredCapabilities defined in " + getDesiredCapabilitiesPropertiesFileName());
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        Properties desiredCapabilitiesProperties = fetchProperties(getDesiredCapabilitiesPropertiesFileName());
        Set<String> keys = desiredCapabilitiesProperties.stringPropertyNames();
        for (String key : keys) {
            String value = desiredCapabilitiesProperties.getProperty(key);
            desiredCapabilities.setCapability(key, value);
        }
        return desiredCapabilities;
    }

    protected abstract void setAppiumDriver() throws IOException;

    private Properties fetchProperties(String filename) {
        Properties properties = new Properties();
        InputStream input = null;
        try {
            input = getClass().getClassLoader().getResourceAsStream(filename);
            if (input == null) {
                logger.error("Sorry, unable to find " + filename);
                throw new FileNotFoundException("Unable to find/open file: " + filename);
            }
            properties.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return properties;
    }

    protected abstract String getDesiredCapabilitiesPropertiesFileName();


    protected String getAppiumServerAddress() {
        return LOCAL_APPIUM_ADDRESS;
    }


    protected File takeScreenshot(String screenshotName) {
        String fullFileName = System.getProperty("user.dir") + "/screenshots/" + screenshotName + ".png";
        logger.debug("Taking screenshot...");
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            File testScreenshot = new File(fullFileName);
            FileUtils.copyFile(scrFile, testScreenshot);
            logger.debug("Screenshot stored to " + testScreenshot.getAbsolutePath());

            return testScreenshot;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

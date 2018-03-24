package appium.android.sample;


import appium.BaseAndroidTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class AndroidAppiumExampleTest extends BaseAndroidTest{

    @BeforeClass
    public void setUp() throws Exception {
        setUpTest();
    }
    @AfterClass
    public void tearDown()
    {
        wd.closeApp();
        wd.quit();

    }


    @Test
    public void mainPageTest() throws IOException, InterruptedException {
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        takeScreenshot("start");
        wd.findElement(By.id("com.myntra.android:id/tv_skip")).click();

        wd.findElement(By.id("com.myntra.android:id/tv_permissions_skip")).click();

        wd.findElementByAccessibilityId("leftElement").click();
        takeScreenshot("end");
        Assert.assertTrue(true);

    }

}

package appium.android.sample;


import appium.BaseAndroidTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AndroidAppiumExampleTest extends BaseAndroidTest{

    @Test
    public void HappyFlow() throws IOException, InterruptedException
    {
        takeScreenshot("start");
        Assert.assertTrue(isElementPresent(By.id("blibli.mobile.commerce:id/iv_blibli_logo")),"user not loggedin");
        scroll("Pulsa");
        wd.findElements(By.id("blibli.mobile.commerce:id/tv_digital_prodt_text")).get(0).click();
        Assert.assertTrue(isElementPresent(By.id("blibli.mobile.commerce:id/ll_phone_number")),"error message");

    }



}

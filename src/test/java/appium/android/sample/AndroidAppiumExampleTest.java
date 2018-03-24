package appium.android.sample;


import appium.BaseAndroidTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AndroidAppiumExampleTest extends BaseAndroidTest{




    @Test
    public void mainPageTest() throws IOException, InterruptedException
    {
        wd.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        takeScreenshot("start");
        int count=0;
        Thread.sleep(2000);
        while (count<5) {
            horizintalSwipe(Integer.parseInt(getWindowSize().get(0).toString()), Integer.parseInt(getWindowSize().get(1).toString()));
            count++;
        }
        wd.findElement(By.id("blibli.mobile.commerce:id/get_started_button")).click();
        wd.findElement(By.id("blibli.mobile.commerce:id/tv_search")).click();
        wd.findElement(By.id("blibli.mobile.commerce:id/av_key_search")).sendKeys("wheatgrass"+"\n");
        wd.findElements(By.id("blibli.mobile.commerce:id/auto_search_keyword")).get(0).click();
        takeScreenshot("end");
        Assert.assertTrue(true);
    }

}

package com.yourcompany;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testobject.appium.junit.TestObjectTestResultWatcher;
import org.testobject.rest.api.appium.common.TestObject;
import org.testobject.appium.junit.TestObjectAppiumSuite;
import io.appium.java_client.android.AndroidDriver;
import java.net.URL;
import java.util.List;
import org.openqa.selenium.OutputType;


@TestObject(testObjectApiKey = System.getEnv("TESTOBJECT_API_KEY"), testObjectSuiteId = System.getEnv("BUILD_TAG"))
@RunWith(TestObjectAppiumSuite.class)
public class SampleSauceTest {

    @Rule
    public TestName testName = new TestName();

    @Rule
    public TestObjectTestResultWatcher resultWatcher = new TestObjectTestResultWatcher();
    
    private AndroidDriver driver;

    @Before
    public void setUp() throws Exception {
   
    	
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("testobject_api_key", resultWatcher.getApiKey());
        capabilities.setCapability("testobject_test_report_id", resultWatcher.getTestReportId());

        driver = new AndroidDriver(new URL("http://appium.testobject.com/wd/hub"), capabilities);

        resultWatcher.setAppiumDriver(driver);

    }

    @Test
    public void addContactTest() {
    	
        WebElement button = driver.findElement(By.className("android.widget.Button"));
        button.click();
        driver.getScreenshotAs(OutputType.FILE);
        List<WebElement> textFieldsList = driver.findElements(By.className("android.widget.EditText"));
        textFieldsList.get(0).sendKeys("Some Name");
        textFieldsList.get(2).sendKeys("Some@example.com");
        driver.getScreenshotAs(OutputType.FILE);
        button.click();
        driver.getScreenshotAs(OutputType.FILE);
    }

}

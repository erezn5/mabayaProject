package com.mabaya.automation.tests.ui;

import com.mabaya.model.conf.EnvConf;
import com.mabaya.model.pages.MabayaLoginPage;
import com.mabaya.model.selenium.Browser;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;


public class BaseTest {
    protected WebDriver driver;
    private static final Browser BROWSER = Browser.valueOf(EnvConf.getProperty("ui.browser.type"));
    protected MabayaLoginPage mabayaLoginPage;

    @BeforeMethod(alwaysRun = true)
    public final void navigate() {
        mabayaLoginPage.navigateAndVerify();
    }

    @BeforeClass(alwaysRun = true)
    public final void baseUiSetup(){
        driver = open(BROWSER);
        mabayaLoginPage = new MabayaLoginPage(driver);
    }

    @AfterClass(alwaysRun = true)
    public final void baseUiTearDown(){
        if(driver != null){
            driver.quit();
        }
    }

    public WebDriver open(Browser browser) {
        switch (browser) {
            case FIREFOX:
                return createFireFoxInst();
            case CHROME:
                return createChromeInst();
            default:
                throw new IllegalArgumentException("'" + browser + "'no such browser type");
        }
    }

    private WebDriver createChromeInst() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        options.setHeadless(EnvConf.getAsBoolean("selenium.headless"));
        options.addArguments("--window-size=" + EnvConf.getProperty("selenium.window_size"));

        options.addArguments("--lang=" + EnvConf.getProperty("selenium.locale"));
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.SEVERE);
        options.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        ChromeDriverService service = ChromeDriverService.createDefaultService();
        driver = new ChromeDriver(service, options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    private WebDriver createFireFoxInst() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.setAcceptInsecureCerts(true);
        options.setHeadless((EnvConf.getAsBoolean("selenium.headless")));
        options.addArguments("--window-size=1920,1080");
        driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }
}




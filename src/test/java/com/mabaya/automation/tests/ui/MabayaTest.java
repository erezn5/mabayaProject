package com.mabaya.automation.tests.ui;

import com.mabaya.model.api.SimpleHttpClient;
import com.mabaya.model.conf.JsonHandler;
import com.mabaya.model.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;

public class MabayaTest extends BaseTest{
    private SimpleHttpClient simpleHttpClient;
    private HashMap<String, String> headersMap = new HashMap<>();

    @BeforeClass(alwaysRun = true)
    public void testSetup(){
        simpleHttpClient = new SimpleHttpClient();
        headersMap.put("Content-Type", "application/json");
    }

    @Test(dataProvider = "loginDetailsNegativeProvider", priority = 10)
    public void loginNegativeTest(String email, String password, String errorMsg){
        Assert.assertEquals(mabayaLoginPage.login(email, password).returnErrorInvalidLoginMsg(), errorMsg);
    }

    @DataProvider(name = "loginDetailsNegativeProvider")
    public Object[][] loginDetailsNegativeProvider(){
        return new Object[][] {
                {"interviewtestuser@mabaya.com", "goodLuck!", "Invalid username or password"},
                {"interviewtestuser3005@mabaya.com", "goodLuck", "Invalid username or password"}
        };
    }

    @Test(dataProvider = "loginDetailsProvider", priority = 20)
    public void loginTest(String email, String password){
        HomePage homePage = new HomePage(driver);
        mabayaLoginPage.login(email, password);
        Assert.assertTrue(homePage.verifyHomePageLogo());
    }

    @DataProvider(name = "loginDetailsProvider")
    public Object[][] loginDetailsProvider(){
        return new Object[][] {
                {"interviewtestuser3005@mabaya.com", "goodLuck!"}
        };
    }

    @Test(priority = 30)
    public void sendGetTest() throws IOException {
        long before = System.currentTimeMillis();
        String res = simpleHttpClient.sendGetRequest
                ("https://17eac024-8de4-4e85-982e-4f95d52cedd2.mock.pstmn.io/server2server/ad.json?storeHash=cd8def3ab0e5bffccf6aaab750dad84b&signal=Hanglampen&widgets=product_page_w1&pageType=search&cookie=mbinterviewest",
                        headersMap);
        System.out.println(res);
        long after = System.currentTimeMillis();
        long elapsedTime = after - before;
        Assert.assertTrue(elapsedTime >= 1);
        Assert.assertTrue(JsonHandler.verifyValidJson(res));
    }

}

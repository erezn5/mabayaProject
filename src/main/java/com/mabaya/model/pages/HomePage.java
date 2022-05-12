package com.mabaya.model.pages;

import com.mabaya.model.infra.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    @FindBy(id = "backoffice-logo")
    private WebElement backOfficeLogoElm;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean verifyHomePageLogo(){
        return backOfficeLogoElm.isDisplayed();
    }
}

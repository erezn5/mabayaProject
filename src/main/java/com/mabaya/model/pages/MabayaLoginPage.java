package com.mabaya.model.pages;

import com.mabaya.model.infra.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MabayaLoginPage extends BasePage {
    @FindBy(id = "auth-login-email")
    private WebElement emailTxtElm;
    @FindBy(id = "auth-login-password")
    private WebElement passwordTxtElm;
    @FindBy(css = "button[type='submit']")
    private WebElement submitBtnElm;
    @FindBy(css = "div[class='form-group control-error'] div")
    private WebElement errorMsgTxtElm;

    public MabayaLoginPage(WebDriver driver) {
        super(driver);
    }

    public String returnErrorInvalidLoginMsg(){
        return errorMsgTxtElm.getText();
    }

    private MabayaLoginPage setEmail(String email){
        emailTxtElm.sendKeys(email);
        return this;
    }

    private MabayaLoginPage setPassword(String email){
        passwordTxtElm.sendKeys(email);
        return this;
    }

    private MabayaLoginPage clickSubmit(){
        submitBtnElm.click();
        return this;
    }

    public MabayaLoginPage login(String email, String password){
        setEmail(email).setPassword(password).clickSubmit();
        return this;
    }

}

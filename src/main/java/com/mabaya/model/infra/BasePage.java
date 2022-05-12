package com.mabaya.model.infra;

import com.mabaya.model.conf.EnvConf;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage extends PageElements {
    private final String url;

    protected final static String URL_ADDRESS= EnvConf.getProperty("base.url");

    public BasePage(WebDriver driver){
        super(driver);
        this.url = URL_ADDRESS;
        PageFactory.initElements(driver, this);
    }

    private void navigate(){
        driver.get(url);
        System.out.printf("Navigate to url=[%s]%n", url);
    }

    private void refresh(){
        System.out.printf("refresh url '%s'", driver.getCurrentUrl());
        driver.navigate().refresh();
    }

    public void navigateAndVerify(){
        if(url.equals(driver.getCurrentUrl())){
            refresh();
        }else{
            navigate();
        }
        System.out.print("navigation succeeded");
    }
}



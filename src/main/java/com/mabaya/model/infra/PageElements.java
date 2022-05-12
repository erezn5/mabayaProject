package com.mabaya.model.infra;

import org.openqa.selenium.WebDriver;

public class PageElements {
    protected final WebDriver driver;

    protected PageElements(WebDriver driver) {
        this.driver = driver;
    }
}

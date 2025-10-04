package org.example;

import locators.FilterPageLocators;
import locators.ProductPageLocators;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;


public class FilterPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public FilterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickPriceFilter() {
        WebElement brandElement = wait.until(ExpectedConditions.elementToBeClickable
                (FilterPageLocators.priceFilterLocator));
        brandElement.click();
    }

    public void setMinPriceValue(String price) {
        WebElement minPriceField = wait.until(ExpectedConditions.elementToBeClickable
                (FilterPageLocators.minPriceInput));
        minPriceField.clear();
        minPriceField.sendKeys(price);
    }

    public void setMaxPriceValue(String price) {
        WebElement minPriceField = wait.until(ExpectedConditions.elementToBeClickable
                (FilterPageLocators.maxPriceInput));
        minPriceField.clear();
        minPriceField.sendKeys(price);
    }

    public SearchItemPage itemPriceFilterApply() {
        WebElement priceFilterButton = wait.until(ExpectedConditions.elementToBeClickable
                (FilterPageLocators.priceFilterApplyButton));
        priceFilterButton.click();
        return new SearchItemPage(driver);
    }

    public void assertNoResultsMessage() {
        WebElement messageElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(ProductPageLocators.noResultsMessage)
        );
        Assert.assertEquals(messageElement.getText(),
                "We couldn't find what you were looking for");
    }
}

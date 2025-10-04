package org.example;

import locators.ProductPageLocators;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {
    private final WebDriver driver;
    private final Actions actions;
    private final WebDriverWait wait;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void changeLanguage() {
        WebElement languageButton = wait.until(ExpectedConditions.elementToBeClickable(
                ProductPageLocators.changeLanguageButton));
        languageButton.click();

        wait.until(ExpectedConditions.attributeContains(
                ProductPageLocators.searchBar, "placeholder", "What are you looking for"
        ));
    }


    public void navigateCategory(String categoryName) {
                wait.until(ExpectedConditions.presenceOfElementLocated
                        (ProductPageLocators.getCategoryLocator(categoryName)));
        actions.moveToElement(driver.findElement(ProductPageLocators.getCategoryLocator(categoryName)))
                .build()
                .perform();
    }

    public FilterPage selectBrand(String brandName) {
        WebElement brandElement = wait.until(ExpectedConditions.elementToBeClickable
                (ProductPageLocators.getBrandLocator(brandName)));
        brandElement.click();
        return new FilterPage(driver);
    }

    public FilterPage searchItem(String itemToSearch) {
        WebElement searchBar = wait.until(
                ExpectedConditions.elementToBeClickable(ProductPageLocators.searchBar));

        searchBar.sendKeys(itemToSearch, Keys.ENTER);
        return new FilterPage(driver);
    }
}
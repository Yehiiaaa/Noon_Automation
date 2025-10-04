package org.example;

import locators.CartPageLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CartPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void assertItemsName(List<String> itemsName) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(CartPageLocators.itemsName));

        List<WebElement> cartElements = driver.findElements(CartPageLocators.itemsName);

        List<String> actualItems = cartElements.stream()
                .map(WebElement::getText)
                .map(String::trim)
                .toList();

        List<String> expectedItems = itemsName.stream()
                .map(String::trim)
                .toList();

        Set<String> expectedSet = Set.copyOf(expectedItems);
        Set<String> actualSet = Set.copyOf(actualItems);

        Assert.assertEquals(actualSet, expectedSet);
    }

    public void proceedToCheckout (){
        WebElement checkoutBtn = wait.until(ExpectedConditions
                .elementToBeClickable(CartPageLocators.checkoutButton));
        checkoutBtn.click();

        WebElement header = wait.until(ExpectedConditions
                .visibilityOfElementLocated(CartPageLocators.checkoutMessage));

        Assert.assertTrue(header.isDisplayed());

    }

}

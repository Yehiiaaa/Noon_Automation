package org.example;

import locators.FilterPageLocators;
import locators.ProductPageLocators;
import locators.SearchItemLocators;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SearchItemPage {
    private final WebDriver driver;
    private final WebDriverWait wait;


    public SearchItemPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void sortDropMenu() {
        wait.until(ExpectedConditions.elementToBeClickable
                (SearchItemLocators.filteredItems));
        WebElement sortButton = wait.until(ExpectedConditions.visibilityOfElementLocated
                (FilterPageLocators.sortButton));
        sortButton.click();
    }

    public void sortSelection(String sortType) {
        List<WebElement> sortOptions = driver.findElements(FilterPageLocators.sortTypeOptions);
        sortOptions.stream()
                .filter(option -> option.getText().equals(sortType))
                .findFirst()
                .ifPresent(WebElement::click);
    }

    public void assertOnFilterCondition(String minPrice, String maxPrice){
        int min = Integer.parseInt(minPrice);
        int max = Integer.parseInt(maxPrice);

        smoothScrollPage();
        List<Integer> prices = driver.findElements(SearchItemLocators.filteredItemsPrice).stream()
                .map(WebElement::getText)
                .map(String::trim)
                .map(s -> s.replace(",", ""))
                .map(Integer::parseInt)
                .toList();

        prices.forEach(price ->
                Assert.assertTrue(price >= min && price <= max));
    }


    public List<String> selectThreeItems()  {
        smoothScrollPage();
        wait.until(ExpectedConditions
                .visibilityOfAllElementsLocatedBy(SearchItemLocators.addItemToCartButton));
        wait.until(ExpectedConditions
                .elementToBeClickable(SearchItemLocators.addItemToCartButton));
        List<WebElement> allItems = driver.findElements(SearchItemLocators.itemsName);
        List<String> selectedTitles = new ArrayList<>();
        for (int i = 0; i < 3 && i < allItems.size(); i++) {
            WebElement item = allItems.get(i);
            String title = item.getText();
            selectedTitles.add(title);
            List<WebElement> allButtons = driver.findElements(SearchItemLocators.addItemToCartButton);
            WebElement addToCartButton = allButtons.get(i);
            wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
        }
        return selectedTitles;
    }

    public CartPage openCart (){
        wait.until(ExpectedConditions.elementToBeClickable
                (SearchItemLocators.cartIconButton));
        WebElement cartButton = wait.until(ExpectedConditions.visibilityOfElementLocated
                (SearchItemLocators.cartIconButton));
        cartButton.click();
        return new CartPage(driver);
    }

    public void selectItem() {
        smoothScrollPage();
        wait.until(ExpectedConditions
                .visibilityOfAllElementsLocatedBy(SearchItemLocators.addItemToCartButton));
        wait.until(ExpectedConditions
                .elementToBeClickable(SearchItemLocators.addItemToCartButton));

        List<WebElement> allItems = driver.findElements(SearchItemLocators.itemsName);
        if (!allItems.isEmpty()) {
            String title = allItems.getFirst().getText();
            System.out.println("Selected item: " + title);

            List<WebElement> allButtons = driver.findElements(SearchItemLocators.addItemToCartButton);
            if (!allButtons.isEmpty()) {
                WebElement addToCartButton = allButtons.getFirst();
                wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
            }
        }
    }



    public void smoothScrollPage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Long pageHeight = (Long) js.executeScript("return document.body.scrollHeight");
        int step = 50;
        final int[] prevCount = {driver.findElements(SearchItemLocators.productBox).size()};

        for (int i = 0; i <= pageHeight; i += step) {
            js.executeScript("window.scrollTo(0, arguments[0]);", i);

            wait.until(driver -> {
                int currentCount = driver.findElements(SearchItemLocators.productBox).size();
                boolean loaded = currentCount > prevCount[0];
                if (loaded) prevCount[0] = currentCount;
                return true;
            });
        }

        for (int i = pageHeight.intValue(); i >= 0; i -= step) {
            js.executeScript("window.scrollTo(0, arguments[0]);", i);

        }
    }

}

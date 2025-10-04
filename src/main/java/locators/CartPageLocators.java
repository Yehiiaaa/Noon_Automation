package locators;

import org.openqa.selenium.By;

public class CartPageLocators {
    public static final By itemsName =
            By.cssSelector("[data-qa='cart-item-name']");

    public static final By checkoutButton =
            By.xpath("//button[.//span[text()='Checkout']]");

    public static final By checkoutMessage =
            By.xpath("//div[text()=\"Hala! Let's get started\"]");

}

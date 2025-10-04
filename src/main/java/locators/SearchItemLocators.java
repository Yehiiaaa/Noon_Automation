package locators;

import org.openqa.selenium.By;

public class SearchItemLocators {
    public static final By filteredItems =
            By.className("PBoxLinkHandler_linkWrapper__8FlHB");
    public static final By filteredItemsPrice =
            By.className("Price_amount__2sXa7");
    public static final By itemsName =
            By.className("ProductDetailsSection_title__JorAV");
    public static final By addItemToCartButton =
            By.className("QuickAtc_atcCta__vfhXG");
    public static final By cartIconButton =
            By.cssSelector("a[data-qa='btn_cartLink-Header-Desktop']");
    public static final By productBox =
            By.cssSelector(".productBox");




}

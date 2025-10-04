package locators;

import org.openqa.selenium.By;

public class FilterPageLocators {
    public static final By priceFilterLocator =
            By.xpath("//h3[contains(normalize-space(text()), 'Price')]");
    public static final By minPriceInput =
            By.cssSelector("input[data-qa='filter-min-input']");
    public static final By maxPriceInput =
            By.cssSelector("input[data-qa='filter-max-input']");
    public static final By priceFilterApplyButton =
            By.className("PriceFilter_submit__NirLK");
    public static final By sortButton =
            By.className("DesktopSort_dropdown__nFV3b");
    public static final By sortTypeOptions =
            By.className("DesktopSortDropdown_link__IjuW9");






}

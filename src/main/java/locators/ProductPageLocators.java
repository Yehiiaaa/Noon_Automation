package locators;

import org.openqa.selenium.By;


public class ProductPageLocators {
    public static final By changeLanguageButton =
            By.cssSelector("button[data-qa='lnk_languageSelector_header-desktop']");

    public static By getCategoryLocator(String categoryName) {
        return By.xpath("//li[@data-qa='btn_main_menu_" + categoryName + "']");
    }

    public static By getBrandLocator(String brandName) {
        return By.xpath("//a[.//span[contains(normalize-space(text()), '" + brandName + "')]]");
    }

    public static final By searchBar =
            By.className("DesktopInput_searchInput__R44H1");

    public static final By productBox
            = By.cssSelector("div.productContainerSelector");

    public static final By noResultsMessage =
            By.xpath("//h3[contains(text(),\"We couldn't find\")]");

}


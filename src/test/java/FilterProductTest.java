import org.example.FilterPage;
import org.example.SearchItemPage;
import org.testng.annotations.Test;

public class FilterProductTest extends BaseTest{

    @Test (priority = 1,dataProvider = "productFilterData")
    public void browseFilterProducts (String category, String brand, String priceMin, String priceMax, String sortType)  {
        productPage.changeLanguage();
        productPage.navigateCategory(category);
        FilterPage filterPage = productPage.selectBrand(brand);
        filterPage.clickPriceFilter();
        filterPage.setMinPriceValue(priceMin);
        filterPage.setMaxPriceValue(priceMax);
        SearchItemPage searchItemPage =filterPage.itemPriceFilterApply();
        searchItemPage.sortDropMenu();
        searchItemPage.sortSelection(sortType);
        searchItemPage.assertOnFilterCondition(priceMin,priceMax);
    }
}

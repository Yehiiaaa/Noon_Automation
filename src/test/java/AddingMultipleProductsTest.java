import org.example.CartPage;
import org.example.FilterPage;
import org.example.SearchItemPage;
import org.testng.annotations.Test;

import java.util.List;

public class AddingMultipleProductsTest extends BaseTest{
    @Test(priority = 1, dataProvider = "searchItem")
    public void addMultipleProducts(String itemToSearch,String minPrice, String maxPrice){
        productPage.changeLanguage();
        FilterPage filterPage = productPage.searchItem(itemToSearch);
        filterPage.clickPriceFilter();
        filterPage.setMinPriceValue(minPrice);
        filterPage.setMaxPriceValue(maxPrice);
        SearchItemPage searchItemPage = filterPage.itemPriceFilterApply();
        List<String> itemsName = searchItemPage.selectThreeItems();
        CartPage cartPage = searchItemPage.openCart();
        cartPage.assertItemsName(itemsName);
    }
}

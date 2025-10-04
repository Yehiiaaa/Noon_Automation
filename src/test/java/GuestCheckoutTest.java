import org.example.CartPage;
import org.example.SearchItemPage;
import org.testng.annotations.Test;

public class GuestCheckoutTest extends BaseTest{
    @Test (dataProvider = "differentCategories")
    public void guestCheckout (String category1,String category2, String category3, String category4,
                               String brand1, String brand2, String brand3, String brand4){
        productPage.changeLanguage();
        productPage.navigateCategory(category1);
        productPage.selectBrand(brand1);
        SearchItemPage searchItemPage = new SearchItemPage(driver);
        searchItemPage.selectItem();

        productPage.navigateCategory(category2);
        productPage.selectBrand(brand2);
        searchItemPage.selectItem();

        productPage.navigateCategory(category3);
        productPage.selectBrand(brand3);
        searchItemPage.selectItem();

        productPage.navigateCategory(category4);
        productPage.selectBrand(brand4);
        searchItemPage.selectItem();
        CartPage cartPage = searchItemPage.openCart();
        cartPage.proceedToCheckout();
    }
}

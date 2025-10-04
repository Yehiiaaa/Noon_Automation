import org.example.FilterPage;
import org.testng.annotations.Test;

public class InvalidScenarioTest extends BaseTest{
    @Test (priority = 1, dataProvider = "invalidSearchData")
    public void invalidInputSearch (String invalidSearchData){
        productPage.changeLanguage();
        FilterPage filterPage = productPage.searchItem(invalidSearchData);
        filterPage.assertNoResultsMessage();
    }
}

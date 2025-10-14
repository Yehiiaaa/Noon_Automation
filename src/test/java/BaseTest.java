import org.example.ProductPage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.CSVFileManager;
import utils.ConfigLoader;
import utils.ExcelFileManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class BaseTest {
    WebDriver driver;
    int i =1;
    ExcelFileManager excelData;
    ConfigLoader configLoader;
    ProductPage productPage;

    @BeforeClass
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        productPage = new ProductPage(driver);
        configLoader = new ConfigLoader("src/main/resources/config.properties");
        excelData = new ExcelFileManager("src/main/resources/product_filter_data.xlsx","Sheet1");

    }

    @BeforeMethod
    public void openURL() {
        driver.get(configLoader.getValue("url"));
    }

    @AfterMethod
    public void takeScreenshotOnFailure(ITestResult result) {

        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                File directory = new File("./screenshots/");
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                String screenshotPath = "screenshots/" + result.getName() + i + ".png";
                FileHandler.copy(src, new File(screenshotPath));
                i++;
                System.out.println("Screenshot saved: " + screenshotPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @DataProvider(name = "productFilterData")
    public Object[][] getExcelData() {
        int rows = excelData.getRowCount();
        int cols = excelData.getColumnCount();

        Object[][] data = new Object[rows - 1][cols];

        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i - 1][j] = excelData.getSpecificCellValue(i, j);
            }
        }
        return data;
    }

    @DataProvider (name = "searchItem")
    public Object[][] getCSVSearchItem() {
        CSVFileManager csv = new CSVFileManager("src/main/resources/search_items.csv");
        List<String[]> rows = csv.getRows();

        Object[][] data = new Object[rows.size()][3];
        for (int i = 0; i < rows.size(); i++) {
            data[i][0] = rows.get(i)[0];
            data[i][1] = rows.get(i)[1];
            data[i][2] = rows.get(i)[2];
        }
        return data;
    }

    @DataProvider (name = "differentCategories")
    public Object[][] getCSVDifferentCategories() {
        CSVFileManager csv = new CSVFileManager("src/main/resources/different_categories.csv");
        List<String[]> rows = csv.getRows();

        Object[][] data = new Object[rows.size()][8];
        for (int i = 0; i < rows.size(); i++) {
            for (int j=0; j<8;j++) {
                data[i][j] = rows.get(i)[j];
            }
        }
        return data;
    }

    @DataProvider (name = "invalidSearchData")
    public Object[][] invalidSearch() {
        CSVFileManager csv = new CSVFileManager("src/main/resources/invalid_search_input.csv");
        List<String[]> rows = csv.getRows();

        Object[][] data = new Object[rows.size()][1];
        for (int i = 0; i < rows.size(); i++) {
                data[i][0] = rows.get(i)[0];
        }
        return data;
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}

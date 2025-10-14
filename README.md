# 🛒 Noon Automation Framework

This project is a **Selenium Test Automation Framework** built with **Java, TestNG, and Maven**, designed to automate test scenarios for [Noon.com](https://www.noon.com).  
It follows the **Page Object Model (POM)** design pattern for scalability and maintainability.

---

## 🚀 Tech Stack
- **Java 22+**
- **Selenium WebDriver**
- **TestNG**
- **Allure Reports**
- **Maven**

---
## 📂 Project Structure

```plaintext
Noon_Automation/
├── .git/
├── .gitignore
├── pom.xml
├── testng.xml
└── src/
    ├── main/
    │   └── java/
    │       ├── locators/
    │       │   ├── CartPageLocators.java
    │       │   ├── FilterPageLocators.java
    │       │   ├── ProductPageLocators.java
    │       │   └── SearchItemLocators.java
    │       │
    │       └── org/
    │           └── example/
    │               ├── CartPage.java
    │               ├── FilterPage.java
    │               ├── ProductPage.java
    │               └── SearchItemPage.java
    │
    └── test/
        └── java/
            ├── AddingMultipleProductsTest.java
            ├── BaseTest.java
            ├── FilterProductTest.java
            ├── GuestCheckoutTest.java
            ├── InvalidScenarioTest.java
            └── utils/
                ├── ConfigLoader.java
                ├── CSVFileManager.java
                ├── ExcelFileManager.java
                └── JSONFileManager.java

```
---
## 🔑 Key Features

- **Page Object Model (POM)** design for clean and maintainable test code
- **Reusable locators and page classes** for better abstraction and scalability
- **Data-driven testing** with support for:
    - **CSV** (via `CSVFileManager`)
    - **Excel** (via `ExcelFileManager`)
    - **Properties** (via `ConfigLoader`)
- **Centralized configuration management** using `ConfigLoader`
- **Base test setup** (`BaseTest.java`) handling WebDriver initialization and teardown
- **Cross-browser readiness** (easy to extend for multiple browsers)
- **Allure Reports** support for rich test reporting
- **Maven integration** for build automation and dependency management  


---
## ✅ Example Test Scenarios

- **FilterProductTest**
    - Browse and filter products by category, brand, price range, and sorting

- **AddingMultipleProductsTest**
    - Add multiple products from search results to the shopping cart
    - Verify items, prices, and quantities in the cart

- **GuestCheckoutTest**
    - Add items from different categories
    - Proceed to checkout as a guest user

- **InvalidScenarioTest**
    - Search with invalid input (special characters / random strings)
    - Verify “No results found” message is displayed

---
## How to Run Tests

### 1. Clone the repository
```
git clone https://github.com/Yehiiaaa/Noon_Automation
cd Noon_Automation
```

### 2. Run tests with Maven
```
mvn clean test
```

### 3. Generate Allure Report
```
allure generate --clean  
allure serve -h localhost
```
👉 This will automatically open the report in your browser.

---
## 📸 Screenshots
Failed tests will automatically capture screenshots and store them in:
```
/screenshots/
```

---
## 🚀 Future Enhancements
- Expand coverage with additional negative test cases
- Optimize test execution time and reduce scenario complexity
- Implement detailed logging for better debugging and traceability
- Enhance reporting by integrating screenshots into Allure reports  

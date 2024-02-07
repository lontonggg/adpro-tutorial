package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest {
    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest(){
        baseUrl = String.format("%s:%d/product/list", testBaseUrl, serverPort);
    }

    @Test
    void createProduct(ChromeDriver driver) throws Exception{
        driver.get(baseUrl);

        // Create a dummy product
        WebElement createProductPageButton = driver.findElement(By.id("createProductPageButton"));
        createProductPageButton.click();

        WebElement productNameInput = driver.findElement(By.id("nameInput"));
        WebElement productQuantityInput = driver.findElement(By.id("quantityInput"));

        productNameInput.clear();
        productQuantityInput.clear();

        String productName = "Dummy Product";
        String productQuantity = Integer.toString(1);
        productNameInput.sendKeys(productName);
        productQuantityInput.sendKeys(productQuantity);

        WebElement createProductButton = driver.findElement(By.id("createProductButton"));
        createProductButton.click();

        // Assert Test
        String pageSource = driver.getPageSource();
        assertTrue(pageSource.contains(productName));
        assertTrue( pageSource.contains(productQuantity));
    }
}

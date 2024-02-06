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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public class CreateProductFunctionalTest {
    /**
     * The port number assigned to the running application during test execution.
     * Set automatically during each test run by Spring Framework's test context.
     */
    @LocalServerPort
    private int serverPort;

    /**
     * The base URL for testing. Default value to {@code http://localhost}.
     */
    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setUp() {
        baseUrl = String.format("%s:%d/product/list", testBaseUrl, serverPort);
    }

    @Test
    void pageTitle_isCorrect(ChromeDriver driver) throws Exception {
        // Exercise
        driver.get(baseUrl);
        String pageTitle = driver.getTitle();

        // Verify
        assertEquals("Product List", pageTitle);
    }

    @Test
    void listMessage_listPage_isCorrect(ChromeDriver driver) throws Exception {
        // Exercise
        driver.get(baseUrl);
        String welcomeMessage = driver.findElement(By.tagName("h2")).getText();

        // Verify
        assertEquals("Product' List", welcomeMessage);
    }

    @Test
    void simulate_createProduct(ChromeDriver driver) throws Exception {
        driver.get(baseUrl);

        String testProductName = "Test Product";
        int testProductQuantity = 77;

        WebElement createProductButton = driver.findElement(By.linkText("Create Product"));
        createProductButton.click();

        WebElement nameInput = driver.findElement(By.name("productName"));
        nameInput.sendKeys(testProductName);

        WebElement quantityInput = driver.findElement(By.name("productQuantity"));
        quantityInput.clear();
        quantityInput.sendKeys(Integer.toString(testProductQuantity));

        WebElement submitButton = driver.findElement(By.tagName("button"));
        submitButton.click();

        String newUrl = driver.getCurrentUrl();
        assertEquals(baseUrl, newUrl);

        String page = driver.getPageSource();
        boolean containNewProductName = page.contains(testProductName);
        boolean containNewProductQuantity = page.contains(Integer.toString(testProductQuantity));
        assertTrue(containNewProductName);
        assertTrue(containNewProductQuantity);
    }
}
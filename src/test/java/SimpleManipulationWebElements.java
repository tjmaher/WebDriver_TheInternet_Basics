import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;

public class SimpleManipulationWebElements {

    // An Example of a quick-and-dirty Selenium test to manipulate a login page.

    private static WebDriver driver;

    private static String username = "tomsmith";
    private static String password = "SuperSecretPassword!";

    @Before
    public void setUp() {
        driver = new FirefoxDriver();
    }

    @Test
    public void test_Login(){

        System.out.println("Navigating to: http://the-internet.herokuapp.com/login");
        driver.get("http://the-internet.herokuapp.com/login");

        System.out.println("Waiting for page to load...");
        new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("The Internet"));

        System.out.println("The title of the page is: " + driver.getTitle() );
        assertTrue((driver.getTitle().equals("The Internet")));

        WebElement txtUsername = driver.findElement(By.id("username"));
        System.out.println("Entering username...");
        txtUsername.sendKeys(username);

        WebElement txtPassword = driver.findElement(By.id("password"));
        System.out.println("Entering password...");
        txtPassword.sendKeys(password);

        WebElement submitButton = driver.findElement(By.cssSelector("[type='submit'][class='radius']"));
        System.out.println("Selecting the submit button.");
        submitButton.click();

        System.out.println("Waiting for page to load...");
        new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("The Internet"));

        System.out.println("The title of the page is: " + driver.getTitle());
        assertTrue((driver.getTitle().equals("The Internet")));

        System.out.println("The current url is: " + driver.getCurrentUrl());

        WebElement logoutButton = driver.findElement(By.cssSelector("[class='button secondary radius']"));
        System.out.println("Waiting for logout button to load...");
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(logoutButton));
        System.out.println("Selecting the logout button.");
        logoutButton.click();
    }

    @After
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}
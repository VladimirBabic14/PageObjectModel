import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    LoginPage loginPage;

    @BeforeMethod
    public void setUp()
    {
       driver = browserOpen();
       loginPage = new LoginPage(driver);

    }
    @Test
    public void LoginOnPage()
    {
    loginPage.setUserName("standard_user");
    loginPage.setPassword("secret_sauce");
    loginPage.setLoginButton();

    Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");
    }

    @Test
    public void loginInvalidUserName()
    {
        loginPage.setUserName("stan");
        loginPage.setPassword("secret_sauce");
        loginPage.setLoginButton();
        Assert.assertEquals(loginPage.getError(),"Epic sadface: Username and password do not match any user in this service");
    }
    @Test
    public void loginWithOutPass()
    {
        loginPage.setUserName("standard_user");
        loginPage.setPassword("");
        loginPage.setLoginButton();
        Assert.assertEquals(loginPage.getError(),"Epic sadface: Password is required");
    }

    @AfterMethod
    public void after()
    {
    driver.quit();
    }
}

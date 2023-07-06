import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class InventoryTest extends BaseTest
{
    LoginPage loginPage;

    InventoryPage inventoryPage;

    CheckOutStepOnePage checkOutStepOnePage;

    CartPage cartPage;

    @BeforeMethod
    public void SetUp()
    {
        driver = browserOpen();
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        checkOutStepOnePage = new CheckOutStepOnePage(driver);
        loginPage.LoginOnPage();
    }
    @Test
    public void AddCartTwoProducts()
    {
   //Act
    /*loginPage.setUserName("standard_user");
    loginPage.setPassword("secret_sauce");

    //Arange
    loginPage.setLoginButton();*/
    inventoryPage.clickBackpack();
    inventoryPage.clickLight();

    //Assert
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");
        Assert.assertEquals(inventoryPage.getCartNumber(),"2");

    }
    @Test
    public void removeProduct()
    {
        inventoryPage.clickBackpack();
        inventoryPage.clickLight();
        inventoryPage.removeBeckpack();

        Assert.assertEquals(inventoryPage.getCartNumber(),"1");
    }
    @Test
    public void sortProduct()
    {
        inventoryPage.sortProduct("Price (high to low)");

        Assert.assertEquals(inventoryPage.getPrice(),"$49.99");
    }

    @Test
    public void sortLowProduct()
    {

        inventoryPage.sortProduct("Price (low to high)");

        Assert.assertEquals(inventoryPage.getPrice(),"$7.99");
    }

    @Test
    public void BuyProductToTheEnd()
    {
    inventoryPage.clickBackpack();
    inventoryPage.clickLight();
    inventoryPage.clickCart();
    cartPage.clickCheckout();
    checkOutStepOnePage.setForm("Vlada","Babic","11070");
    checkOutStepOnePage.clickFinish();

    Assert.assertEquals(checkOutStepOnePage.getMessage(),"Thank you for your order!");

    }

    @Test
    public void BuyProductWithOutData()
    {
        inventoryPage.clickBackpack();
        inventoryPage.clickLight();
        inventoryPage.clickCart();
        cartPage.clickCheckout();
        checkOutStepOnePage.setForm("","","");


        Assert.assertEquals(checkOutStepOnePage.getError(),"Error: First Name is required");

    }

    @AfterMethod
    public void after()
    {
        driver.quit();
    }
}

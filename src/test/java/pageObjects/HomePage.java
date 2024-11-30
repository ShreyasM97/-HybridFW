package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
	
	WebDriver driver;
	
	public HomePage(WebDriver driver)
	{
		super(driver);
	}
	
	
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement locMyAccount;
	
	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement locRegister;
	
	@FindBy(linkText="Login")
	WebElement linkLogin;
	
	
	public void clickMyAccount()
	{
		locMyAccount.click();
	}
	
	public void clickRegister()
	{
		locRegister.click();
	}
	
	public void clickLogin()
	{
		linkLogin.click();
	}

}

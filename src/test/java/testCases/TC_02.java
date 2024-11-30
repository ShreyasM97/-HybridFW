package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import pageObjects.HomePage;

public class TC_02 extends BaseClass {
	
	//Login Test case
	
	@Test(groups={"Sanity","Master"})
	public void verify_login() throws InterruptedException
	{
		logger.info("***** Starting TC_02 *****");
		
		//HomePage
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		Thread.sleep(3000);
		
		//LoginPage
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		Thread.sleep(3000);
		
		//MyAccountPage
		MyAccountPage map = new MyAccountPage(driver);
		boolean targetPage = map.isMyAccountPageExists();
		
		Assert.assertEquals(targetPage, true, "LoginFailed"); 			// "targetPage" will be compared to 'true'
																		// If "targetPage" is true, 'verify_login()' is passed
																		// If "targetPage" is false, then "LoginFailed" will be displayed and 'verify_login()' is failed.
		//or 
		
		//Assert.assertTrue(targetPage);
		
		logger.info("***** Finished TC_02 *****");
		
		
		
	}
	

}

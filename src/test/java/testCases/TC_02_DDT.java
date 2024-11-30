package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;

public class TC_02_DDT extends BaseClass {
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class,groups="Datadriven")			// "dataProviderClass=DataProviders.class" is used since 'DataProviders.java' is in another class
	public void verify_loginDDT(String email, String pwd, String exp) throws InterruptedException
	{
		logger.info("***** stating TC_003_LoginDDT ******");
		
		try
		{  
		//HomePage
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//Login
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
			
		//MyAccount
		MyAccountPage map=new MyAccountPage(driver);
		boolean targetPage=map.isMyAccountPageExists();
		
		
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(targetPage==true)
			{			
				map.clickLogout();
				Assert.assertTrue(true);
				
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(targetPage==true)
			{
				map.clickLogout();
				Assert.assertTrue(false);
				
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		
		}catch(Exception e)
		{
			Assert.fail();
		}
		Thread.sleep(3000);
		
		logger.info("***** Finished TC_003_LoginDDT ******");
		
		
		
		
		
		
		
	}

	
}
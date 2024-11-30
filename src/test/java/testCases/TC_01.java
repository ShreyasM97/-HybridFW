package testCases;
import org.apache.commons.lang3.RandomStringUtils;

import org.testng.Assert;

import org.testng.annotations.Test;

import baseClass.BaseClass;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;


public class TC_01 extends BaseClass {
	
	
	
	@Test(groups={"Regression","Master"})
	public void verify_account_registation()
	{
		
		try
		{
		logger.info("***** TC_01 Started *****");								// This comment will show in logs
		
		HomePage hp = new HomePage(driver);										// Home Page object created
		hp.clickMyAccount();
		hp.clickRegister();
		logger.info("***** Register opened *****");								// This comment will show in logs
		
		AccountRegistrationPage arp = new AccountRegistrationPage(driver);		// // AccountRegistrationPage object created
		arp.setFirstName(randomString().toUpperCase());
		arp.setLastName(randomString().toUpperCase());
		arp.setEmail(randomString()+"@gmail.com");
		arp.setTelephone(randomString());
		
		String password = randomAlphaNumeric();
		
		arp.setPassword(password);
		arp.setConfirmPassword(password);
		
		arp.setPrivacyPolicy();
		logger.info("***** Details Filled *****");								// This comment will show in logs
		
		arp.clickContinue();
		logger.info("***** Continue *****");									// This comment will show in logs
		
		String cnfrmMsg = arp.getConfirmationMsg();
		
		Assert.assertEquals(cnfrmMsg, "Your Account Has Been Created!");
		
		logger.info("***** Assertion Done *****");								// This comment will show in logs
		}
		catch(Exception e)
		{
			logger.error("Test Failed...");										// This comment will show in logs, logger.error will capture error logs
			logger.debug("Debug Logs....");										// This comment will show in logs, logger.debug will capture debug logs
			
			Assert.fail();
		}
		
		logger.info("***** TC_01 Finished....... *****");	
	}
	
	public String randomString()
	{
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	public String randomAlphaNumeric()
	{
		String generatedString = RandomStringUtils.randomAlphabetic(3);
		String generatedNumeric = RandomStringUtils.randomNumeric(3);
		return (generatedString+"@"+generatedNumeric);
	}
	
	
	
	
	
	
	
	
	
	

}

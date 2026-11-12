package swagtest_pkg;


import org.testng.Assert;
import org.testng.annotations.Test;

import swagbase_pkg.Swag_base;

public class Swag_logintest extends Swag_base
{
	
	@Test(priority=1)
	public void login()
	{
		test =extent.createTest("Loginverification");
		obj.swagloginmthd("standard_user", "secret_sauce");
	}
	
	
	
	@Test(priority=2)
	
	public void titleverification()
	{
		test = extent.createTest("Titleverification");
		String exp="Swag Labs";
		String actual=obj.swagtitlemthd();
		System.out.println(actual);
		Assert.assertEquals(exp, actual);
	}

}
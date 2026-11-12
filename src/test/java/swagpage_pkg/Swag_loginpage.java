package swagpage_pkg;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Swag_loginpage {

       WebDriver driver;
	
	@FindBy(xpath="//*[@id=\"user-name\"]")WebElement username;
	@FindBy(xpath="//*[@id=\"password\"]")WebElement password;
	@FindBy(xpath="//*[@id=\"login-button\"]")WebElement loginbtn;
	
	public Swag_loginpage() { //constructor -->classname is constructor name
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//methods
	public String swagtitlemthd()
	{
		String title=driver.getTitle();
		return title;
	}
	
	
	public void swagloginmthd(String un,String pw)
	{
		username.sendKeys(un);
		password.sendKeys(pw);
		loginbtn.click();
		
	}
	
	
}
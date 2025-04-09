package MohamedHafdi.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import MohamedHafdi.AbstractComponents.AbstractComponent;

public class Carts extends AbstractComponent {
	
	WebDriver driver;
	
	public Carts(WebDriver driver) {
		
		//Initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	
	@FindBy(css=".cartSection h3")
	List<WebElement> products;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutBtn;
	
	
	public List<WebElement> getProductAdded() {
		
		return products;
	}
	
	public Boolean cartProductCheck(String productName) {
		
		Boolean match = getProductAdded().stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public PaymentOrder checkOut() {
		
		checkoutBtn.click();
		PaymentOrder paymentOrder = new PaymentOrder(driver);
		return paymentOrder;
	}


}

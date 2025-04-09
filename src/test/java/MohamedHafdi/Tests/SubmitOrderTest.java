package MohamedHafdi.Tests;

import java.io.IOException;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import MohamedHafdi.TestComponents.BaseTest;
import MohamedHafdi.pageobjects.Carts;
import MohamedHafdi.pageobjects.ConfirmationPage;
import MohamedHafdi.pageobjects.LandingPage;
import MohamedHafdi.pageobjects.OrderPage;
import MohamedHafdi.pageobjects.PaymentOrder;
import MohamedHafdi.pageobjects.ProductCatalogue;

//@Listeners(MohamedHafdi.TestComponents.Listeners.class)
//Mohamedhafdi
public class SubmitOrderTest extends BaseTest {

		
		
		String country = "Mor";
		
		@Test(dataProvider= "getData", groups = {"purchaseOrder"})
		public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		ProductCatalogue productCatalogue = landingPage.LogingApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProducts();
	
		productCatalogue.addProductToCart(input.get("productName"));
		Carts cart = productCatalogue.goToCartPage();
		 
		List<WebElement> addedProduct = cart.getProductAdded();
		List<String> prod = addedProduct.stream().map(s -> s.getText()).toList();
		System.out.println(prod);
		Assert.assertTrue(cart.cartProductCheck(input.get("productName")));
		
		PaymentOrder paymentOrder = cart.checkOut();
		paymentOrder.chipping(country);
		ConfirmationPage confirmationPage = paymentOrder.submitOrder();
		String confirmMessage = confirmationPage.confirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));

		}
		
		@Test(dependsOnMethods= {"submitOrder"})
		public void orderHistoryTest() throws InterruptedException {
			
			String produtWanted = "adidas original";
			ProductCatalogue productCatalogue = landingPage.LogingApplication("miguel1234@gmail.com", "Miguel1234");
			OrderPage orderPage = productCatalogue.goToOrdersPage();
			Assert.assertTrue(orderPage.verifyOrderProduct(produtWanted));
			
		}
		
		@DataProvider
		public Object[][] getData() throws IOException {
			
//			HashMap<String, String> map = new HashMap<String, String>();
//			map.put("email", "miguel1234@gmail.com");
//			map.put("password", "Miguel1234");
//			map.put("productName", "adidas original");
//			HashMap<String, String> map1 = new HashMap<String, String>();
//			map1.put("email", "mohamed1234@gmail.com");
//			map1.put("password", "Mohamed1234");
//			map1.put("productName", "zara coat 3");
			
			List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//MohamedHafdi//data//PurchaseOrder.json");
			
			return new Object[][] {{data.get(0)},{data.get(1)}};
			
		}
		
}

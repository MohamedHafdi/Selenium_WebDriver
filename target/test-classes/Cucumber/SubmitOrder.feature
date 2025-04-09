
@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file
	
	Background:
	Given I landed on Ecommerce page
	
  @tag2
  Scenario Outline: Positive test of submiting the order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to cart
    And Checkout <productName> and submit the order
    Then "Thankyou for the order." message is displayed in the confirmation page

    Examples: 
      | name 								 | password	  | productName     |
      | miguel1234@gmail.com | Miguel1234 | ADIDAS ORIGINAL |
    

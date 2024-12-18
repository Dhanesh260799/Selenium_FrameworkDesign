@tag
Feature: Purchase the order from Ecommerce Website

Background: 
Given I landed on Ecommerce Page


  @tag2
  Scenario Outline: Positive Test of Submitting the order
    Given Loggged in with username <name> and password <password>
    When I add product <productName> to cart
    And Checkout <productName> and Submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmationPage

    Examples: 
      | name 					     |    password      |    productName  |
      |dhanesh12@gmail.com |    Selvi@1098    |    ZARA COAT 3  |
      
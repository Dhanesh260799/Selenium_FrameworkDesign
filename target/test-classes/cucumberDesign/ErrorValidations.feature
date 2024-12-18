
@tag
Feature: Error Validation
  I want to use this template for my feature file

 

  @tag2
  Scenario Outline: Negative Scenario for Login 
    Given I landed on Ecommerce Page
    When Loggged in with username <name> and password <password>
    Then "Incorrect email or password." Error message is displayed

    Examples: 
      | name 					     |    password     |   
      |dhanesh12@gmail.com |    Selvi@109    |   

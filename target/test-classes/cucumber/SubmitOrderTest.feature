#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

   Background: 
     Given I landed on Ecommerce Website
  
  @Regression
  Scenario Outline: Positive Test of submitting the order
    Given I want to login with username <name> and password <password>
    When  I want to add <productName> to cart
    And Checkout the <productName> and submit order
    Then "Thankyou for the order." message is displayed on ConfirmationPage

    Examples: 
      | name                           | password           | productName  |
      | saloniagrawalexam@gmail.com    | DurgaMaa12@        |     qwerty   |
      

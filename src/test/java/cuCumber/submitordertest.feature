
@tag
Feature: Purchase the order
  I want to use this template for my feature file

 Background:
 Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: positive test for submitting the order
    Given login in with username <name> and password <password>
    When I add the product <productname> to cart
    And checkout <productname> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confermation page

    Examples: 
      | name               | password  |  productname |
      | 12345678@gmail.com |Aa12345678 | ZARA COAT 3 |
     

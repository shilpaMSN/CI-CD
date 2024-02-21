
@tag
Feature: Error validation
  I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: Title of your scenario outline
   Given I landed on Ecommerce Page
    When login in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | name               | password   |   
      | 12345678@gmail.com |Aa123456789 | 

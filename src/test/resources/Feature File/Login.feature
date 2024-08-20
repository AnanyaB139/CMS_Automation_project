Feature: Login in to CMS

  Scenario: Successful login as an Admin user
    Given I navigate to the CMS login page
    When I enter the correct username and password
    Then I should be logged in successfully
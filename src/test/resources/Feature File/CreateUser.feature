Feature: creating a user

  Scenario: Admin creates a new user
    Given I am logged in as an admin
    When I navigate to the user creation page
    And I fill out the user form
    And I submit the form
    Then the new user should be successfully created

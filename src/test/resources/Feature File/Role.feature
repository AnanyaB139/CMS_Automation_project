Feature: Role module

 Scenario Outline: As a admin I have to create roles
    Given user login to the cms
    And navigate to roles and click on + button
    When user enter the "<RoleName>" and clicks on the create button
    Then role name should be created successfully.
   Examples:
     |RoleName  |
     | userManager |






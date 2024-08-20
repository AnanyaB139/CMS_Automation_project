Feature: Roles permission
  Scenario Outline: : Verify user can only access the User module in the side menu
    Given user login to the cms by giving "<UserName>" and "<password>"
    When I verify the "<UserName>" and  navigate to the side menu bar
    Then I should see the user module and able to read, write, modify, and delete in the user module
    And I should not see any other modules in the side menu
    Examples:
      |UserName| password |
      |Apoorva |Pixfix@12 |



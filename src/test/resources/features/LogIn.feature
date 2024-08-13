Feature: Log in to saucedemo;

  @Login
  Scenario: Verify if log in functionality working

    Given user is on log in page
    And the user writes username
    Then the use provides the password
    Then the user clicks the sign in button
    Then verify if the user in this page

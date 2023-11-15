@run
Feature: Register Flow Test Suite

  Background:
    Given "/index.php?route=account/register" end point is accessed

  Scenario: Register page can be accessed from the Home Page
    Given "/" end point is accessed
    When Register Link from Header Menu is clicked
    Then the current url contains "route=account/register" keyword

  Scenario: The Account Page Url is opened when the registration is successful
    When the following form from "RegisterPage" is populated as follows:
      | firstNameInput | RandomFirstname |
      | lastNameInput  | Random          |
      | emailInput     | RandomEmail     |
      | passwordInput  | RandomPassword  |
    And the "privacyToggle" from "RegisterPage" is clicked
    And the "continueBtn" from "RegisterPage" is clicked
    Then the current url contains "route=account/success" keyword

  Scenario: User remains on Register page when the continue button is not clicked
    When the following form from "RegisterPage" is populated as follows:
      | firstNameInput | RandomFirstname |
      | lastNameInput  | Random          |
      | emailInput     | RandomEmail     |
      | passwordInput  | RandomPassword  |
    Then the current url contains "route=account/register" keyword

  Scenario Outline: An error message is displayed when invalid <impacted attribute> is used for register flow
    When the following form from "RegisterPage" is populated as follows:
      | firstNameInput | <firstName>    |
      | lastNameInput  | <lastName>     |
      | emailInput     | RandomEmail    |
      | passwordInput  | RandomPassword |
#    When the Register Form is populated with the following data:
#      | firstName | <firstName>    |
#      | lastName  | <lastName>     |
#      | email     | <emailData>    |
#      | password  | <passwordData> |
    And the "privacyToggle" from "RegisterPage" is clicked
    And the "continueBtn" from "RegisterPage" is clicked
    Then the following error message is displayed:
      | "<impacted attribute> must be between 1 and 32 characters!" |
    Examples:
      | impacted attribute | firstName                           | lastName                                  | emailData   | passwordData |
      | First Name         | 555555555555555gg500009090900999g54 | Random                                    | RandomEmail | Random       |
      | Last Name          | RandomFirstname                     | 5555555555555555555005gg50009000909999g54 | RandomEmail | Random       |
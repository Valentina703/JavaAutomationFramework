@run
Feature: Login Related Test Cases
  Scenario Outline: An error message is displayed when using invalid <affectedAttribute> for login flow
    Given "/index.php?route=account/login" end point is accessed
    When the following form from "LoginPage" is populated as follows:
      | userNameInput | <email>    |
      | passwordInput | <password> |
#    When the Login Form is populated with following details:
#      | <email>    |
#      | <password> |
    And the "loginBtn" from "LoginPage" is clicked
    Then the following error message is displayed:
      | "Warning: No match for E-Mail Address and/or Password." |
    Examples:
      | email           | password     | affectedAttribute |
      | johns@yahoo.com | Password123! | password          |
@Regression
  Scenario Outline: Successful login is performed with valid credentials
    Given "/index.php?route=account/login" end point is accessed
    When the following form from "LoginPage" is populated as follows:
      | userNameInput | <username> |
      | passwordInput | <password> |
    And the "loginBtn" from "LoginPage" is clicked
    Then the current url contains "account" keyword
    Examples:
      | username                  | password        |
      | stephen.kuvalis@gmail.com | i17980hpxdzpnwd |
      | myong.turnery@ahoo.com    | et2r07t95c5plld |


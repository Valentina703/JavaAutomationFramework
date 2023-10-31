Feature: Register Flow Test Suite

  Scenario: Register page can be accessed from the Home Page
    Given "/" end point is accessed
    When Register Link from Header Menu is clicked
    Then the current url contains "route=account/register" keyword

    Scenario: The Account Page Url is opened when the registration is successful
      Given "/index.php?route=account/register&language=en-gb" end point is accessed
      And Register Link from Header Menu is clicked
      When the Register Form is populated with valid random  data
      And continue button is clicked
      Then the current url contains "route=account/success" keyword

  Scenario: User remains on Register page when the continue button is not clicked
    Given "/index.php?route=account/register&language=en-gb" end point is accessed
    When the Register Form is populated with valid random  data
    Then the current url contains "route=account/register" keyword


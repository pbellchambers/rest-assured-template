Feature: Test API Feature
  Demonstrate basic API test functionality with rest assured

  Scenario: Login Successful
    Given I have login details "eve.holt@reqres.in" "cityslicka"
    When I send a login request
    Then I should be logged in successfully

  Scenario: Login Unsuccessful
    Given I have login details "eve.holt@reqres.in" ""
    When I send a login request
    Then I should not be logged in
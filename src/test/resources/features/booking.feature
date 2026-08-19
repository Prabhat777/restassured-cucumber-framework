Feature: Restful Booker API

  Background:
    Given the Restful Booker API is available

  Scenario: Generate authentication token
    When I generate an authentication token
    Then the response status code should be 200
    And the authentication token should be generated
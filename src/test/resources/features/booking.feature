Feature: Restful Booker API

  Background:
    Given the Restful Booker API is available

  Scenario: Complete booking CRUD flow

  Given I generate an authentication token

  When I create a new booking
  Then the response status code should be 200
  And the booking should be created successfully

  When I get the newly created booking
  Then the response status code should be 200
  And the booking details should be displayed

  When I update the newly created booking
  Then the response status code should be 200
  And the booking should be updated successfully
  
  When I delete the newly created booking
  Then the response status code should be 201
  And the booking should be deleted successfully

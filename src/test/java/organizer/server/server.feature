Feature: Server status
  Background:
    Given url baseUrl
    Given path '/server'

  Scenario: Server status

    When method GET
    Then status 200
    And match $ == "OK"
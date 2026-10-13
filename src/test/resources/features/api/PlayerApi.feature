@api
Feature: Player API

  # Zadanie 3: odkomentuj kroki scenariusza i dopisz ich definicje w PlayerApiSteps.java.
  Scenario: Player details are returned
    When I request player "P-102"
#    Then the response status is 200
#    And the response data is a JSON object
#    And the player id is "P-102"
#    And the player email ends with "@example.test"

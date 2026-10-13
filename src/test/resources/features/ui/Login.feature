@ui
Feature: Player login

  # Zadanie 3: odkomentuj kroki scenariusza i dopisz ich definicje w LoginSteps.java.
  Scenario Outline: Player logs in with valid credentials
    Given I open the login page in "<layout>" layout
#    When I log in with valid player credentials
#    Then I see the player login confirmation

    Examples:
      | layout   |
      | normal   |
      | reversed |

Feature: Characters use their abilities

  Scenario: Eskimo use ability
    Given Create "IceTable" "stable" field with 2 capacity and "no" item
    And Create "Bob" "eskimo" type character on "IceTable" field
    When "Bob" use ability on "IceTable"
    Then There is an igloo on the "IceTable"
    And "Bob" has 3 work unit

  Scenario: Researcher use ability
    Given Create "IceTable" "stable" field with 2 capacity and "no" item
    And Create "Bob" "researcher" type character on "IceTable" field
    When "Bob" use ability on "IceTable"
    Then "IceTable" capacity equal the value from use ability
    And "Bob" has 3 work unit
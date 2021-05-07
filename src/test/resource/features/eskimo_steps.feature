Feature: Eskimo steps

  Scenario: Eskimo step on a Stable field
    Given Create "Start" "stable" field with 2 capacity and "no" item
    And Create "End" "stable" field with 2 capacity and "no" item
    And "Start" "right" neighbour "End"
    And Create "Bob" "eskimo" type character on "Start" field
    When "Bob" move to "right"
    Then "Bob" is on "End"
    And "Bob" has 3 work unit
    And "Bob" is not in water

  Scenario: Eskimo step on an Hole field
    Given Create "Start" "stable" field with 2 capacity and "no" item
    And Create "End" "hole" field with 0 capacity and "no" item
    And "Start" "right" neighbour "End"
    And Create "Bob" "eskimo" type character on "Start" field
    When "Bob" move to "right"
    Then "Bob" is on "End"
    And "Bob" has 3 work unit
    And "Bob" is in water

  Scenario: Eskimo step on an Unstable field which break
    Given Create "Start" "stable" field with 2 capacity and "no" item
    And Create "End" "unstable" field with 2 capacity and "no" item
    And "Start" "right" neighbour "End"
    And Create "Bob" "eskimo" type character on "Start" field
    And Create "Alice" "eskimo" type character on "End" field
    When "Bob" move to "right"
    Then "Bob" is on "End"
    And "Bob" has 3 work unit
    And "Bob" is in water
    And "Alice" is in water
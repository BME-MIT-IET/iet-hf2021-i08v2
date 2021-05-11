Feature: Pick up and use items

  Scenario: Eskimo pick up a diving suit
    Given Create "IceTable" "stable" field with 2 capacity and "diving suit" item
    And Create "Bob" "eskimo" type character on "IceTable" field
    When "Bob" dig on the ice field
    Then "Bob" has a diving suit
    And "Bob" has 3 work unit

  Scenario: Eskimo pick up a spade and use it
    Given Create "Start" "stable" field with 2 capacity and "spade" item
    And Create "End" "stable" field with 2 capacity and "no" item
    And Add 2 layer of snow to "End"
    And "Start" "right" neighbour "End"
    And Create "Bob" "eskimo" type character on "Start" field
    When "Bob" dig on the ice field
    And "Bob" move to "right"
    And "Bob" use the first item
    Then There is no snow on "End"
    And "Bob" has 1 work unit

  Scenario: Eskimo pick up a food
    Given Create "IceTable" "stable" field with 2 capacity and "food" item
    And Create "Bob" "eskimo" type character on "IceTable" field
    When "Bob" dig on the ice field
    Then "Bob" body temperature is 6
    And "Bob" has 3 work unit

  Scenario: Eskimo pick up a tent and use it
    Given Create "IceTable" "stable" field with 2 capacity and "tent" item
    And Create "Bob" "eskimo" type character on "IceTable" field
    When "Bob" dig on the ice field
    And "Bob" use the first item
    Then There is a tent on the "IceTable"
    And "Bob" has 2 work unit


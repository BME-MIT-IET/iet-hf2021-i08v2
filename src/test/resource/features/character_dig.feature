Feature: Character dig

  Scenario: Eskimo dig on an ice field
    Given Create "IceTable" "stable" field with 2 capacity and "no" item
    And Add 1 layer of snow to "IceTable"
    And Create "Bob" "eskimo" type character on "IceTable" field
    When "Bob" dig on the ice field
    Then There is no snow on "IceTable"
    And "Bob" has 3 work unit
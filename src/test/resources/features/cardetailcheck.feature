Feature: Car details Tests

  Scenario: Verification of car details for the vehicles mentioned in the files
    Given there are list of car registration numbers extracted from the input files
    And I am on car check home page
    When I do free car check for all the registration numbers extracted from the input files
    Then the expected car details should be displayed in the free-car-check page

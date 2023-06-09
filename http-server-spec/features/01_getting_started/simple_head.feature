@simple-head @01-getting-started
Feature: Simple HEAD

  @implemented
  Scenario: Executing a HEAD request to /simple_get
    Given I make a HEAD request to "/simple_get"
    Then my response should have status code 200
    And my response should have an empty body

  @implemented
  Scenario: HEAD does not include body
    Given I make a HEAD request to "/head_request"
    Then my response should have status code 200
    And my response should have an empty body

  @implemented
  Scenario: GET request does include the body
    Given I make a GET request to "/head_request"
    Then my response should have status code 200
    And my response should have a body with the text "This body does not show up in a HEAD request"

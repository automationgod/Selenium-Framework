Feature: this feature is about github application validation
  In addition, it will also validate the home page of github

  Scenario Outline: this is a scenario about github
    Given user open github application
    Then github home page loaded successfully
    When user click on "<Link1>" - link
    Then "<Link1>" page loaded successfully
    And user go back to github home
    When user click on "<Link2>" - link
    Then "<Link2>" page loaded successfully
    And user go back to github home
    And user close github application

    Examples: 
      | Link1 | Link2     |
      | About | Community |

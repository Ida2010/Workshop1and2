Feature: Add new address to user account

  Scenario Outline: Add a new address and verify it
    Given I am logged in as a registered user with email "<email>" and password "<password>"
    When I click on Address
    And I click on "+ Create new address"
    And I fill in the new assress form with alias "<alias>", address "<address>", city "<city>", zip "<zip>", country "<country>", phone "<phone>"
    Then I verify the new address is added correctly with alias "<alias>"

    Examples:
      | email                       | password            | alias | address       | city     | zip   | country | phone      |
      | trifunovicindira@gmail.com | DunjaTrifunovic1603 | Home  | Jevrejska 20  | Belgrade | 11000 | United Kingdom  | 0600161475 |




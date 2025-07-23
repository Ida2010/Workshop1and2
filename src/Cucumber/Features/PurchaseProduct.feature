Feature: Purchase product from mystore-testlab

  Scenario Outline: Buy Hummingbird Sweater with quantity and size
    Given User is on the login page
    When User logs in with email "trifunovicindira@gmail.com" and password "DunjaTrifunovic1603"
    And User selects "Hummingbird Printed Sweater"
    And User verifies the discount is "SAVE 20%"
    And User selects size "<size>"
    And User sets quantity to "<quantity>"
    And User adds the product to the cart
    And User proceeds to checkout
    And User confirms the address
    And User selects delivery method "Pick up in store"
    And User selects payment method "Pay by Check"
    And User confirms the order
    Then Order confirmation is displayed
    And Order appears in history with status "Awaiting check payment"

    Examples:
      | size | quantity |
      | M    | 5        |






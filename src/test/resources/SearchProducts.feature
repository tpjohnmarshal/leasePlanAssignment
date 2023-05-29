
  @REQ_LP_SEARCH
  Feature: Validate Product Search API

  @TEST_LP_1001 @automated
  Scenario Outline: Display the available options to the customer, when customer search using an available product id
    Given the customer search for "<PRODUCT_ID>"
    Then the search is successful
    And a list of available options are shown to the customer

    Examples:
      | PRODUCT_ID  |
      | apple       |
      | orange      |

  @TEST_LP_1002 @automated
  Scenario: Alert the customer when customer tries to search an unavailable product
    Given the customer search for "car"
    Then the search is unsuccessful
    And the customer alerted about the unavailability of the product

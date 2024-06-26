Feature: Validate the places API
@AddPlaceAPI @regression
  Scenario Outline: Validate if user can add place in the server using the add place API
    Given User should have a predefined payload "<name>" "<address>" "<phone number>" "<language>" "<website>" "<accuracy>"
    When User will send a POST HTTP request with the predefined payload to create a new place in the server using AddPlaceAPI
    Then validate the correct status code as 200 for adding place in the api call
    And Validate if "status" is "OK"
    And store the place_id

    Examples:
      | name    | address       | phone number | language   | website     | accuracy |
      | A-house | 1st main road | 91-1234567   | English-EN | www.1st.com | 23.567   |
      | B-house | 2nd main road | 91-23456     | Russian-RS | www.2nd.com | 25.678   |
      | C-house | 3rd main road | 91-98765     | Italian-IT | www.3rd.com | 12.678   |
      | D-house | 4th road      | 91-765433    | Candian-CA | www.4th.com | 456.744  |

  @GetPlaceAPI @regression
   Scenario: validate if user can fetch the place added using the get place API
     Given User should have the "place id" value to fetch the place details
     When User will send HTTP GET request along with the place id to fetch the place details
     Then Validate if the status code is 200

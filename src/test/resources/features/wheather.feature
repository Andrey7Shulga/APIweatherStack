Feature: End to End Test for WeatherStack API
  API documentation - https://weatherstack.com/documentation


    Scenario: Authorized user is capable of getting weather info of a given city
      Given "Paris" city weather info received
      When Status code is equal 200
      When Response of current weather has the expected Json schema from a file "currentWeatherJsonSchema.json"
      Then A value from jsonPath "location.name" is equal to "Paris"

    Scenario Outline: : Checking weather info data
      When A response by "<city>" matches current weather object
      Then Checking out the city "<city>" data

      Examples:
        | city       |
        | Sevastopol |
        | London     |
        | Paris      |
        | Tokyo      |


      Scenario Outline: Checking negative scenarios
        When A request to endpoint "<endpoint>" with key "<key>" for "<city>" city has sent
        Then Status code is equal 200
        Then A value from jsonPath "error.code" is equal to "<code>"
        Then A value from jsonPath "error.type" is equal to "<text>"

        Examples:
          | endpoint      | key                              | city                     | code | text                               |
          | current       |                                  | Tokyo                    | 101  | missing_access_key                 |
          | current       | bb98b761a6a29f43187db0c088800fc4 |                          | 601  | missing_query                      |
          | currentFalse  | bb98b761a6a29f43187db0c088800fc4 | Tokyo                    | 103  | invalid_api_function               |
          | current       | bb98b761a6a29f43187db0c088800fc4 | London;Singapur;Shanghai | 604  | bulk_queries_not_supported_on_plan |


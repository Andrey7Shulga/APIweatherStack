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


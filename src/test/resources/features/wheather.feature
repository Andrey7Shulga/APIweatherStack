Feature: End to End Test for WeatherStack API
  API documentation - https://weatherstack.com/documentation


    Scenario: Authorized user is capable of getting weather info of a given city to compare
      Given "London" city weather info received
      When Status code is equal 200
      When Response of current weather has the expected Json schema from a file "currentWeatherJsonSchema.json"
#      Then A value from jsonPath "location.name" is equal to "London"


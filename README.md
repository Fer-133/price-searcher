# Price Searcher

## Description


The application offers a REST endpoint that provides the final price and rate applied to a product in a given date range.

The endpoint /prices/ accepts as parameters: The application date, the product id and the brand id.

And return: The product id, the brand id, the rate, the start date, the end date and the final price.

The app is implemented following an hexagonal architecture with SpringBoot.

## Technologies

Java 17

Maven 4.0.0

Spring-Boot 2.7.4

H2 Database

## How to use it

### Build and run the application

To build the application use this command on the project directory:

mvn package

To run the application use the next command:

mvn spring-boot:run

### Launch a request

Launch a request to the /prices/ endpoint using the localhost address and 8080 port with the required parameters (applicationDate, productId and brandId) and you will receibe a JSON with the information.

And example of the request would be:

http://localhost:8080/prices/?applicationDate=2020-06-14-16.00.00&productId=35455&brandId=1

And the response:

{

  "productId" : "35455",

  "brandId" : "1",

  "priceList" : "2",

  "startDate" : "2020-06-14-15.00.00",

  "endDate" : "2020-06-14-18.30.00",

  "finalPrice" : "25.45 EUR"
  
}

And if the product is not found:

{
 
 "timestamp" : "2022-10-06T10:59:52.3815714",
 
 "status" : "404",
 
 "error" : "Not Found",
 
 "message" : "Precio no encontrado"
 
}

## How to run the tests

If you want to run all the test use the following command:

mvn test

If you only want to run the unit test use the following command:

mvn -Dtest=UnitTest test

To run only the integration test use the following command:

mvn -Dtest=IntegrationTest test

To run only the system test use the next command:

mvn -Dtest=SystemTest test

<H1>Accela Coding Exercise </H1>

<h4>This is a Web Application to manage person data which provide functionality to  insert, edit, delete and counting of persons and their addresses.
</h4>

##  Technologies Used
Java 8, SpringBoot, Rest web service, lombok, Maven, Junit, In memory H2 Database

## Getting Started

Below instructions will help you to run this project on your local machine.

## Prerequisites

- JDK 8 or above
- Postman (to check Rest APIs)


## Install & Run

    Run the below command in terminal 
    
    $ git clone https://github.com/poojakh1/PersonDetails.git
    $ cd PersonDetails
    $ mvn package
    $ mvn spring-boot:run

## Test

    $ mvn clean test

## Rest API's
Run below api's in Postman to check the functionality
- Make sure  to set the content type in Headers section   
Content-Type: application/json

Postman / Internet browser (GET, POST, PUT, DELETE)

- Get all persons  
    GET: http://localhost:8080/persons
      
    
- Add a person   
    POST:  http://localhost:8080/persons/add  
    Sample JSON on Postman
    {
    "firstName": "Test",
    "lastName": "Java"
    }
    
    
- Delete person by their id    
    DELETE : http://localhost:8080/persons/delete/{personID}
      
    
- Edit person details   
    PUT : http://localhost:8080/persons/update/{person_id}
      
    
- Count the no of persons in DB  
    GET: http://localhost:8080/persons/count
      
      
- Get all the addresses by person ID  
    GET: http://localhost:8080/persons/{personId}/address
    
    
- Add addresses to given person ID  
      POST: http://localhost:8080/persons/{personId}/address/add
      
    Sample JSON on Postman  
        {
        "street": "Street",
        "city": "Dublin",
        "state": "Ireland",
        "postalCode": "D16 YF58"
        }
    
    
- Delete address by addressId  
    DELETE: http://localhost:8080/persons/address/delete/{addressId}
    
    
- Edit address details   
      PUT : http://localhost:8080/persons/address/update/{addressId}

## Example 
- POST http://localhost:8080/persons/add
    
    Body - {"firstName":"John","lastName":"Snow"}
    
        Response - 200 OK - {
            "id": 1,
            "firstName": "John",
            "lastName": "Snow"
            }

    
- GET http://localhost:8080/persons
  
        Response - 200 OK - [
            {
                "id": 1,
                "firstName": "John",
                "lastName": "Snow"
            }
        ]

    
- POST http://localhost:8080/persons/1/address/add  
    Body - {"street": "Street","city": "Dublin","state": "Ireland","postalCode": "D16 YF58"}
        
        Response - 200 OK- {
                "id": 2,
                "street": "Street",
                "city": "Dublin",
                "state": "Ireland",
                "postalCode": "D16 YF58",
                "person": {
                    "id": 1,
                    "firstName": "John",
                    "lastName": "Snow"
                }
                }
  
- GET http://localhost:8080/persons/1/address
  
        Response - 200 OK - [
        {
            "id": 2,
            "street": "Street",
            "city": "Dublin",
            "state": "Ireland",
            "postalCode": "D16 YF58",
            "person": {
                "id": 1,
                "firstName": "John",
                "lastName": "Snow"
            }
        }
        ]
# Welcome to Belong Code Challenge By Veeneta Ray


# Requirement / Problem Statement
We are a Telecom operator. In our database, we are starting to store phone numbers associated to customers (1 customer: N phone numbers) and we will provide interfaces to manage them.
We need to provide the below capabilities:

1. Get all phone numbers
2. Get all phone numbers of a single customer
3. Activate a phone number

# Acceptance Criteria

Provide interface specifications for the above functions/capabilities.
Provide an implementation of the formulated specifications.
You can assume the phone numbers as a static data structure that is initialised when your program runs.

 
## Gradle commands

The project makes use of Gradle and uses the Gradle wrapper to help you out carrying some common tasks such as building the project or running it.

### List all Gradle tasks

List all the tasks that Gradle can do, such as `build` and `test`.

```console
$ ./gradlew tasks
```

### Build the project

Compiles the project, runs the test and then creates an executable JAR file

```console
$ ./gradlew build
```

Run the application using Java and the executable JAR file produced by the Gradle `build` task. The application will be
listening to port `9000`.

```console
$ java -jar build/libs/codeChallenge-0.0.1-SNAPSHOT.jar
```

### Run the tests

There are two types of tests, the unit tests and the functional tests. These can be executed as follows.

- Run unit tests only

```console
  $ ./gradlew test
  ```

### Run the application

Run the application which will be listening on port `9000`.

```console
$ ./gradlew bootRun
```

## API

Below is a list of API endpoints with their respective input and output. Please note that the application needs to be
running for the following endpoints to work. For more information about how to run the application, please refer
to [run the application](#run-the-application) section above.

### This endpoint is used to get phone numbers of all customers

GET /phones

* Invoke via Swagger URL

```console
http://localhost:9000/documentation/swagger-ui/index.html#/CustomerPhone/getAllCustomerPhoneUsingGET
```


* The following GET request, is an example request using CURL:

```console
curl -X GET "http://localhost:9000/phones" -H "accept: */*"
```

* The following GET request can be used to invoke via Postman

http://localhost:9000/phones


Example of response

```json
{
  "payload": [
    {
      "customerId": 1,
      "customerName": "FGMGOAQ",
      "phoneDetails": [
        {
          "phoneId": 21,
          "phoneNumber": "06597452896",
          "activated": false
        },
        {
          "phoneId": 22,
          "phoneNumber": "6597452896",
          "activated": false
        },
        {
          "phoneId": 23,
          "phoneNumber": "6597452897",
          "activated": false
        }
      ]
    }
  ]
}
```

## This endpoint is used to get a specific customer's phone number(s).

Endpoint

```text
GET /phones/{customerId} 
```

* Invoke via Swagger URL

```text
http://localhost:9000/documentation/swagger-ui/index.html#/CustomerPhone/getCustomerPhoneUsingGET
```

* The following sample GET request, is an example request using CURL:

```console
$ curl -X GET "http://localhost:9000/phones/3" -H "accept: */*"
```

* The following sample GET request can be used to invoke via Postman

```console
http://localhost:9000/phones/3
```

Sample Response

```json
{
  "payload": {
    "customerId": 3,
    "customerName": "BCNCPZC",
    "phoneDetails": [
      {
        "phoneId": 25,
        "phoneNumber": "6597452899",
        "activated": false
      },
      {
        "phoneId": 26,
        "phoneNumber": "6597452900",
        "activated": false
      },
      {
        "phoneId": 27,
        "phoneNumber": "6597452901",
        "activated": false
      },
      {
        "phoneId": 28,
        "phoneNumber": "6597452902",
        "activated": false
      }
    ]
  }
}
```

`Parameter(s) *`

`customerId` - customerId is mandatory field to identify customer



## This endpoint is used to activate specific customer's phone number.

Endpoint

```text
POST /activate
```

Request Body

```json

{
  "customerId": 0,
  "phoneNumber": "string"
}
```

`Parameter(s) *`

`customerId` - CustomerId is mandatory field to identify customer

`phoneNumber` - PhoneNumber is mandatory field to identify the phone number to be activated

* Invoke via Swagger URL

```console
http://localhost:9000/documentation/swagger-ui/index.html#/CustomerPhone/activateCustomerPhoneUsingPOST
```

* The following sample POST request, is an example request using CURL:

```console
$ curl -X POST "http://localhost:9000/activate" -H "accept: */*" -H "Content-Type: application/json" -d "{\"customerId\":3,\"phoneNumber\":\"6597452899\"}"
```

* The following sample POST request can be used to invoke via Postman

```console
http://localhost:9000/activate
```

Sample Output

```json
{
  "payload": [
    "Phone Number 6597452899 for CustomerId 3 is activated."
  ]
}
```

```json
{
  "payload": [
    "Given Phone number 6597452899 is already activated."
  ]
}
```

 

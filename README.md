# Smart Home Plus Gateway

This project is a smart home gateway system developed using Java, Maven, and Python.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- Java 17
- Maven

### Installing

A step by step series of examples that tell you how to get a development environment running.

# Clone the repository
git clone https://github.com/Gituser010/smarthomeplusgateway.git

# Navigate into the directory

# Install dependencies
mvn install

# Run the application
mvn spring-boot:run

# Create and Run the Docker Container
To create and run the Docker container, follow these steps:
1.mvn clean install
2.docker build -t smart-home-plus-gateway .
3.docker run -p local_port:docker_port smart-home-plus-gateway

# Api Endpoints
The application provides the following RESTful endpoints:

## House Endpoints
1. **GET /api/{apiVersion}/gateway/house**: Returns all the houses in the system.
2. **POST /api/{apiVersion}/gateway/house**: Creates a new house in the system.
3. **GET /api/{apiVersion}/gateway/{id}**: Returns the details of the house with the specified ID.
4. **PUT /api/{apiVersion}/gateway/{id}**: Updates the details of the house with the specified ID.
5. **DELETE /api/{apiVersion}/gateway/{id}**: Deletes the house with the specified ID.

## Device Endpoints

1. **GET /api/{apiVersion}/gateway/{house}/device**: Returns all the devices in the system.
2. **GET /api/{apiVersion}/gateway/{house}/device/{id}**: Returns the details of the device with the specified ID.
3. **GET /api/{apiVersion}/gateway/{house}/device/{deviceType}/{id}**: Returns the details of the device with the specified ID and type.
4. **PUT /api/{apiVersion}/gateway/{house}/device/{deviceType}/{id}**: Updates the details of the device with the specified ID.

### Device Types
- **fireplace**
- **door**
- **thermometer**
- **RGBLight**

# Testing
To run the tests, execute the following command:

1. git clone virtual-smart-home-plus-tests
2. follow the instructions in the README.md file


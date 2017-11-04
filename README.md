# Restful Service Sample

Simple code skeleton of how one could go about designing a restful service.
The sample uses the following frameworks/libraries/apis:

* Maven (for dependency management and creation of spring boot jar)
* Spring Boot / Spring Framework (for DI/IOC and conventions over configuration)
* Spring MVC (for restful endpoint)
* MapStruct (for object to object mapping between DTO and Entity)
* Spring JPA (for ORM that easily allows for database interaction)
* Log4J (for logging)
* Use of Spring's ResponseEntityExceptionHandler in order to provide proper exception handling and JSON responses when exceptions occur.
* Use of spring Validator in order to intercept all incoming json request and validate them
* Proper decoulping of endpoint layer, service layer, dao layer

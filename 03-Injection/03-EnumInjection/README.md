# Sample - CDI - Injection with Qualifiers with Enumerations

## Purpose of this sample

The purpose of this sample is to show you how to inject a bean and an EJB into a Servlet and REST web service using CDI injection with Qualifiers with enumerations. The `NumberGenerator` interface is implemented by `IsbnGenerator` and `IssnGenerator`. To choose between both implementations we use the `@NumberOfDigits` qualifier which has a `Digits` enumeration.

[Read more on my blog](http://agoncal.wordpress.com/2012/01/16/wytiwyr-what-you-test-is-what-you-run/)

## Class diagram

![image](https://github.com/agoncal/agoncal-sample-arquilian/raw/master/01-wytiwyr/src/main/webapp/classdiag.png)

## Compile and package

Being Maven centric, you can compile and package this sample with `mvn clean compile`, `mvn clean package` or `mvn clean install`. You will then get a `sampleCdiInjectQualifierEnum.war` file that you can deploy.

## Test the sample

The integration test class is launched using `mvn test`. This test uses the embedded `EJBContainer` API with an in memory Derby database.

## Deploy the sample

This sample has been deployed with GlassFish 3.1.2 in several modes :

* GlassFish runtime : [download GlassFish](http://glassfish.java.net/public/downloadsindex.html), install it, start GlassFish (typing `asadmin start-`domain) and once the application is packaged deploy it (using the admin console or the command line `asadmin deploy target/sampleCdiInjectQualifierEnum.war`)
* GlassFish embedded : use the [GlassFish Maven Plugin](http://maven-glassfish-plugin.java.net/) by running `mvn clean package embedded-glassfish:run`

## Execute the sample

Once deployed go to the home page at [http://localhost:8080/sampleCdiInjectQualifierEnum](). Invoke the servlet at [http://localhost:8080/sampleCdiInjectQualifierEnum/itemServlet]() and the REST service at [http://localhost:8080/sampleCdiInjectQualifierEnum/rs/items]() and see all the books in the database. You can also run some [curl](http://curl.haxx.se/) commands :

	// Get an ISBN number
	curl -X GET http://localhost:8080/sampleCdiInjectQualifierEnum/rs/items/isbn

	// Get the XML representation of a Book
	curl -X GET http://localhost:8080/sampleCdiInjectQualifierEnum/rs/items/405

	// Get the JSON representation of a Book
	curl -X GET -H "Accept: application/json" http://localhost:8080/sampleCdiInjectQualifierEnum/rs/items/405

	// Get the XML representation of all Books
	curl -X GET http://localhost:8080/sampleCdiInjectQualifierEnum/rs/items

	// Get the JSON representation of all Books
	curl -X GET -H "Accept: application/json" http://localhost:8080/sampleCdiInjectQualifierEnum/rs/items

	// Creates a new Book
	curl -X POST --data-binary "{ \"title\":\"Abbey Road\", \"description\":\"Beatles album\", \"musicCompany\":\"Apple\",\"numberOfCDs\":\"1\",\"totalDuration\":\"241\", \"price\":\"24.0\" }" -H "Content-Type: application/json" http://localhost:8080/sampleCdiInjectQualifierEnum/rs/items

<div class="footer">
    <span class="footerTitle"><span class="uc">a</span>ntonio <span class="uc">g</span>oncalves</span>
</div>
# Welcome...
### To the best project in distributed-mald!

But not just because it has a Dilbert comic...

![dilbert](https://qph.ec.quoracdn.net/main-qimg-c2780eede155f6e214f1ffea2e8d62da)

# Employee Management System Website
The Employee Managment System (EMS) is a system for tracking which employees have worked against which clients for a business. The website is a GUI user friendly front end for this.

This project consistutes the web front end for the EMS, allowing users to.

   - Create business and retrieve businesses.
   - Create and retrieve user accounts in the form of employees.
   - Login to user accounts, generating a session key.
   - Create and retrieve clients with location boundaries.
   - Create and retrieve worklogs with location and time data.

### Spring Boot
The website uses spring boot technology for a no fuss start up. Just run the SpringBootWebApplication.java file to start up an instance of the website with an embedded tomcat server.

In deployment mode just run the compiled jar file on the target machine to start up the server.

### Spring MVC 
Web processing is all done through the enterprise ready [Spring MVC](https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html) framework. It makes use of offical spring features where ever possible. Allowing use seemless beaning, authentication, and communication with the API backend.

URLs are built like follows:
   - /
   - /<business id>
   - /<business id>/login
   - /<business id>/dashboard

[RestTemplate](https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/client/RestTemplate.html) provides us with seemless easy integration with the API. Simply instaniate it like so with a root URL for the API.
```Java
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder)
    {
        return builder
                .rootUri(apiRootUri)
                .errorHandler(new EMSResponseErrorHandler())
                .additionalInterceptors(new AuthenticationInterceptor())
                .build();
    }
```
Then it ready to go:
```Java
    public Collection<Employee> getEmployees()
    {
    	Employee[] response = restTemplate
                .getForObject("/business/{businessTag}/employee",
                		Employee[].class
                );

        return Arrays.asList(response);
    }
```
### WebJars
the project makes us of webjars, a system that allows us to deploy local web jars containing libraries with our website by simply including them in the maven pom file. Combinied this with webjars locator service this makes a powerful tool where we can include locally hosted libraries with auto resolving versions on our web content.
```Pom
    <dependency>
      <groupId>org.webjars</groupId>
      <artifactId>jquery</artifactId>
      <version>2.1.4</version>
    </dependency>
```
Then the jsp simply has:
```JSP
	<script src='webjars/jquery/jquery.min.js'></script>
```

# Tech
The EMS Website uses a number of third party dependencies to work properly:

   - [Spring Boot Framework](https://projects.spring.io/spring-boot/) - an level enterprise web development framework.
   - [Apache Tomcat Embedded](https://mvnrepository.com/artifact/org.apache.tomcat.embed/tomcat-embed-core) - embedded apache tomcat a java web server.
   - [Webjars](https://www.webjars.org/) - deployment of web libraries as jar files.
   - [Webjar locator](https://github.com/webjars/webjars-locator) - easy auto version resolution.
   - [JQuery (webjar)](https://github.com/webjars/jquery) - fast, small, and feature-rich JavaScript library.
   - [Bootstrap (webjar)](https://github.com/webjars/bootstrap) - an open source front-end component library.
   - [MomentJS (webjar)](https://github.com/webjars/momentjs) - a tool for handling dates and times in JavaScript.
   - [Apache Maven](https://maven.apache.org/) - a software project management and comprehension tool.
   - [Docker](https://www.docker.com/) - a container management system.
   - [Yaml](http://yaml.org/) - human-readable data serialization language.
   - [JUnit4](http://junit.org/junit4/) - a simple framework for unit testing frameworks.






# hybrid-cloud

* 12 Factor App Style
https://spring.io/blog/2015/01/13/configuring-it-all-out-or-12-factor-app-style-configuration-with-spring
One highlight: 
    Enable system-wide refresh:
    The Spring Cloud Bus links all services through a RabbitMQ powered-bus. This is particularly powerful. You can tell one (or thousands!) of microservices to refresh themselves by sending a single message to a message bus. This prevents downtime and is much more friendly than having to systematically restart individual services or nodes.

* Spring Cloud Netflix: 
https://github.com/spring-cloud/spring-cloud-netflix/blob/master/docs/src/main/asciidoc/spring-cloud-netflix.adoc
http://cloud.spring.io/spring-cloud-netflix/spring-cloud-netflix.html

* Amazon made the Amazon Linux available as a docker image. 
$ docker run -dit amazonlinux
$ docker attach <container-id>

TODO:

    * Server registration
    Look into requiring service registration - in order to use service must first register and use key for access

    * OpenAPI Code Generation
    SDK/Model code generation using swagger?   
    
        Use Spring Fox for swagger code gen - opposite approach. Analyses existing code to generate swagger?
    
        See https://github.com/swagger-api/swagger-codegen
        "This is the swagger codegen project, which allows generation of API client libraries (SDK generation), server stubs and documentation automatically given an OpenAPI Spec. 
        Currently, the following languages/frameworks are supported"
        https://github.com/OAI/OpenAPI-Specification/blob/master/versions/2.0.md


    Docker Maven plugin - generates a docker image as part of build
    https://github.com/spotify/docker-maven-plugin
    Discovered reference to it here: http://tech.asimio.net/2016/04/05/Microservices-using-Spring-Boot-Jersey-Swagger-and-Docker.html#create_project
    
    
    Possible solution to sdk config issue (different package not picked up in autoscan)
    @SpringBootApplication(scanBasePackages = {"com.j2clark", "com.xyz"})
    
    
    OpenAPI Documentation
    https://apihandyman.io/writing-openapi-swagger-specification-tutorial-part-1-introduction/
    
    
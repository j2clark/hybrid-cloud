# hybrid-cloud

TODO:
Look into this: https://spring.io/blog/2015/01/13/configuring-it-all-out-or-12-factor-app-style-configuration-with-spring
One highlight: 
    Enable system-wide refresh:
    The Spring Cloud Bus links all services through a RabbitMQ powered-bus. This is particularly powerful. You can tell one (or thousands!) of microservices to refresh themselves by sending a single message to a message bus. This prevents downtime and is much more friendly than having to systematically restart individual services or nodes.

How to trigger a refresh: curl -d{} http://127.0.0.1:8080/refresh

Spring Cloud Netflix: 
https://github.com/spring-cloud/spring-cloud-netflix/blob/master/docs/src/main/asciidoc/spring-cloud-netflix.adoc
http://cloud.spring.io/spring-cloud-netflix/spring-cloud-netflix.html

Set up config server
https://cloud.spring.io/spring-cloud-config/spring-cloud-config.html
https://spring.io/guides/gs/centralized-configuration/

Look into requiring service registration - in order to use service must first register and use key for access
 
Use Spring Fox for swagger code gen
    - auto generate model in service and sdk using swagger definition

 
http://blog.abhijitsarkar.org/technical/netflix-eureka/
 
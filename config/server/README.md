# Config Server

The AWS deployment of this project is based on knowledge gained while getting Eureka deployed
Best to take a look at that project first (AWS Steps, bash.sh and ami_bash.sh) 

Set up config server
https://cloud.spring.io/spring-cloud-config/spring-cloud-config.html
https://spring.io/guides/gs/centralized-configuration/


I am having an issue getting config server to talk to Eureka.
Look into this: https://github.com/Netflix/eureka/wiki/Understanding-eureka-client-server-communication

Eureka Client Configuration:
https://github.com/Netflix/eureka/wiki/Configuring-Eureka#configuration

Sample eureka-client.properties: https://github.com/Netflix/eureka/blob/master/eureka-examples/conf/sample-eureka-client.properties

This looks interesting...
http://tech.asimio.net/2016/11/14/Microservices-Registration-and-Discovery-using-Spring-Cloud-Eureka-Ribbon-and-Feign.html
http://tech.asimio.net/2016/12/09/Centralized-and-Versioned-Configuration-using-Spring-Cloud-Config-Server-and-Git.html

http://docs.aws.amazon.com/IAM/latest/UserGuide/id_roles_use_switch-role-ec2.html



So many red-herrings trying to get this launched. Embarrassing actually.
Still not sure if IAM Role BasicMicroserviceRole is required, need to validate
Was able to run using:
java -jar -Dspring.application.json='{"eureka.client.serviceUrl.defaultZone":"http://eureka.j2clark.pics:8761/eureka/"}' configserver.jar
which overrides default eureka url
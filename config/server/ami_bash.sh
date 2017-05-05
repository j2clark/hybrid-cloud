#!/bin/bash

# bash script assumes AMI has java 1.8 installed
# and /app directory has been created
# For initialization from scratch, use bash.sh

export s3_base=j2clark/repo
export application=configserver
export bucket=${s3_base}/${application}
export eureka_host=
export eureka_port=8761

# install updates
yum update -y

# create the working directory
mkdir /opt/${application}

# download the maven artifact from S3
aws s3 cp s3://${bucket}/jar/${application}-latest.jar /opt/${application}/${application}.jar
echo "java -jar -Dspring.application.json='{\"eureka.client.serviceUrl.defaultZone\":\"${eureka_host}:$eureka_port/eureka/\"}' /opt/${application}/${application}.jar" > /opt/${application}/${application}.sh
chmod +x /opt/${application}/${application}.sh

# install as a service
# see http://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/html/deployment-install.html#deployment-service

# create a springboot user to run the app as a service
useradd springboot
# disable shell login for user springboot
chsh -s /sbin/nologin springboot

chown springboot:springboot /opt/${application}
chown springboot:springboot /opt/${application}/${application}.jar
chmod 500 /opt/${application}/${application}.jar
# lock down any changes to jar
chattr +i /opt/${application}/${application}.jar

# create a symbolic link in /etc/init.d
ln -s /opt/${application}/${application}.sh /etc/init.d/${application}


# start services
service ${application} start

# automatically start service if ec2 instance reboots
chkconfig ${application} on
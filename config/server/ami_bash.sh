#!/bin/bash

# bash script assumes AMI has java 1.8 installed
# and /app directory has been created
# For initialization from scratch, use bash.sh

export s3_base=j2clark/repo
export application=configserver
export bucket=${s3_base}/${application}

# install updates
yum update -y

# install apache httpd - only for web apps
#yum install httpd -y

#install awslogs [publish application logs to cloudwatch]
#yum install awslogs -y

# create the working directory
mkdir /opt/${application}

# create configuration specifying the used profile
#echo "RUN_ARGS=--spring.profiles.active=ec2" > /opt/${application}/${application}.conf

# download the maven artifact from S3
aws s3 cp s3://${bucket}/jar/${application}-latest.jar /opt/${application}/${application}.jar
aws s3 cp s3://${bucket}/conf/${application}.sh /opt/${application}/${application}.sh

# copy appropriate awslogs configs from s3
# set region to us-west-1
#aws s3 cp s3://${bucket}/conf/awscli.conf /etc/awslogs/awscli.conf
# set log group configuration [log groups auto-created, be careful]
#aws s3 cp s3://${bucket}/conf/awslogs.conf /etc/awslogs/awslogs.conf


# install as a service
# see http://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/html/deployment-install.html#deployment-service

# create a springboot user to run the app as a service
useradd springboot
# disable shell login for user springboot
chsh -s /sbin/nologin springboot

chown springboot:springboot /opt/${application}
chown springboot:springboot /opt/${application}/${application}.jar
chmod 500 /opt/${application}/${application}.jar
# lock down changes to jar
chattr +i /opt/${application}/${application}.jar

# tighten security on conf file
chmod 400 /opt/${application}/${application}.conf
chwon root:root /opt/${application}/${application}.conf

#echo "java -jar /opt/${application}/${application}.jar -Deureka.client.serviceUrl.defaultZone=http://${eureka.instance.hostname}:${server.port}/eureka/" >> /opt/${application}/${application}.sh
#chmod 500 /opt/${application}/${application}.sh

# create a symbolic link in /etc/init.d
#ln -s /opt/${application}/${application}.jar /etc/init.d/${application}
ln -s /opt/${application}/${application}.sh /etc/init.d/${application}



# forward port 80 to 8080 - web apps only
#echo "<VirtualHost *:80>
#  ProxyRequests Off
#  ProxyPass / http://localhost:8080/
#  ProxyPassReverse / http://localhost:8080/
#</VirtualHost>" >> /etc/httpd/conf/httpd.conf

# start services
#service httpd start
#service awslogs start
service ${application} start

# automatically start service if ec2 instance reboots
#chkconfig httpd on
#chkconfig awslogs on
chkconfig ${application} onusr/bin/env bash
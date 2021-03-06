#!/bin/bash

# bash script assumes AMI has java 1.8 installed
# and /app directory has been created
# For initialization from scratch, use bash.sh

# install updates
yum update -y

# install apache httpd - only for web apps
#yum install httpd -y

# remove openjdk java - horrible performance
yum remove java* -y

# install Sun JDK 1.8
wget --no-cookies --header "Cookie: gpw_e24=xxx; oraclelicense=accept-securebackup-cookie;" 'http://download.oracle.com/otn-pub/java/jdk/8u121-b13/e9e7ea248e2c4826b92b3f075a80e441/jdk-8u121-linux-x64.rpm'
sudo rpm -i jdk-8u121-linux-x64.rpm

#install awslogs [publish application logs to cloudwatch]
#yum install awslogs -y

export s3_base=j2clark/repo
export application=eureka
export bucket=${s3_base}/${application}


# create the working directory
mkdir /opt/${application}

# create configuration specifying the used profile
#echo "RUN_ARGS=--spring.profiles.active=ec2" > /opt/${application}/${application}.conf

# download the maven artifact from S3
aws s3 cp s3://${bucket}/jar/${application}-latest.jar /opt/${application}/${application}.jar

# download env specific config from S3
#aws s3 cp s3://${bucket}/conf/XYZ /opt/${application}/XYZ

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

# create a symbolic link in /etc/init.d
ln -s /opt/${application}/${application}.jar /etc/init.d/${application}


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
chkconfig ${application} on
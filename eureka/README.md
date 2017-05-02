# Eureka

Eureka on AWS
https://github.com/Netflix/eureka/wiki/Deploying-Eureka-Servers-in-EC2

Highlights
    * In AWS Cloud, instances come and go. This means you cannot identify Eureka servers with a standard hostname or an IP Address. That is where AWS EC2 Elastic IP addresses come in handy.  
    
    1. EC2 Dashboard -> (Newtwork & Security) Elastic IPs -> Allocate new address
    2. Create Autoscale Group for Eureka, associate Elastic IP with instance   
    
    
    
    3. Eureka server requires access to query the ASG information as well as bind/unbind IPs in the cloud. Hence AWS policy should be configured to allow the above accesses. The following is a sample policy with the accesses required.
    {
      "Statement": [
        {
          "Sid": "Stmt1358974336152",
          "Action": [
            "ec2:DescribeAddresses",
            "ec2:AssociateAddress",
            "ec2:DisassociateAddress"
          ],
          "Effect": "Allow",
          "Resource": "*"
        },
        {
          "Sid": "Stmt1358974395291",
          "Action": [
            "autoscaling:DescribeAutoScalingGroups"
          ],
          "Effect": "Allow",
          "Resource": "*"
        }
      ]
    }
    
    Creating a policy
    http://docs.aws.amazon.com/IAM/latest/UserGuide/access_policies_create.html
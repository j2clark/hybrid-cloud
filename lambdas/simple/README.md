
Examples I am following:
https://aws.amazon.com/blogs/compute/building-testing-and-deploying-java-applications-on-aws-lambda-using-maven-and-jenkins/

This gave me appropriate maven package
http://qiita.com/Keisuke69/items/fc39a2f464d14480a432


Creating Lamda DynamoDb IAM Role
http://docs.aws.amazon.com/lambda/latest/dg/with-dynamodb-create-execution-role.html

Getting jar packaging right
http://docs.aws.amazon.com/lambda/latest/dg/java-create-jar-pkg-maven-no-ide.html

Maven Artifacts
Nasty seeming issue with aws artifac versions not getting [ulled properly, resulting in methodNotFound exceptions
I am using aws-java-sdk-dynamodb-1.11.125, 
but following dependencies were coming down with very old versions (1.11.18) until I explicity included then
    aws-java-sdk-core
    aws-java-sdk-s3
    aws-java-sdk-kms

Test data through me for a loop
I was sending json data, but my input param is an int - doh!

IAM Role, as instructed above by AWS, did not work.
I Had to grant full DynamoDB permissions



Ideas:
I like how AWS uses a date for a version
Contains some context as well as uniqueness

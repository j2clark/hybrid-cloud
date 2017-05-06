package com.j2clark;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.Optional;

public class SimpleLambda implements RequestHandler<Integer, String> {

    private static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
    private static DynamoDB dynamoDB = new DynamoDB(client);
    private static String tableName = "Users";

    public String handleRequest(Integer uid, Context context) {

        LambdaLogger lambdaLogger = context.getLogger();
        Table table = dynamoDB.getTable(tableName);

        String name = null;
        try {

            Optional<Item> item = Optional.ofNullable(table.getItem("userid", 1));
            if (item.isPresent()) {
                name = item.get().get("lastName") + ", " + item.get().get("firstName");
                lambdaLogger.log("user[" + uid + "] = " + name);
            } else {
                lambdaLogger.log("user[" + uid + "] not found");
            }
        }
        catch (Exception e) {
            lambdaLogger.log("GetItem failed: " + e.getMessage());
        }

        return name;
    }

    /*@Override
    public String handleRequest(Integer integer, Context context) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }*/
}
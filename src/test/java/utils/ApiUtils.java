package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiUtils {

	private static final String BASE_URL = "http://jsonplaceholder.typicode.com";

    public static Response getUsers() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification httpRequest = RestAssured.given();
        return httpRequest.get("/users");
    }
    
    public static Response getTodos() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification httpRequest = RestAssured.given();
        return httpRequest.get("/todos");
    }
}

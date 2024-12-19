package test;

import com.google.gson.Gson;
import dto.request.UserRequest;
import dto.response.UserResponse;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreateUserAPITest extends BaseAPITest {

    private String createUserPath = "/users";

    @Test
    public void createUser() {
        Gson gson = new Gson();
        // Create UserRequest object
        UserRequest userRequest = new UserRequest("Jane Doe", "doctor");

        // Convert to JSON string
        String jsonRequest = gson.toJson(userRequest);

        RequestSpecification request = given()
                .header("Content-Type", "application/json")
                .body(jsonRequest)
                .log()
                .all();
        Response responseNew = request.post(createUserPath);
        ValidatableResponse validatableResponse = responseNew.then();

        // print response
        System.out.println("-----------------------------------------------");
        System.out.println("Print response: " + responseNew.prettyPrint());
        System.out.println("Get body: " + responseNew.getBody().asString());
        System.out.println("Get response content-type: " + responseNew.getContentType());
        System.out.println("Validate response: " + validatableResponse.body("name", equalTo("Jane Doe")));
        // validate status code
        System.out.println("Status code is: " + validatableResponse.statusCode(201));

        // Deserialize
        UserResponse userResponseData = gson.fromJson(responseNew.prettyPrint(), UserResponse.class);
        System.out.println("Extract field in response data: " + userResponseData.getCreateTime());
        System.out.println("-----------------------------------------------");


        // Send POST request with the JSON body
        String response = given()
                .header("Content-Type", "application/json")
                .body(jsonRequest)
                .post(createUserPath)
                .then()
                .statusCode(201)
                .extract()
                .asString();

        UserResponse userResponseAfterCallPostMethod = gson.fromJson(response, UserResponse.class);
        System.out.println("Response string: " + response);
        System.out.println("Response Deserialize JSON to UserResponse object: " + userResponseAfterCallPostMethod.getCreateTime() + " " + userResponseAfterCallPostMethod.getID());
    }
}

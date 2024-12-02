package test;

import com.google.gson.Gson;
import dto.request.CreateUserRequest;
import dto.response.CreateUserResponse;

import static io.restassured.RestAssured.given;

public class CreateUserAPITest {

    public static void main(String[] args) {

        Gson gson = new Gson();
        // Create UserRequest object
        CreateUserRequest userRequest = new CreateUserRequest("Jane Doe", "doctor");

        // Convert to JSON string
        String jsonRequest = gson.toJson(userRequest);

        // Send POST request with the JSON body
        String response = given()
                .header("Content-Type", "application/json")
                .body(jsonRequest)
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .extract()
                .asString();

        CreateUserResponse userResponseAfterCallPostMethod = gson.fromJson(response, CreateUserResponse.class);
        System.out.println("Response string: " + response);
        System.out.println("Response Deserialize JSON to UserResponse object: " + userResponseAfterCallPostMethod.getCreateTime() + " " + userResponseAfterCallPostMethod.getID());
    }
}

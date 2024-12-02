package test;

import com.google.gson.Gson;
import dto.response.CreateUserResponse;
import dto.response.GetUserResponse;

import static io.restassured.RestAssured.given;

public class GetUserAPITest {

    public static void main(String[] args) {
        Gson gson = new Gson();
        // Send GET request and get response
        String jsonResponse = given()
                .get("https://reqres.in/api/unknown/2")
                .then()
                .statusCode(200)
                .extract()
                .asString();

        // Deserialize JSON to UserResponse object
        GetUserResponse userResponse = gson.fromJson(jsonResponse, GetUserResponse.class);

        System.out.println("Name: " + userResponse.getData().getUserName());
        System.out.println("Color: " + userResponse.getData().getColor());
    }
}

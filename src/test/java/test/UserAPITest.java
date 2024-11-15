package test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.UserDTO;

import static io.restassured.RestAssured.given;

public class UserAPITest {
    @Test
    public static void main(String[] args) {
        // Set up the base URI for Rest Assured
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";

        // Serialize DTO into JSON and send POST request
        UserDTO newUser = new UserDTO("1", "John Doe", "john@example.com");

        Response postResponse = given()
                .contentType("application/json")
                .body(newUser)
                .post("/users");

        System.out.println("POST Response Status: " + postResponse.getStatusCode());
        postResponse.prettyPrint();
        // validate status code
        Assert.assertEquals(postResponse.getStatusCode(), 200);

        // Deserialize JSON response to DTO
        UserDTO createdUser = postResponse.as(UserDTO.class);
        System.out.println("Created User: " + createdUser);
    }
}

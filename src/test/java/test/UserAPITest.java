package test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import dto.UserDTO;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

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
        System.out.println("Type of response: " + postResponse.getClass().getName());
        postResponse.prettyPrint();
        // validate status code
        Assert.assertEquals(postResponse.getStatusCode(), 201);

        // Deserialize JSON response to DTO
        UserDTO createdUser = postResponse.as(UserDTO.class);
        System.out.println("Created User: " + createdUser + " " + createdUser.getClass().getName().getClass());

        JSONObject data = new JSONObject();

        data.put("employee_name", "MapTest");
        data.put("profile_image", "test.png");
        data.put("employee_age", "30");
        data.put("employee_salary", "11111");

        System.out.println("json object: " + data.toString());

        Map<String, String> map = new HashMap<String, String>();
        map.put("employee_name", "MapTest");
        map.put("employee_salary", "99999");
        map.put("employee_age", "30");
        map.put("profile_image", "test.png");

        System.out.println("Map after init: " + map);
    }
}

package test;

import dto.request.UserRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import com.google.gson.Gson;


public class UpdateUserAPITest extends BaseAPITest {

    private String path = "/users/2";

    @Test
    public void updateEntireUserWithPUTMethod() {

        Gson gson = new Gson();
        // create user object
        UserRequest userRequest = new UserRequest("harry", "IT");

        // convert object to JSON string
        String payload = gson.toJson(userRequest);

        // create request specification
        RequestSpecification requestSpecification = RestAssured.given()
                .log()
                .all();

        // Setting content type to specific format in which request
        requestSpecification.contentType(ContentType.JSON);

        // Add body as String
        requestSpecification.body(payload);

        // calling put method
        Response response = requestSpecification.put(path);
        ValidatableResponse res = response.then()
                .statusCode(200)
                .log()
                .all();

        System.out.println(res.extract().asString());

        // Let's print response body.
        String responseString = response.prettyPrint();

    }

    @Test
    public void updatePartialUserWithPatchMethod() {
        Gson gson = new Gson();

        // Create object user
        UserRequest userRequest = new UserRequest("teacher");

        // convert object to JSON string
        String payload = gson.toJson(userRequest);

        // create specification request
        RequestSpecification requestSpecification = RestAssured.given();

        // setting content type to specific format in which request
        requestSpecification.contentType(ContentType.JSON);

        // send payload
        requestSpecification.body(payload);

        // send patch request
        Response response = requestSpecification.patch(path);

        // print result
        String result = response.prettyPrint();
    }
}

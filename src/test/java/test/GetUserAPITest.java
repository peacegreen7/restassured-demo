package test;

import com.google.gson.Gson;
import dto.response.GetUserResponse;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetUserAPITest extends BaseAPITest {
    private int userId = 2;
    private String getUserByIDPath = "/unknown/" + userId;
    private String getListUserPath = "/users";

    @Test
    public void getSpecificUser() {
        Gson gson = new Gson();
        // Send GET request and get response
        String jsonResponse = given()
                .log()
                .all()
                .get(getUserByIDPath)
                .then()
                .statusCode(200)
                .extract()
                .asString();

        // Deserialize JSON to UserResponse object
        GetUserResponse userResponse = gson.fromJson(jsonResponse, GetUserResponse.class);

        System.out.println("Name: " + userResponse.getData().getUserName());
        System.out.println("Color: " + userResponse.getData().getColor());
    }

    @Test
    public void getListUser() {

        int page = 1;
        Response res, resGet;
        String resTotal, perPage;

        resGet = given()
                .get(getListUserPath);
        resTotal = resGet.jsonPath().get("total").toString();
        perPage = resGet.jsonPath().get("per_page").toString();

        int total = Integer.parseInt(resTotal);
        int count = Integer.parseInt(perPage);

        do {
            res = given().param("page", page).get(getListUserPath);
            List<String> users = res.jsonPath().getList("data.id");
            System.out.println("Page " + page + ": " + users);
            page++;
        } while (count * page <= total);
    }
}

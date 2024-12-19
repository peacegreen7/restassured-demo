package test.mock;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;

public class WireMockTest extends WireMockBaseTest {

    @Test
    public void testMockedAPI() {
        // Create a mock POST endpoint
        wireMockServer.stubFor(post(urlEqualTo("/api/test"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{ \"message\": \"Mocked Response\" }")));

        // Send a POST request to the mocked endpoint using RestAssured
        ValidatableResponse res =  given()
                .baseUri("http://localhost:8090")
                .contentType("application/json")
                .body("{ \"key\": \"value\" }")
                .post("/api/test")
                .then()
                .statusCode(200)
                .log()
                .all();

        System.out.println(res.extract().asString());
    }
}

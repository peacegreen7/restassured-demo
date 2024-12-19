package test;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;

public class BaseAPITest {
    @BeforeSuite
    public void init() {
        RestAssured.baseURI = "https://reqres.in/api";
    }
}

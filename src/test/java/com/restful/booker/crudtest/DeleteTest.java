package com.restful.booker.crudtest;

import com.restful.booker.testbase.TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class DeleteTest extends TestBase
{
    public String getToken;
    @Test
    public void test001()
    {

        String payload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";
        Response response = given().
                baseUri("https://restful-booker.herokuapp.com").
                contentType("application/json").
                body(payload).
                when().
                post("/auth").
                then().
                log().all().
                extract().response();
        getToken = response.jsonPath().getString("token");
        System.out.println("Token :" + getToken);
    }
    @Test
    public void test002()
    {
        Response response = given().
                baseUri("https://restful-booker.herokuapp.com")
                .contentType(ContentType.JSON)
                .when()
                . header("Cookie","token=ef3bdf17bc74737")
                .delete("/booking/10");
                response.then().log().all().statusCode(201);

    }


}

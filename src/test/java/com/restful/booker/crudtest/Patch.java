package com.restful.booker.crudtest;

import com.restful.booker.model.BookingPojo;
import com.restful.booker.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class Patch
{
    static String  firstname = "Jim" + TestUtils.getRandomValue();
    static String lastname = "Brown"+ TestUtils.getRandomValue();
    static Integer totalprice = 111 ;
    static Boolean depositpaid = true;
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

        HashMap<Object, Object> bookingdates = new HashMap<>();
        bookingdates.put("checkin", "2018-01-01");
        bookingdates.put("checkout", "2019-01-01");

        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstname(firstname);
        bookingPojo.setLastname(lastname);
        bookingPojo.setTotalprice(totalprice);
        bookingPojo.setDepositpaid(depositpaid);
        bookingPojo.setBookingdates(bookingdates);
        bookingPojo.setAdditionalneeds("Breakfast");

        Response response = given().
                baseUri("https://restful-booker.herokuapp.com")
                .contentType(ContentType.JSON)
                .header("Content-Type", "application/json")
                .header("Cookie", "token=378655ddc0d6b63")
                //. header("Cookie","token="+getToken)
                .pathParam("id", 2)
                .body(bookingPojo)
                .when()
                .patch("/booking/{id}");
        response.then().statusCode(200);
        response.then().time(lessThan(3000L));
        response.prettyPrint();

    }
}

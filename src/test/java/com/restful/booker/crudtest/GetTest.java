package com.restful.booker.crudtest;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetTest
{
    //Singe User
    @Test
    public void Test001()
    {
        int bookingId = given().
                baseUri("https://restful-booker.herokuapp.com").
                contentType("application/json").
                when().
                get("/booking").
                then().
                extract().
                response().
                path("bookingid[0]");
        System.out.println("Booking Id : "+ bookingId);

    }

    //All booking details
    @Test
    public void Test002()
    {
        Response response = given().
                baseUri("https://restful-booker.herokuapp.com").
                contentType("application/json").
                when().
                get("/booking");
        response .then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void Test003()
    {
        Response response = given().
                baseUri("https://restful-booker.herokuapp.com").
                contentType("application/json").
                when().
                get("/booking?checkin=2014-03-13&checkout=2014-05-21");
        response .then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void Test004()
    {
        Response response = given().
                baseUri("https://restful-booker.herokuapp.com").
                contentType("application/json").
                when().
                get("/booking?firstname=sally&lastname=brown");
        response .then().statusCode(200);
        response.prettyPrint();
    }


}

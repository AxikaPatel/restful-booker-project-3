package com.restful.booker.crudtest;

import com.restful.booker.model.BookingPojo;
import com.restful.booker.utils.TestUtils;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class CrudTest
{
    public String getToken;

    static String  firstname = "Jim" + TestUtils.getRandomValue();
    static String lastname = "Brown"+ TestUtils.getRandomValue();
    static int totalprice = 111 ;
    static boolean depositpaid = true;
    static String additionalneeds;




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


        List<String> bookingdates = new ArrayList<>();
        bookingdates.add("2018-01-01");
        bookingdates.add("2019-01-01");

        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstname(firstname);
        bookingPojo.setLastname(lastname);
        bookingPojo.setTotalprice(totalprice);
        bookingPojo.setDepositpaid(depositpaid);
        bookingPojo.setBookingdates(bookingdates);

        given().
                baseUri("https://restful-booker.herokuapp.com").
                contentType("application/json").
                body(bookingPojo).
                header("Cookie","token="+ getToken).
                log().all().
                when().
                post("/booking").
                then().log().all().statusCode(201);


    }

    //Singe User
    @Test
    public void test003()
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
    public void test004()
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
    public void test005()
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
    public void test006()
    {
        Response response = given().
                baseUri("https://restful-booker.herokuapp.com").
                contentType("application/json").
                when().
                get("/booking?firstname=sally&lastname=brown");
        response .then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void test007()
    {

        List<String> bookingdates = new ArrayList<>();
        bookingdates.add("2018-01-01");
        bookingdates.add("2019-01-01");

        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstname(firstname);
        bookingPojo.setLastname(lastname);
        bookingPojo.setTotalprice(totalprice);
        bookingPojo.setDepositpaid(depositpaid);
        bookingPojo.setBookingdates(bookingdates);
        given().
                baseUri("https://restful-booker.herokuapp.com").
                contentType("application/json").
                body(bookingPojo).
                header("Cookies","token="+getToken).
                log().all().
                when().
                put("/booking/1").
                then().log().all().statusCode(200);
    }

    @Test
    public void test008()
    {


        List<String> bookingdates = new ArrayList<>();
        bookingdates.add("2018-01-01");
        bookingdates.add("2019-01-01");

        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstname(firstname);
        bookingPojo.setLastname(lastname);
        bookingPojo.setTotalprice(totalprice);
        bookingPojo.setDepositpaid(depositpaid);
        bookingPojo.setBookingdates(bookingdates);

        given().
                baseUri("https://restful-booker.herokuapp.com").
                contentType("application/json").
                body(bookingPojo).
                header("Cookie","token="+getToken).
                log().all().
                when().
                patch("/booking/10").
                then().log().all().statusCode(200);


    }

    @Test
    public void test009()
    {
        given().
                baseUri("https://restful-booker.herokuapp.com").
                contentType("application/json").
                header("Cookie","token="+getToken).
                log().all().
                when().
                delete("/booking/10").then().log().all().statusCode(201);

    }



}

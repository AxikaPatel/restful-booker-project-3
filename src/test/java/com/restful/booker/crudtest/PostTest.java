package com.restful.booker.crudtest;

import com.restful.booker.model.BookingPojo;
import com.restful.booker.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class PostTest
{
    static String  firstname = "Jim" + TestUtils.getRandomValue();
    static String lastname = "Brown"+ TestUtils.getRandomValue();
    static Integer totalprice = 111 ;
    static Boolean depositpaid = true;


    @Test
    public void createNewBooking() {

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
                .when()
                .body(bookingPojo)
                . header("Cookie","token="+new GetToken().getToken)
                .post("/booking");
        response.then().log().all().statusCode(200);


    }
 }



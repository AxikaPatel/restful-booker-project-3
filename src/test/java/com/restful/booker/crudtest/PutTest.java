package com.restful.booker.crudtest;

import com.restful.booker.model.BookingPojo;
import com.restful.booker.testbase.TestBase;
import com.restful.booker.utils.TestUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class PutTest extends TestBase
{
    static String  firstname = "Jim" + TestUtils.getRandomValue();
    static String lastname = "Brown"+ TestUtils.getRandomValue();
    static int totalprice = 111 ;
    static boolean depositpaid = true;
    static String additionalneeds;






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
            header("Cookies","token="+new GetToken().getToken).
            log().all().
            when().
            put("/booking/1").
            then().log().all().statusCode(200);


//    Response response = given()
//            .contentType(ContentType.JSON)
//            .when()
//            .body(bookingPojo)
//            .header("Cookies","token =" + getToken)
//            .log().all().
//            when().
//            put("/booking/1");
//
//    response.then().log().all().statusCode(200);



}






}

package com.restful.booker.model;

import java.util.List;

public class BookingPojo
{

    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private List<String> bookingdates;
    private String checkin;
    private String checkout;
    private String additionalneeds;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public boolean isDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public List<String> getBookingdates() {
        return bookingdates;
    }

    public void setBookingdates(List<String> bookingdates) {
        this.bookingdates = bookingdates;
    }

}

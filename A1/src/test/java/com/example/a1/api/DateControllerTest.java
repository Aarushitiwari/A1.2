package com.example.a1.api;

import com.example.a1.A1Application;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Calendar;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class DateControllerTest {
    private static ConfigurableApplicationContext c;

    @BeforeAll
    public static void setup() {
        c = SpringApplication.run(A1Application.class, "null");
    }

    @AfterAll
    public static void clean() {
        c.close();
    }

    // fetch the current date
    @Test
    void getDateSuccess() {
        Calendar c = Calendar.getInstance();

        given().
                param("type","date").
        when().
                get("/api/v1/current").
        then().
                statusCode(200).
                body(equalTo(Integer.toString(c.get(Calendar.DATE))));
    }

    @Test
    void getDateFail() {
        Calendar c = Calendar.getInstance();

        given().
                param("t","date"). // invalid parameter name passed
                when().
                get("/api/v1/current").
                then().
                statusCode(400);
    }

    // fetch the current day
    @Test
    void getDaySuccess() {
        Calendar c = Calendar.getInstance();

        given().
                param("type","day").
                when().
                get("/api/v1/current").
                then().
                statusCode(200).
                body(equalTo(Integer.toString(c.get(Calendar.DAY_OF_WEEK))));
    }

    @Test
    void getDayFail() {
        Calendar c = Calendar.getInstance();

        given().
                param("t","day"). // invalid parameter name passed
                when().
                get("/api/v1/current").
                then().
                statusCode(400);
    }

    // fetch the current month
    @Test
    void getMonthSuccess() {
        Calendar c = Calendar.getInstance();

        given().
                param("type","month").
                when().
                get("/api/v1/current").
                then().
                statusCode(200).
                body(equalTo(Integer.toString(c.get(Calendar.MONTH))));
    }

    @Test
    void getMonthFail() {
        Calendar c = Calendar.getInstance();

        given().
                param("t","month"). // invalid parameter name passed
                when().
                get("/api/v1/current").
                then().
                statusCode(400);
    }

    // fetch the current year
    @Test
    void getYearSuccess() {
        Calendar c = Calendar.getInstance();

        given().
                param("type","year").
                when().
                get("/api/v1/current").
                then().
                statusCode(200).
                body(equalTo(Integer.toString(c.get(Calendar.YEAR))));
    }

    @Test
    void getYearFail() {
        Calendar c = Calendar.getInstance();

        given().
                param("t","year"). // invalid parameter name passed
                when().
                get("/api/v1/current").
                then().
                statusCode(400);
    }
}
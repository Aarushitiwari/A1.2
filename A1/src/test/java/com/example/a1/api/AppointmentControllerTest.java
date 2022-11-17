package com.example.a1.api;

import com.example.a1.A1Application;
import com.example.a1.model.Appointment;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDate;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class AppointmentControllerTest {
    private static ConfigurableApplicationContext c;

    @BeforeAll
    public static void setup() {
        c = SpringApplication.run(A1Application.class, "null");
    }

    @AfterAll
    public static void clean() {
        c.close();
    }

    // test to add an appointment
    @Test
    void addAppointmentSuccess() {
        Appointment testAppointment = new Appointment(UUID.randomUUID(),"Charles");

        given().
                contentType("application/json").
                body(testAppointment).
        when().
                post("/api/v1/appointments").
        then().
                statusCode(200);
    }

    @Test
    void addAppointmentFail() {
        when().
                post("/api/v1/appointments").          // incomplete parameters were passed
        then().
                statusCode(415);
    }

    // test to fetch all appointments for a given date
    @Test
    void getAppointmentByDateSuccess() {
        when().
                get("/api/v1/appointments/{date}","2022-11-16").
        then().
                statusCode(200);
    }

    @Test
    void getAppointmentByDateFail() {
        when().
                get("/api/v1/appointments").  // missing parameters
        then().
                statusCode(405);
    }

    // test the deletion of appointment by ID
    @Test
    void deleteAppointmentByIdSuccess() {
        when().
                delete("/api/v1/appointments/{id}",UUID.randomUUID()).
        then().
                statusCode(200);
    }

    @Test
    void deleteAppointmentByIdFail() {
        when().
                delete("/api/v1/appointments"). // missing parameters
                then().
                statusCode(405);
    }

    // test the updating of an existing appointment by ID
    @Test
    void updateAppointmentSuccess() {
        UUID id = UUID.randomUUID();
        Appointment newAppointment = new Appointment(id,"Charles");

        given().
                contentType("application/json").
                body(newAppointment).
        when().
                put("/api/v1/appointments/{id}",id).
                then().
                statusCode(200);
    }

    @Test
    void updateAppointmentFail() {
        UUID id = UUID.randomUUID();
        Appointment newAppointment = new Appointment(id,"Charles");

        given().
                contentType("application/json").
                body(newAppointment).
                when().
                put("/api/v1/appointments/{id}",45645).    // invalid parameter passed
                then().
                statusCode(400);
    }
}
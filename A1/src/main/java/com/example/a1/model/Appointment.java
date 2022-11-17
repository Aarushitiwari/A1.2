package com.example.a1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

// appointment oclass
public class Appointment {
    private final UUID id;   // unique appointment ID
    private final String date;    // current day date
    @NotBlank
    private final String pName;     // patient name

    // constructor
    public Appointment(@JsonProperty("id") UUID id, @JsonProperty("name") String pName) {
        this.id = id;
        this.pName = pName;
        this.date = LocalDate.now().toString();
    }

    public UUID getID() {
        return id;
    }

    public String getName() {
        return pName;
    }

    public String getDate() {
        return LocalDate.now().toString();
    }
}
